package net.rmplugins.rmannouncer.core.cron;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Please use this to control and manage all tasks and senders!
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

    /**
     * Start a bukkit task.
     *
     * @param plugin +JavaPlugin+ The plugin instance to run the task.
     * @param task +BukkitRunnable+ The task to be run.
     * @param period +int+ The task period.
     */
    public void runTask(JavaPlugin plugin, BukkitRunnable task, long period) {
        try {
            task.runTaskTimer(plugin, 1L, period);
        }catch (java.lang.IllegalArgumentException | java.lang.IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stop a bukkit task.
     *
     * @param task +BukkitRunnable+ The task to be stopped.
     */
    public void stopTask(BukkitRunnable task) {
        try {
            task.cancel();
        }catch (java.lang.IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
