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
    /**
     * PLEASE MAKE SURE ALL OF DATA MUST BE INITIATED!
     *
     * Time unit: Tick (1 second = 20 tick)
     */

    public static final JavaPlugin PLUGIN = RmAnnouncer.self();
    public static String configPath;
    public static String logPath;
    public static String msgPrefix;

    public static boolean isEnableChat = true;
    public static boolean isEnableTitle = true;
    public static boolean isEnableActionBar = true;
    public static boolean isEnableBossBar = true;

    public static int chatInterval = 20;
    public static int titleInterval = 20;
    public static int actionbarInterval = 20;
    public static int bossbarInterval = 20;

    public static int titleFadeIn;
    public static int titleStay;
    public static int titleFadeOut;

    public static List<String> chatTexts;
    public static List<String> titleTexts;
    public static List<String> actionbarTexts;
    public static List<String> bossbarTexts;
}
