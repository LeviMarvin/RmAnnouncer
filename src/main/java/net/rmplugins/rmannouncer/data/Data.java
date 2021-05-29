package net.rmplugins.rmannouncer.data;

import net.rmplugins.rmannouncer.data.io.LocalFile;
import net.rmplugins.rmannouncer.data.plugin.Main;
import net.rmplugins.rmannouncer.data.server.nms.NmsClass;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static net.rmplugins.rmannouncer.data.plugin.Main.dataPath;
import static net.rmplugins.rmannouncer.util.MessageUtil.sendMsg;

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
        sendMsg("    Loading NMS...");
        NmsClass.getNms().init();
        LocalFile.configFile = YamlConfiguration.loadConfiguration(new File(dataPath, "config.yml"));
        // Load files.
        sendMsg("    Loading file...");
        loadFiles();
        // Load variables
        sendMsg("    Loading config...");
        loadVariables();
        // Load language file;
        loadI18nFile();
        sendMsg("    Loading language...[" + Main.i18n + "]");
    }

    public void reinit() {
        // Re-initialize NMS Classes.
        sendMsg("    Reloading NMS...");
        NmsClass.getNms().init();
        LocalFile.configFile = YamlConfiguration.loadConfiguration(new File(dataPath, "config.yml"));
        // Reload files.
        sendMsg("    Reloading file...");
        loadFiles();
        // Reload variables
        sendMsg("    Reloading config...");
        loadVariables();
        // Reload language file;
        sendMsg("    Reloading language...");
        loadI18nFile();
    }

    private void loadFiles() {
        LocalFile.chatTextFile = YamlConfiguration.loadConfiguration(new File(dataPath, "message\\" + "chat.yml"));
        LocalFile.titleTextFile = YamlConfiguration.loadConfiguration(new File(dataPath, "message\\" + "title.yml"));
        LocalFile.actionBarTextFile = YamlConfiguration.loadConfiguration(new File(dataPath, "message\\" + "actionbar.yml"));
        LocalFile.bossBarTextFile = YamlConfiguration.loadConfiguration(new File(dataPath, "message\\" + "bossbar.yml"));
    }

    private void loadI18nFile() {
        LocalFile.i18nFile = YamlConfiguration.loadConfiguration(new File(dataPath, "lang\\" + Main.i18n + ".yml"));
        Main.i18nAuthor  = LocalFile.i18nFile.getString("author");
    }

    private void loadVariables() {
        Main.i18n = LocalFile.configFile.getString("lang");
        Main.msgPrefix = LocalFile.configFile.getString("prefix");

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
        Main.barColor = getBarColor(LocalFile.configFile.getString("bossbar.color"));
        Main.barStyle = getBarStyle(LocalFile.configFile.getInt("bossbar.style"));
        Main.barFlag = getBarFlag(LocalFile.configFile.getString("bossbar.flag"));
        Main.bossbarTexts = LocalFile.bossBarTextFile.getStringList("text");
    }

    private BarColor getBarColor(String color) {
        if (color == null) {
            color = "PURPLE";
        }
        switch (color) {
            case "RED":
                return BarColor.RED;
            case "BLUE":
                return BarColor.BLUE;
            case "PINK":
                return BarColor.PINK;
            case "GREEN":
                return BarColor.GREEN;
            case "YELLOW":
                return BarColor.YELLOW;
            case "WHITE":
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
        if (flag == null) {
            flag = "NULL";
        }
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
