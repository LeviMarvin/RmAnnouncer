package net.rmplugins.rmannouncer.data.plugin;

import net.rmplugins.rmannouncer.RmAnnouncer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class Main {
    public static final JavaPlugin PLUGIN = RmAnnouncer.getSelf();
    public static String configPath;
    public static String logPath;
    public static String msgPrefix;

    public static int chatInterval = 1;
    public static int titleInterval = 1;
    public static int actionbarInterval = 1;
    public static int bossbarInterval = 1;

    /**
     * PLEASE MAKE SURE ALL OF TEXTS MUST BE INITIATED!
     */
    public static List<String> chatTexts;
    public static List<String> titleTexts;
    public static List<String> actionbarTexts;
    public static List<String> bossbarTexts;
}
