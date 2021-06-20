package net.rmplugins.rmannouncer.util;

import static net.rmplugins.rmannouncer.data.plugin.Main.PLUGIN;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class ServerUtil {
    /**
     * Get the full version of the server.
     * Include major, minor, revision.
     *
     * @return +String[]+ The servers major version.
     */
    public static String getServerVersion() {
        String[] versions = PLUGIN.getServer().getBukkitVersion().split("\\.");
        String major = versions[0];
        String minor = versions[1];
        String revision = "-1";
        String NMSBaseHead = "net.minecraft.server.v" + major + "_" + minor + "_R";
        for (int i = 1; i <= 9; i++) {
            String versionTest = NMSBaseHead + i;
            try {
                Class.forName(versionTest + ".ItemStack");
                revision = i + "";
                break;
            } catch (ClassNotFoundException ignored) {}
        }
        return "v" + major + "_" + minor + "_R"+ revision;
    }

    /**
     * Get the major version of the server.
     * @deprecated
     * @return +int+ The servers major version.
     */
    public static int getMajorVersion() {
        String[] versions = PLUGIN.getServer().getBukkitVersion().split("\\.");
        String major = versions[0];
        return Integer.getInteger(major);
    }

    /**
     * Get the minor version of the server.
     * @deprecated
     * @return +int+ The servers minor version.
     */
    public static int getMinorVersion() {
        String[] versions = PLUGIN.getServer().getBukkitVersion().split("\\.");
        String minor = versions[1];
        return Integer.getInteger(minor);
    }
}
