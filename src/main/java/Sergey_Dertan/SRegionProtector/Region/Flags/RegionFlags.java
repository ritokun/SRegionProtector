package Sergey_Dertan.SRegionProtector.Region.Flags;

import Sergey_Dertan.SRegionProtector.Region.Flags.Flag.RegionFlag;
import Sergey_Dertan.SRegionProtector.Region.Flags.Flag.RegionSellFlag;
import Sergey_Dertan.SRegionProtector.Region.Flags.Flag.RegionTeleportFlag;
import Sergey_Dertan.SRegionProtector.Utils.Utils;
import cn.nukkit.Server;
import cn.nukkit.permission.Permissible;
import cn.nukkit.permission.Permission;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class RegionFlags {

    /**
     * https://github.com/SergeyDertan/SRegionProtector/wiki/Flags
     */
    public static final int FLAG_INVALID = -1;
    public static final int FLAG_PLACE = 0;
    public static final int FLAG_BREAK = 29;
    public static final int FLAG_INTERACT = 1;
    public static final int FLAG_USE = 2;
    public static final int FLAG_PVP = 3;
    public static final int FLAG_EXPLODE = 4;
    public static final int FLAG_LIGHTER = 5;
    public static final int FLAG_MAGIC_ITEM_USE = 6;
    public static final int FLAG_HEAL = 7;
    public static final int FLAG_INVINCIBLE = 8;
    public static final int FLAG_TELEPORT = 9;
    public static final int FLAG_SELL = 10;
    public static final int FLAG_POTION_LAUNCH = 11;
    public static final int FLAG_MOVE = 12;
    public static final int FLAG_LEAVES_DECAY = 13;
    public static final int FLAG_ITEM_DROP = 14;
    public static final int FLAG_SEND_CHAT = 15;
    public static final int FLAG_RECEIVE_CHAT = 16;
    public static final int FLAG_HEALTH_REGEN = 17;
    public static final int FLAG_MOB_DAMAGE = 18;
    public static final int FLAG_MOB_SPAWN = 19;
    public static final int FLAG_CROPS_DESTROY = 20;
    public static final int FLAG_REDSTONE = 21;
    public static final int FLAG_ENDER_PEARL = 22;
    public static final int FLAG_EXPLODE_BLOCK_BREAK = 23;
    public static final int FLAG_LIGHTNING_STRIKE = 24;
    public static final int FLAG_FIRE = 25;
    public static final int FLAG_LIQUID_FLOW = 26; //lava & water spread
    public static final int FLAG_CHEST_ACCESS = 27;
    public static final int FLAG_SLEEP = 28;
    public static final int FLAG_CHUNK_LOADER = 30;
    public static final int FLAG_SMART_DOORS = 31;
    public static final int FLAG_MINEFARM = 32;
    public static final int FLAG_FALL_DAMAGE = 33;

    public static final int FLAG_AMOUNT = 34;

    public static final RegionFlag[] defaults = new RegionFlag[FLAG_AMOUNT];
    public static final Permission[] permissions = new Permission[FLAG_AMOUNT];

    public static final BiMap<Integer, String> flags; //flags names
    public static final Map<String, Integer> aliases; //flags names aliases
    public static final Map<Integer, Boolean> state; //true if "allow" means that flag should be disabled

    static {
        BiMap<Integer, String> flagList = HashBiMap.create(FLAG_AMOUNT);
        flagList.put(FLAG_PLACE, "place");
        flagList.put(FLAG_BREAK, "break");
        flagList.put(FLAG_INTERACT, "interact");
        flagList.put(FLAG_USE, "use");
        flagList.put(FLAG_PVP, "pvp");
        flagList.put(FLAG_EXPLODE, "tnt");
        flagList.put(FLAG_LIGHTER, "lighter");
        flagList.put(FLAG_MAGIC_ITEM_USE, "magic-item");
        flagList.put(FLAG_HEAL, "heal");
        flagList.put(FLAG_INVINCIBLE, "invincible");
        flagList.put(FLAG_TELEPORT, "teleport");
        flagList.put(FLAG_SELL, "sell");
        flagList.put(FLAG_POTION_LAUNCH, "potion-launch");
        flagList.put(FLAG_MOVE, "move");
        flagList.put(FLAG_LEAVES_DECAY, "leaves-decay");
        flagList.put(FLAG_ITEM_DROP, "item-drop");
        flagList.put(FLAG_SEND_CHAT, "send-chat");
        flagList.put(FLAG_RECEIVE_CHAT, "receive-chat");
        flagList.put(FLAG_HEALTH_REGEN, "health-regen");
        flagList.put(FLAG_MOB_DAMAGE, "mob-damage");
        flagList.put(FLAG_MOB_SPAWN, "mob-spawn");
        flagList.put(FLAG_CROPS_DESTROY, "crops-destroy");
        flagList.put(FLAG_REDSTONE, "redstone");
        flagList.put(FLAG_ENDER_PEARL, "ender-pearl");
        flagList.put(FLAG_EXPLODE_BLOCK_BREAK, "explode-block-break");
        flagList.put(FLAG_LIQUID_FLOW, "liquid-flow");
        flagList.put(FLAG_FIRE, "fire");
        flagList.put(FLAG_LIGHTNING_STRIKE, "lightning-strike");
        flagList.put(FLAG_CHEST_ACCESS, "chest-access");
        flagList.put(FLAG_SLEEP, "sleep");
        flagList.put(FLAG_CHUNK_LOADER, "chunk-loader");
        flagList.put(FLAG_SMART_DOORS, "smart-doors");
        flagList.put(FLAG_MINEFARM, "minefarm");
        flagList.put(FLAG_FALL_DAMAGE, "fall-damage");
        flags = ImmutableBiMap.copyOf(flagList);

        Map<String, Integer> aAliases = new HashMap<>(FLAG_AMOUNT);
        flagList.forEach((k, v) -> {
            aAliases.put(v.replace("-", "_"), k);
            aAliases.put(v.replace("-", ""), k);
            aAliases.remove(v);
        });
        aliases = ImmutableMap.copyOf(aAliases);

        flags.forEach((k, v) -> permissions[k] = Server.getInstance().getPluginManager().getPermission("sregionprotector.region.flag." + v.replace("-", "_")));

        Map<Integer, Boolean> fState = new HashMap<>(FLAG_AMOUNT);
        fState.put(FLAG_PLACE, true);
        fState.put(FLAG_BREAK, true);
        fState.put(FLAG_INTERACT, true);
        fState.put(FLAG_USE, true);
        fState.put(FLAG_PVP, true);
        fState.put(FLAG_EXPLODE, true);
        fState.put(FLAG_LIGHTER, true);
        fState.put(FLAG_MAGIC_ITEM_USE, true);
        fState.put(FLAG_HEAL, false);
        fState.put(FLAG_INVINCIBLE, false);
        fState.put(FLAG_TELEPORT, false);
        fState.put(FLAG_SELL, false);
        fState.put(FLAG_POTION_LAUNCH, true);
        fState.put(FLAG_MOVE, true);
        fState.put(FLAG_LEAVES_DECAY, true);
        fState.put(FLAG_ITEM_DROP, true);
        fState.put(FLAG_SEND_CHAT, true);
        fState.put(FLAG_RECEIVE_CHAT, true);
        fState.put(FLAG_HEALTH_REGEN, true);
        fState.put(FLAG_MOB_DAMAGE, true);
        fState.put(FLAG_MOB_SPAWN, true);
        fState.put(FLAG_CROPS_DESTROY, true);
        fState.put(FLAG_REDSTONE, true);
        fState.put(FLAG_ENDER_PEARL, true);
        fState.put(FLAG_EXPLODE_BLOCK_BREAK, true);
        fState.put(FLAG_LIQUID_FLOW, true);
        fState.put(FLAG_FIRE, true);
        fState.put(FLAG_LIGHTNING_STRIKE, true);
        fState.put(FLAG_CHEST_ACCESS, true);
        fState.put(FLAG_SLEEP, true);
        fState.put(FLAG_CHUNK_LOADER, false);
        fState.put(FLAG_SMART_DOORS, false);
        fState.put(FLAG_MINEFARM, false);
        fState.put(FLAG_FALL_DAMAGE, false);

        state = ImmutableMap.copyOf(fState);
    }

    private RegionFlags() {
    }

    public static void init(boolean[] flagsDefault) {
        for (int i = 0; i < FLAG_AMOUNT; ++i) {
            defaults[i] = new RegionFlag(flagsDefault[i]);
        }
        defaults[FLAG_TELEPORT] = new RegionTeleportFlag(flagsDefault[FLAG_TELEPORT]);
        defaults[FLAG_SELL] = new RegionSellFlag(flagsDefault[FLAG_SELL]);
    }

    public static RegionFlag[] getDefaultFlagList() {
        return Utils.deepClone(Arrays.asList(defaults)).toArray(new RegionFlag[FLAG_AMOUNT]);
    }

    public static Permission getFlagPermission(int flag) {
        return permissions[flag];
    }

    public static String getFlagName(int flag) {
        return flags.get(flag);
    }

    public static int getFlagId(String name) {
        name = name.toLowerCase();
        int id = flags.inverse().getOrDefault(name, FLAG_INVALID);
        if (id == FLAG_INVALID) id = aliases.getOrDefault(name, FLAG_INVALID);
        return id;
    }

    public static boolean getStateFromString(String state, int flag) {
        if (state.equalsIgnoreCase("allow")) return !RegionFlags.state.get(flag);
        if (state.equalsIgnoreCase("deny")) return RegionFlags.state.get(flag);
        throw new RuntimeException("Wrong state");
    }

    public static void fixMissingFlags(RegionFlag[] flags) {
        for (int i = 0; i < FLAG_AMOUNT; ++i) {
            if (flags[i] != null) continue;
            flags[i] = defaults[i].clone();
        }
    }

    public static boolean hasFlagPermission(Permissible target, int flag) {
        return target.hasPermission(permissions[flag]);
    }

    public static boolean hasFlagPermission(Permissible target, String flag) {
        return hasFlagPermission(target, getFlagId(flag));
    }

    public static boolean getDefaultFlagState(int flag) {
        return defaults[flag].state;
    }

    public static boolean getDefaultFlagState(String flag) {
        return getDefaultFlagState(getFlagId(flag));
    }
}
