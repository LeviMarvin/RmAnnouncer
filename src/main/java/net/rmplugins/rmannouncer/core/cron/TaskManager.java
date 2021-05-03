package net.rmplugins.rmannouncer.core.cron;

import org.bukkit.scheduler.BukkitRunnable;

import static net.rmplugins.rmannouncer.data.plugin.Main.PLUGIN;
import static net.rmplugins.rmannouncer.util.MessageUtil.sendError;

/**
 * Please use this to control and manager senders!
 *
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class TaskManager {
    @Deprecated
    public void start(Class<BukkitRunnable> taskClass, int interval) {
        try {
            PLUGIN.getServer().getScheduler().scheduleSyncRepeatingTask(PLUGIN, taskClass.newInstance(), 1, interval);
        } catch (InstantiationException | IllegalAccessException e) {
            sendError(e);
        }
    }

    @Deprecated
    public void stop(type type) {}

    public enum type{
        CHAT, TITLE, ACTIONBAR, BOSSBAR
    }
}
