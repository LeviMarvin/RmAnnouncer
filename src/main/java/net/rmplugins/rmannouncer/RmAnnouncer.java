package net.rmplugins.rmannouncer;

import net.rmplugins.rmannouncer.core.cron.TaskManager;
import net.rmplugins.rmannouncer.core.cron.task.ActionBarSender;
import net.rmplugins.rmannouncer.core.cron.task.ChatSender;
import net.rmplugins.rmannouncer.core.cron.task.TitleSender;
import org.bukkit.Bukkit;
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
    private static JavaPlugin PLUGIN;
    public static JavaPlugin self() {
        return PLUGIN;
    }

    @Override
    public void onEnable() {
        PLUGIN = this;
        // Init config.
        // TODO
        // Init extensions.
        initExtension();
        // Run senders.
        runSender();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void onReload() {
        runSender();
    }

    private void runSender() {
        if (isEnableChat) {
            TaskManager.getManager().runTask(this, ChatSender.self(), chatInterval);
        }
        if (isEnableTitle) {
            TaskManager.getManager().runTask(this, TitleSender.self(), chatInterval);
        }
        if (isEnableActionBar) {
            TaskManager.getManager().runTask(this, ActionBarSender.self(), chatInterval);
        }
        if (isEnableBossBar) {
            //TaskManager.getManager().runTask(this, BossBarSender.self(), chatInterval);
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
        //if (BossBarSender.self().isRunning()) {}
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
