package net.rmplugins.rmannouncer.data.plugin;

import net.rmplugins.rmannouncer.RmAnnouncer;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class Main {
    public static final JavaPlugin PLUGIN = RmAnnouncer.get();
    public static String configPath;
    public static String logPath;
    public static String msgPrefix;

    public static int titleInterval = 1;
    public static int actionbarInterval = 1;
    public static int bossbarInterval = 1;
}
