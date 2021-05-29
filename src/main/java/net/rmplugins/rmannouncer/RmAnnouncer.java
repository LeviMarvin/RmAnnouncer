package net.rmplugins.rmannouncer;

import net.rmplugins.rmannouncer.core.command.cmd.Cli;
import net.rmplugins.rmannouncer.core.command.cmd.Main;
import net.rmplugins.rmannouncer.core.cron.TaskManager;
import net.rmplugins.rmannouncer.core.cron.task.ActionBarSender;
import net.rmplugins.rmannouncer.core.cron.task.BossBarSender;
import net.rmplugins.rmannouncer.core.cron.task.ChatSender;
import net.rmplugins.rmannouncer.core.cron.task.TitleSender;
import net.rmplugins.rmannouncer.data.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

import static net.rmplugins.rmannouncer.data.plugin.Main.*;
import static net.rmplugins.rmannouncer.data.plugin.Extension.*;
import static net.rmplugins.rmannouncer.util.MessageUtil.*;

/**
 * @author Levi Marvin
 * @version 1.0
 */
public final class RmAnnouncer extends JavaPlugin {
    private static RmAnnouncer PLUGIN;
    public static RmAnnouncer self() {
        return PLUGIN;
    }

    @Override
    public void onLoad() {}

    @Override
    public void onEnable() {
        PLUGIN = this;
        sendMsg("Starting...");
        // Init config.
        sendMsg("  Saving resource...");
        this.saveResourceFile("config.yml", false);
        this.saveResourceFile("lang\\default.yml", true);
        this.saveResourceFile("lang\\en_US.yml", false);
        this.saveResourceFile("message\\chat.yml", false);
        this.saveResourceFile("message\\title.yml", false);
        this.saveResourceFile("message\\actionbar.yml", false);
        this.saveResourceFile("message\\bossbar.yml", false);
        // Init server data.
        sendMsg("  Loading data...");
        Data.self().init();
        // Init extensions.
        sendMsg("  Hooking third-party plugin...");
        this.initExtension();
        // Init commands.
        sendMsg("  Initializing command...");
        this.initCommand();
        // Run senders.
        this.runSender();
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
        sendMsg("§aThanks for using. See you next time.");
    }

    public void onReload() {
        sendMsg("Reloading config...");
        reloadConfig();
        sendMsg("Reloading data...");
        Data.self().reinit();
        runSender();
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
            ChatSender.self().cancel();
        }
        if (TitleSender.self().isRunning()) {
            TitleSender.self().cancel();
        }
        if (ActionBarSender.self().isRunning()) {
            ActionBarSender.self().cancel();
        }
        if (BossBarSender.self().isRunning()) {
            BossBarSender.self().cancel();
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
        File file = null;
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
        String[] text = new String[6];
        text[0] = PREFIX + "§6+-==============-[RmAnnouncer]-==============-+";
        text[1] = PREFIX + "§e            Welcome to RmAnnouncer!            ";
        text[2] = PREFIX + "§e            Get help by: /rac help             ";
        text[3] = PREFIX + "§e     Get help by: /rac info or /rac about      ";
        text[4] = PREFIX + "§a   See details at: https://rac.rmplugins.net   ";
        text[5] = PREFIX + "§6+-==============-[RmAnnouncer]-==============-+";
        p.sendMessage(text);
        return true;
    }
}
