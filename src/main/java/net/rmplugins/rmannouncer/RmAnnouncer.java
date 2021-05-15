package net.rmplugins.rmannouncer;

import net.rmplugins.rmannouncer.core.cron.TaskManager;
import net.rmplugins.rmannouncer.core.cron.task.ActionBarSender;
import net.rmplugins.rmannouncer.core.cron.task.BossBarSender;
import net.rmplugins.rmannouncer.core.cron.task.ChatSender;
import net.rmplugins.rmannouncer.core.cron.task.TitleSender;
import net.rmplugins.rmannouncer.data.Data;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;

import static net.rmplugins.rmannouncer.data.plugin.Main.*;
import static net.rmplugins.rmannouncer.util.MessageUtil.sendAlert;
import static net.rmplugins.rmannouncer.util.MessageUtil.sendMsg;
import static net.rmplugins.rmannouncer.data.plugin.Extension.*;

/**
 * @author Levi Marvin
 * @version 1.0
 */
public final class RmAnnouncer extends JavaPlugin {
    private static RmAnnouncer PLUGIN;
    public static RmAnnouncer self() {
        return PLUGIN;
    }
    private PluginLogger logger;

    @Override
    public void onEnable() {
        PLUGIN = this;
        // Init config.
        // TODO: init config
        this.saveDefaultConfig();
        // Init server data.
        Data.self().init();
        // Init extensions.
        this.initExtension();
        // Run senders.
        this.runSender();
    }

    @Override
    public void onDisable() {
        sendMsg("Thanks for using. See you next time.");
    }

    public void onReload() {
        reloadConfig();
        Data.self().reload();
        runSender();
    }

    public PluginLogger getPluginLogger() {
        return this.logger;
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
}
