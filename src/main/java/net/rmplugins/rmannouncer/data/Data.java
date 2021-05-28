package net.rmplugins.rmannouncer.data;

import net.rmplugins.rmannouncer.data.io.LocalFile;
import net.rmplugins.rmannouncer.data.plugin.Main;
import net.rmplugins.rmannouncer.data.server.nms.NmsClass;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Locale;

import static net.rmplugins.rmannouncer.data.plugin.Main.dataPath;
import static net.rmplugins.rmannouncer.data.plugin.Main.i18n;
import static net.rmplugins.rmannouncer.util.MessageUtil.sendError;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class Data {
    private static Data data;
    public static Data self(){
        if (data == null) {
            data = new Data();
        }
        return data;
    }

    /**
     * !IF YOU NEED TO USE THE CLASS OF THIS PACKAGE!
     * !YOU MUST RUN THIS FUNCTION AT FIRST!
     *
     * Init classes witch under this package.
     * If plugin need reload, I suggested you implement this method.
     */
    public void init() {
        // Initialize NMS Classes.
        NmsClass.getNms().init();
        // Initialize files.
        try {
            LocalFile.configFile = YamlConfiguration.loadConfiguration(new File(dataPath, "config.yml"));
            LocalFile.i18nFile = YamlConfiguration.loadConfiguration(new File(dataPath, "language\\" + i18n + ".yml"));
            LocalFile.chatTextFile = YamlConfiguration.loadConfiguration(new File(dataPath, "message\\" + "chat.yml"));
            LocalFile.titleTextFile = YamlConfiguration.loadConfiguration(new File(dataPath, "message\\" + "title.yml"));
            LocalFile.actionBarTextFile = YamlConfiguration.loadConfiguration(new File(dataPath, "message\\" + "actionbar.yml"));
            LocalFile.bossBarTextFile = YamlConfiguration.loadConfiguration(new File(dataPath, "message\\" + "bossbar.yml"));

            Main.isEnableChat = LocalFile.configFile.getBoolean("chat.enable");
            Main.chatInterval = LocalFile.configFile.getInt("chat.interval");
            Main.chatTexts = LocalFile.chatTextFile.getStringList("text");

            Main.isEnableTitle = LocalFile.configFile.getBoolean("title.enable");
            Main.titleInterval = LocalFile.configFile.getInt("title.interval");
            Main.titleFadeIn = LocalFile.configFile.getInt("title.fadein");
            Main.titleStay = LocalFile.configFile.getInt("title.stay");
            Main.titleFadeOut = LocalFile.configFile.getInt("title.fadeout");
            Main.titleTexts = LocalFile.titleTextFile.getStringList("text.main");
            Main.subTitleTexts = LocalFile.titleTextFile.getStringList("text.sub");

            Main.isEnableActionBar = LocalFile.configFile.getBoolean("actionbar.enable");
            Main.actionbarInterval = LocalFile.configFile.getInt("actionbar.interval");
            Main.actionbarTexts = LocalFile.actionBarTextFile.getStringList("text");

            Main.isEnableBossBar = LocalFile.configFile.getBoolean("bossbar.enable");
            Main.bossbarTexts = LocalFile.bossBarTextFile.getStringList("text");
            Main.barColor = getBarColor(LocalFile.bossBarTextFile.getString("bossbar.color"));
            Main.barStyle = getBarStyle(LocalFile.bossBarTextFile.getInt("bossbar.style"));
            Main.barFlag = getBarFlag(LocalFile.bossBarTextFile.getString("bossbar.flag"));
        }catch (Exception e) {
            sendError(e);
        }
    }

    public void reload() {
        // Reinitialize NMS Classes.
        NmsClass.getNms().reload();
        // Reinitialize files.
        try {
            LocalFile.configFile = YamlConfiguration.loadConfiguration(new File(dataPath, "config.yml"));
            LocalFile.i18nFile = YamlConfiguration.loadConfiguration(new File(dataPath, "language\\" + i18n + ".yml"));
            LocalFile.chatTextFile = YamlConfiguration.loadConfiguration(new File(dataPath, "message\\" + "chat.yml"));
            LocalFile.titleTextFile = YamlConfiguration.loadConfiguration(new File(dataPath, "message\\" + "title.yml"));
            LocalFile.actionBarTextFile = YamlConfiguration.loadConfiguration(new File(dataPath, "message\\" + "actionbar.yml"));
            LocalFile.bossBarTextFile = YamlConfiguration.loadConfiguration(new File(dataPath, "message\\" + "bossbar.yml"));
        }catch (Exception e) {
            sendError(e);
        }
    }

    private BarColor getBarColor(String color) {
        switch (color.toLowerCase(Locale.ROOT)) {
            case "red":
                return BarColor.RED;
            case "blue":
                return BarColor.BLUE;
            case "pink":
                return BarColor.PINK;
            case "green":
                return BarColor.GREEN;
            case "yellow":
                return BarColor.YELLOW;
            case "white":
                return BarColor.WHITE;
            default:
                return BarColor.PURPLE;
        }
    }

    private BarStyle getBarStyle(int style) {
        switch (style) {
            case 1:
                return BarStyle.SOLID;
            case 6:
                return BarStyle.SEGMENTED_6;
            case 10:
                return BarStyle.SEGMENTED_10;
            case 20:
                return BarStyle.SEGMENTED_20;
            default:
                return BarStyle.SEGMENTED_12;
        }
    }

    private BarFlag getBarFlag(String flag) {
        switch (flag) {
            case "DARKEN_SKY":
                return BarFlag.DARKEN_SKY;
            case "CREATE_FOG":
                return BarFlag.CREATE_FOG;
            case "PLAY_BOSS_MUSIC":
                return BarFlag.PLAY_BOSS_MUSIC;
            default:
                return null;
        }
    }
}
