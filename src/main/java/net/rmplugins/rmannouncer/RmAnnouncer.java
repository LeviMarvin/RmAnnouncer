package net.rmplugins.rmannouncer;

import net.rmplugins.rmannouncer.core.command.cmd.Cli;
import net.rmplugins.rmannouncer.core.command.cmd.Main;
import net.rmplugins.rmannouncer.core.cron.TaskManager;
import net.rmplugins.rmannouncer.core.cron.task.ActionBarSender;
import net.rmplugins.rmannouncer.core.cron.task.BossBarSender;
import net.rmplugins.rmannouncer.core.cron.task.ChatSender;
import net.rmplugins.rmannouncer.core.cron.task.TitleSender;
import net.rmplugins.rmannouncer.data.Data;
import net.rmplugins.rmannouncer.data.plugin.i18n.I18nLoader;
import net.rmplugins.rmannouncer.data.plugin.info.ReleaseLoader;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

import static net.rmplugins.rmannouncer.data.plugin.Main.*;
import static net.rmplugins.rmannouncer.data.plugin.Extension.*;
import static net.rmplugins.rmannouncer.util.MessageUtil.*;
import static net.rmplugins.rmannouncer.data.io.LocalFile.configFile;

/**
 * @author Levi Marvin
 * @version 1.0
 */
public final class RmAnnouncer extends JavaPlugin {
    private static RmAnnouncer PLUGIN;
    public static RmAnnouncer self() {
        return PLUGIN;
    }

    ReleaseLoader loader;
    I18nLoader i18nLoader;

    @Override
    public void onLoad() {
        // Init release information.
        try {
            loader = new ReleaseLoader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        PLUGIN = this;
        welcomeInfo[0] = PREFIX + "§6+-==============-[RmAnnouncer]-==============-+";
        welcomeInfo[1] = PREFIX + "§e            Welcome to RmAnnouncer!            ";
        welcomeInfo[2] = PREFIX + "§e            Get help by: /rac help             ";
        welcomeInfo[3] = PREFIX + "§e     Get help by: /rac help or /rac about      ";
        welcomeInfo[4] = PREFIX + "§a   See details at: https://rac.rmplugins.net   ";
        welcomeInfo[5] = PREFIX + "§6+-==============-[RmAnnouncer]-==============-+";
        sendMsg("Loading...");
        // Init config.
        sendMsg("  Saving resource...");
        this.saveResourceFile("config.yml", false);
        this.saveResourceFile("lang\\template.lang", true);
        this.saveResourceFile("lang\\en_US.lang", false);
        this.saveResourceFile("lang\\zh_CN.lang", false);
        this.saveResourceFile("message\\chat.yml", false);
        this.saveResourceFile("message\\title.yml", false);
        this.saveResourceFile("message\\actionbar.yml", false);
        this.saveResourceFile("message\\bossbar.yml", false);
        // Init server data.
        sendMsg("  Loading data...");
        Data.self().init();
        // Init i18n support.
        i18n = configFile.getString("lang");
        try {
            i18nLoader = new I18nLoader(i18n);
        } catch (IOException e) {
            e.printStackTrace();
        }
        i18nFile = i18nLoader.getProp();
        i18nAuthor = i18nFile.getProperty("ro.base.author");
        sendMsg("    Loading language...[" + net.rmplugins.rmannouncer.data.plugin.Main.i18n + "]");
        // Init extensions.
        sendMsg("  Hooking third-party plugin...");
        this.initExtension();
        // Init commands.
        sendMsg("  Initializing command...");
        this.initCommand();
        // Run senders.
        this.runSender();
        sendMsg(i18nFile.getProperty("ro.base.welcome"));
    }

    private void initCommand() {
        if (Bukkit.getPluginCommand("rac") != null) {
            Bukkit.getPluginCommand("rac").setExecutor(new Main());
        }
        if (Bukkit.getPluginCommand("rac-cli") != null) {
            Bukkit.getPluginCommand("rac-cli").setExecutor(new Cli());
        }
    }

    @Override
    public void onDisable() {
        stopSender();
        sendMsg("§aThanks for using. See you next time.");
    }

    public void onReload() {
        stopSender();
        onLoad();
        onEnable();
    }

    private void runSender() {
        stopSender();
        if (isEnableChat) {
            TaskManager.getManager().runTask(this, ChatSender.self(), chatInterval);
        }
        if (isEnableTitle) {
            TaskManager.getManager().runTask(this, TitleSender.self(), titleInterval);
        }
        if (isEnableActionBar) {
            TaskManager.getManager().runTask(this, ActionBarSender.self(), actionbarInterval);
        }
        if (isEnableBossBar) {
            TaskManager.getManager().runTask(this, BossBarSender.self(), bossbarInterval);
        }
    }

    private void stopSender() {
        if (ChatSender.self().isRunning()) {
            TaskManager.getManager().stopTask(ChatSender.self());
        }
        if (TitleSender.self().isRunning()) {
            TaskManager.getManager().stopTask(TitleSender.self());
        }
        if (ActionBarSender.self().isRunning()) {
            TaskManager.getManager().stopTask(ActionBarSender.self());
        }
        if (BossBarSender.self().isRunning()) {
            TaskManager.getManager().stopTask(BossBarSender.self());
        }
    }

    private void initExtension() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            PAPI_support = true;
            sendMsg("Enable the support of PlaceholderAPI successfully!");
        } else {
            sendAlert("Could not find or load PlaceholderAPI! Will not enable the support of PlaceholderAPI.");
        }
    }

    private void saveResourceFile(String resourcePath, boolean replace) {
        if (resourcePath == null || resourcePath.equals("")) {
            throw new IllegalArgumentException("ResourcePath cannot be null or empty");
        }

        resourcePath = resourcePath.replace('\\', '/');
        InputStream in = getResource(resourcePath);
        if (in == null) {
            throw new IllegalArgumentException("The embedded resource '" + resourcePath + "' cannot be found in this plugins package.");
        }

        File outFile = new File(this.getDataFolder(), resourcePath);
        int lastIndex = resourcePath.lastIndexOf('/');
        File outDir = new File(this.getDataFolder(), resourcePath.substring(0, Math.max(lastIndex, 0)));

        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        try {
            if (!outFile.exists() || replace) {
                OutputStream out = new FileOutputStream(outFile);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.close();
                in.close();
            }
        } catch (IOException ex) {
            sendError("Could not save " + outFile.getName() + " to " + outFile);
        }
    }

    public boolean welcome(Player p) {
        p.sendMessage(welcomeInfo);
        return true;
    }

    public ReleaseLoader getRLoader() {
        return loader;
    }
}
