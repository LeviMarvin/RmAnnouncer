package net.rmplugins.rmannouncer.core.command.cmd;

import net.rmplugins.rmannouncer.RmAnnouncer;
import net.rmplugins.rmannouncer.core.command.STRING;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static net.rmplugins.rmannouncer.data.plugin.Main.PLUGIN;
import static net.rmplugins.rmannouncer.data.plugin.Main.UUID;
import static net.rmplugins.rmannouncer.data.plugin.Main.VERSION;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class Main implements CommandExecutor {
    CommandSender sender;
    Command command;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.sender = sender;
        this.command = command;
        if (command.getName().equals("rma") || command.getAliases().contains("rma")) {
            if (args.length == 0) {
                return welcome();
            }else {
                String subCmd = args[0];
                switch (subCmd) {
                    case "":
                        return welcome();
                    case "reload":
                        return reload();
                    case "about":
                        return about();
                    default:
                        sender.sendMessage(STRING.CommandError);
                        return false;
                }
            }
        }else {
            sender.sendMessage(STRING.NoSuchCommand);
            return false;
        }
    }

    private boolean reload() {
        sender.sendMessage("§a Reloading...");
        ((RmAnnouncer) PLUGIN).onReload();
        sender.sendMessage("§a Reload successfully!");
        return true;
    }

    private boolean welcome() {
        String[] text = new String[5];
        text[0] = net.rmplugins.rmannouncer.data.plugin.Main.PREFIX + "§6+-==============-[RmAnnouncer]-==============-+\n";
        text[1] = net.rmplugins.rmannouncer.data.plugin.Main.PREFIX + "§e            Welcome to RmAnnouncer!            \n";
        text[2] = net.rmplugins.rmannouncer.data.plugin.Main.PREFIX + "§e            Get help by: /rma help             \n";
        text[3] = net.rmplugins.rmannouncer.data.plugin.Main.PREFIX + "§a   See details at: https://rma.rmplugins.net   \n";
        text[4] = net.rmplugins.rmannouncer.data.plugin.Main.PREFIX + "§6+-==============-[RmAnnouncer]-==============-+\n";
        sender.sendMessage(text);
        return true;
    }

    private boolean about() {
        if (command.getName().equals("rac") || command.getAliases().contains("rac")) {
            String[] text = new String[5];
            text[0] = net.rmplugins.rmannouncer.data.plugin.Main.PREFIX + "§6+-==============-[RmAnnouncer]-==============-+" + "\n";
            text[1] = net.rmplugins.rmannouncer.data.plugin.Main.PREFIX + "§a Author: Levi_Marvin" +                            "\n";
            text[2] = net.rmplugins.rmannouncer.data.plugin.Main.PREFIX + "§a Version: " + VERSION +                            "\n";
            text[3] = net.rmplugins.rmannouncer.data.plugin.Main.PREFIX + "§a UUID: " + UUID +                                  "\n";
            text[4] = net.rmplugins.rmannouncer.data.plugin.Main.PREFIX + "§6+-==============-[RmAnnouncer]-==============-+" + "\n";
            sender.sendMessage(text);
            return true;
        }else {
            sender.sendMessage(STRING.NoSuchCommand);
            return false;
        }
    }

}
