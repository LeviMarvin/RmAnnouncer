package net.rmplugins.rmannouncer.core.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import static net.rmplugins.rmannouncer.data.plugin.Extension.*;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class StringUtil {
    /**
     * @
     */
    public static String getKeyValue(String jsonText, String key) {
        // Create Json object.
        JsonObject jsonObject = new JsonParser().parse(jsonText).getAsJsonObject();
        // Get text's value.
        return jsonObject.get(key).getAsString();
    }

    public static String setKeyValue(String jsonText, String key, String value) {
        // Create Json object.
        JsonObject jsonObject = new JsonParser().parse(jsonText).getAsJsonObject();
        // Set text to jsonObject.
        jsonObject.addProperty(key, value);
        return jsonObject.getAsString();
    }

    public static String translateString(Player player, String text) {
        String tmp1, tmp2;
        if (PAPI_support) {
            tmp1 = PlaceholderAPI.setPlaceholders(player, text);
        }else {
            tmp1 = text;
        }
        tmp2 = tmp1;
        return tmp2;
    }
}
