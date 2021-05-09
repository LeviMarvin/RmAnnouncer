package net.rmplugins.rmannouncer.util;

import java.util.Arrays;

import static net.rmplugins.rmannouncer.data.plugin.Main.PLUGIN;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class MessageUtil implements Util{
    /**
     * Send a message to server console as an info.
     * @param text String - The message you want to send.
     */
    public static void sendInfo(String text) {
        PLUGIN.getServer().getConsoleSender().sendMessage("§f" + text);
    }

    public static void sendMsg(String text) { PLUGIN.getServer().getConsoleSender().sendMessage("§a" + text);}

    public static void sendError(String text) {
        PLUGIN.getServer().getConsoleSender().sendMessage("§c" + text);
    }

    public static void sendError(Exception exception) {
        String[] exceptionInfo = new String[5];
        exceptionInfo[0] = "  Oops! There are exception was thrown!";
        exceptionInfo[1] = "    Message: \n      " + exception.getMessage();
        exceptionInfo[2] = "    Location(Class): \n      " + exception.getClass().getName();
        exceptionInfo[3] = "    Cause: \n      " + exception.getCause().toString();
        exceptionInfo[4] = "    StackTrace: \n      " + Arrays.toString(exception.getStackTrace());
        for (String text : exceptionInfo) {
            PLUGIN.getServer().getConsoleSender().sendMessage("§c" + text);
        }
    }

    /**
     * Send a message to server console as an alert.
     * @param text String - The message you want to send.
     */
    public static void sendAlert(String text) {
        PLUGIN.getServer().getConsoleSender().sendMessage("§e" + text);
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
        info[0] = "  Oops! There are exception was thrown!";
        info[1] = "    Message: " + e.getMessage();
        info[2] = "    Location(Class): " + e.getClass().getName();
        info[3] = "    Cause: " + e.getCause().toString();
        info[4] = "    StackTrace: " + Arrays.toString(e.getStackTrace());
        return info;
    }
}
