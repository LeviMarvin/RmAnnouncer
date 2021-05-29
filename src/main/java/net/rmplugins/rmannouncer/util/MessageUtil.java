package net.rmplugins.rmannouncer.util;

import java.util.Arrays;

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
     * @param text String - The message you want to send.
     */
    public static void sendInfo(String text) {
        PLUGIN.getServer().getConsoleSender().sendMessage(PREFIX + "§f" + text);
    }

    public static void sendMsg(String text) {
        PLUGIN.getServer().getConsoleSender().sendMessage(PREFIX + "§a" + text);
    }

    public static void sendMsg(String[] text) {
        PLUGIN.getServer().getConsoleSender().sendMessage(text);
    }

    public static void sendError(String text) {
        PLUGIN.getServer().getConsoleSender().sendMessage(PREFIX + "§c" + text);
    }

    /**
     * Send a message to server console as an alert.
     * @param text String - The message you want to send.
     */
    public static void sendAlert(String text) {
        PLUGIN.getServer().getConsoleSender().sendMessage(PREFIX + "§e" + text);
    }

    /**
     * @deprecated Please use the new one.
     * <br/>
     * Send a message to server console as an error.
     * @param errorText String[] - The error info you want to send.<br/>
     *                  errorText[0] = "Message: " + e.getMessage();<br/>
     *                  errorText[1] = "Location(Class): " + e.getClass().getName();<br/>
     *                  errorText[2] = "Cause: " + e.getCause().toString();<br/>
     *                  errorText[3] = "StackTrace: " + Arrays.toString(e.getStackTrace());<br/>
     *
     */
    public static void sendError(String[] errorText) {
        for (String text : errorText) {
            PLUGIN.getServer().getConsoleSender().sendMessage("§c" + text);
        }
    }

    /**
     * @deprecated Please use sendError() instead.
     * <br/>
     * Return a custom exception information.
     * @param e Exception - An exception.
     * @link sendError()
     */
    @Deprecated
    public static String[] getExceptionInfo(Exception e) {
        String[] info = new String[5];
        info[0] = "  §eOops! There are exception was thrown!";
        info[1] = "    Message: " + e.getMessage();
        info[2] = "    Location(Class): " + e.getClass().getName();
        if (e.getCause() == null) {
            info[3] = "    Cause: \n      " + "NULL";
        }else {
            info[3] = "    Cause: \n      " + e.getCause().toString();
        }
        info[4] = "    StackTrace: " + Arrays.toString(e.getStackTrace());
        return info;
    }
}
