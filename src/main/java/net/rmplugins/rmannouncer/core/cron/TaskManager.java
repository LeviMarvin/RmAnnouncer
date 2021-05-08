package net.rmplugins.rmannouncer.core.cron;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

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
    BukkitScheduler scheduler = PLUGIN.getServer().getScheduler();

    public void runTask(JavaPlugin plugin, BukkitRunnable task, long period) {
        task.runTaskTimer(plugin, 1L, period);
    }

    public void stopTask(BukkitRunnable task) {
        task.cancel();
    }
}
