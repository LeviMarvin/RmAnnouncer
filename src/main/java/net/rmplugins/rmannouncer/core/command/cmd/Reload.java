package net.rmplugins.rmannouncer.core.command.cmd;

import net.rmplugins.rmannouncer.RmAnnouncer;
import net.rmplugins.rmannouncer.core.annotation.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static net.rmplugins.rmannouncer.data.plugin.Main.PLUGIN;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
@Permission(permission = "pxam.cmd.reload")
public class Reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("§a Reloading...");
        ((RmAnnouncer) PLUGIN).onReload();
        sender.sendMessage("§a Reload successfully!");
        return true;
    }
}
