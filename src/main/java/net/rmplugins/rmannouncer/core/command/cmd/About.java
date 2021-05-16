package net.rmplugins.rmannouncer.core.command.cmd;

import net.rmplugins.rmannouncer.core.command.STRING;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class About implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("rma") || command.getAliases().contains("rma")) {
            //
            return true;
        }else {
            sender.sendMessage(STRING.NoSuchCommand);
            return false;
        }
    }
}
