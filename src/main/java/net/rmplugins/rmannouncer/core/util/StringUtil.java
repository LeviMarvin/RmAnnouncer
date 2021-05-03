package net.rmplugins.rmannouncer.core.util;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import static net.rmplugins.rmannouncer.data.plugin.Extension.*;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class StringUtil {
    public static String translateString(Player player, String text) {
        String tmp1, tmp2;
        if (PAPI_support) {
            tmp1 = PlaceholderAPI.setPlaceholders(player, text);
        }else {
            tmp1 = text;
        }
        if (VaultAPI_support) {}
    }
}
