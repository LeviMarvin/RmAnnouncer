package net.rmplugins.rmannouncer.util;

import static net.rmplugins.rmannouncer.data.plugin.Main.PLUGIN;
import static net.rmplugins.rmannouncer.data.plugin.Main.PREFIX;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class MessageUtil {
    /**
     * Send a message to server console as an info.
     * @param text +String+ The message you want to send.
     */
    public static void sendMsg(String text) {
        PLUGIN.getServer().getConsoleSender().sendMessage(PREFIX + "§a" + text);
    }

    public static void sendMsg(String[] text) {
        PLUGIN.getServer().getConsoleSender().sendMessage(text);
    }

    public static void sendError(String text) {
        PLUGIN.getServer().getConsoleSender().sendMessage(PREFIX + "§c " + text);
    }

    /**
     * Send a message to server console as an alert.
     * @param text +String+ The message you want to send.
     */
    public static void sendAlert(String text) {
        PLUGIN.getServer().getConsoleSender().sendMessage(PREFIX + "§e " + text);
    }
}
