package net.rmplugins.rmannouncer.data.server;

import net.rmplugins.rmannouncer.util.ServerUtil;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class SERVER {
    public static String serverVersion = ServerUtil.getServerVersion();
    public static int majorVersion = ServerUtil.getMajorVersion();
    public static int minorVersion = ServerUtil.getMinorVersion();
}
