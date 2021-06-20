package net.rmplugins.rmannouncer.data.plugin;

import net.rmplugins.rmannouncer.RmAnnouncer;
import net.rmplugins.rmannouncer.data.plugin.info.ReleaseLoader;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Properties;
import java.util.UUID;

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
    public static final ReleaseLoader LOADER = RmAnnouncer.self().getRLoader();
    public static final String PREFIX = "§6§l[§e§nRAC§6§l]§f§r ";
    public static final String VERSION = (String) LOADER.get("rm.build.version");
    public static final UUID UUID = java.util.UUID.fromString((String) LOADER.get("rm.build.token"));
    public static final String TIMESTAMP = (String) LOADER.get("rm.build.ts");
    public static final String SYMBOL = (String) LOADER.get("rm.build.symbol");
    public static final boolean ISDEV = Boolean.parseBoolean((String) LOADER.get("rm.build.dev"));

    public static final String[] welcomeInfo = new String[6];

    public static String msgPrefix = "[§a服务器君§f]";
    public static String i18n = "en_US";
    public static String i18nAuthor = "Levi Marvin";
    public static Properties i18nFile;
    public static String dataPath = PLUGIN.getDataFolder().getPath();
    //public static String logPath = PLUGIN.getDataFolder().getPath();

    public static boolean isEnableChat = false;
    public static boolean isEnableTitle = false;
    public static boolean isEnableActionBar = false;
    public static boolean isEnableBossBar = false;

    public static int chatInterval = 20;
    public static int titleInterval = 20;
    public static int actionbarInterval = 20;
    public static int bossbarInterval = 20;

    public static int titleFadeIn = 20;
    public static int titleStay = 20;
    public static int titleFadeOut = 20;

    public static List<String> chatTexts;
    public static List<String> titleTexts;
    public static List<String> subTitleTexts;
    public static List<String> actionbarTexts;
    public static List<String> bossbarTexts;

    public static BarColor barColor;
    public static BarStyle barStyle;
    public static BarFlag barFlag;
}
