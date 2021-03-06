package Sergey_Dertan.SRegionProtector.Command.Manage;

import Sergey_Dertan.SRegionProtector.Command.SRegionProtectorCommand;
import Sergey_Dertan.SRegionProtector.Region.Region;
import Sergey_Dertan.SRegionProtector.Region.RegionManager;
import Sergey_Dertan.SRegionProtector.Region.Selector.RegionSelector;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.math.Vector3;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;

import java.util.Map;

public final class RegionSelectCommand extends SRegionProtectorCommand {

    private final RegionManager regionManager;
    private final RegionSelector selector;

    public RegionSelectCommand(RegionManager regionManager, RegionSelector selector) {
        super("rgselect", "select");
        this.regionManager = regionManager;
        this.selector = selector;

        Map<String, CommandParameter[]> parameters = new Object2ObjectArrayMap<>();
        parameters.put("rgselect", new CommandParameter[]{
                new CommandParameter("region", CommandParamType.STRING, false)
        });
        this.setCommandParameters(parameters);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            this.messenger.sendMessage(sender, "command.select.in-game");
            return false;
        }
        if (!this.testPermissionSilent(sender)) {
            this.messenger.sendMessage(sender, "command.select.permission");
            return false;
        }
        if (args.length < 1 || args[0].isEmpty()) {
            this.messenger.sendMessage(sender, "command.select.usage");
            return false;
        }
        Region rg = this.regionManager.getRegion(args[0]);
        if (rg == null) {
            this.messenger.sendMessage(sender, "command.select.region-doesnt-exists", "@region", args[0]);
            return false;
        }
        if (!rg.isLivesIn(sender.getName()) && !sender.hasPermission("sregionprotector.region.select-other")) {
            this.messenger.sendMessage(sender, "command.select.permission");
            return false;
        }
        if (!rg.level.equalsIgnoreCase(((Player) sender).level.getName())) {
            this.messenger.sendMessage(sender, "command.select.different-worlds");
            return false;
        }
        this.messenger.sendMessage(sender, "command.select.success");
        this.selector.showBorders((Player) sender, new Vector3(rg.minX, rg.minY, rg.minZ), new Vector3(rg.maxX, rg.maxY, rg.maxZ));
        return false;
    }
}
