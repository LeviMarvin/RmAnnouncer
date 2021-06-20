package net.rmplugins.rmannouncer.util;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class ReflectUtil {
    public static final String VERSION = ServerUtil.getServerVersion();

    /**
     * Get the class belong to the "net.minecraft.server" package.
     *
     * @param name +String+ The name of class what you want. (Include path)
     * @return Class<?> -
     */
    public static Class<?> getNmsClass(String name) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + VERSION + "." + name);
    }
}
