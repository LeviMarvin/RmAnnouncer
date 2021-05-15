package net.rmplugins.rmannouncer.data.plugin;

import net.rmplugins.rmannouncer.RmAnnouncer;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

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
    public static final String PREFIX = "§6§l[§e§nRAC§6§l]§f§r ";
    public static final UUID UUID = java.util.UUID.fromString("7af33e4e-b4c7-11eb-85ab-0a80ff2603de");

    public static String msgPrefix;
    public static String i18n = "en_US";
    public static String dataPath = PLUGIN.getDataFolder().getPath();
    public static String logPath = PLUGIN.getDataFolder().getPath();

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
    public static List<String> subTitleTexts;
    public static List<String> actionbarTexts;
    public static List<String> bossbarTexts;

    public static BarColor barColor;
    public static BarStyle barStyle;
}
