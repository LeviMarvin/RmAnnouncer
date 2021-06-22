package net.rmplugins.rmannouncer.util;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class JsonUtil {
    /**
     * Judge whether the input text is JSON
     *
     * @param str +String+ The text that you want to judge.
     * @return +boolean+ true or false
     */
    public static boolean isJson(String str) {
        boolean result = false;
        if (str != null) {
            str = str.trim();
            if (str.startsWith("{") && str.endsWith("}")) {
                result = true;
            } else if (str.startsWith("[") && str.endsWith("]")) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Converse an input test to Standard-JSON type
     *
     * @param str +String+ The text that you want to converse.
     * @return +String+ An standard-JSON type text
     */
    public static String toTextJson(String str) {
        String text;
        text = "{\"text\":\"" + str + "\"}";
        return text;
    }
}
