package net.rmplugins.rmannouncer.core.command;

import net.rmplugins.rmannouncer.core.annotation.Commander;
import net.rmplugins.rmannouncer.core.annotation.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class CommandExecutor implements org.bukkit.command.CommandExecutor{
    private final org.bukkit.command.CommandExecutor COMMAND_EXECUTOR;

    public CommandExecutor(org.bukkit.command.CommandExecutor CE) {
        this.COMMAND_EXECUTOR = CE;
    }

    /**
     * Executes the given command, returning its success
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Permission permission = COMMAND_EXECUTOR.getClass().getAnnotation(Permission.class);
        Commander commander = COMMAND_EXECUTOR.getClass().getAnnotation(Commander.class);
        if (sender.hasPermission(permission.permission())) {
            //TODO: THERE MAYBE HAVE SOME BUGS!
            switch (commander.only()) {
                case "console":
                    if (sender instanceof Player) {
                        sender.sendMessage("Only the CONSOLE can execute this!");
                        break;
                    }
                    COMMAND_EXECUTOR.onCommand(sender, command, label, args);
                    break;
                case "player":
                    if (sender instanceof Player) {
                        sender.sendMessage("Only the PLAYER can execute this!");
                        break;
                    }
                    COMMAND_EXECUTOR.onCommand(sender, command, label, args);
                    break;
                default:
                    COMMAND_EXECUTOR.onCommand(sender, command, label, args);
            }
        } else {
            sender.sendMessage("§cYou have not permission to do this!");
        }
        return true;
    }
}
