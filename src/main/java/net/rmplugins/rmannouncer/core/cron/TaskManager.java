package net.rmplugins.rmannouncer.core.cron;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import static net.rmplugins.rmannouncer.util.MessageUtil.sendError;

/**
 * Please use this to control and manager senders!
 *
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class TaskManager {
    private static TaskManager manager;
    public static TaskManager getManager() {
        if (manager == null) {
            manager = new TaskManager();
        }
        return manager;
    }

    public void runTask(JavaPlugin plugin, BukkitRunnable task, long period) {
        try {
            task.runTaskTimer(plugin, 1L, period);
        }catch (java.lang.IllegalArgumentException | java.lang.IllegalStateException e) {
            sendError(e);
        }
    }

    public void stopTask(BukkitRunnable task) {
        try {
            task.cancel();
        }catch (java.lang.IllegalStateException e) {
            sendError(e);
        }
    }
}
