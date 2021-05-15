package net.rmplugins.rmannouncer.util;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class ReflectUtil {
    public static final String VERSION = ServerUtil.getServerVersion();

    public static Class<?> getNmsClass(String name) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + VERSION + "." + name);
    }

    public static Class<?> getObcClass(String name) throws ClassNotFoundException {
        return Class.forName("org.bukkit.craftbukkit." + VERSION + "." + name);
    }

    public static Class<?> getObClass(String pathAndName) throws ClassNotFoundException {
        return Class.forName("org.bukkit." + pathAndName);
    }
}
