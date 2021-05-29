package net.rmplugins.rmannouncer.core.command.cmd;

import net.rmplugins.rmannouncer.RmAnnouncer;
import net.rmplugins.rmannouncer.core.command.STRING;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.rmplugins.rmannouncer.data.plugin.Main.*;

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
        if (args.length == 0) {
            return RmAnnouncer.self().welcome((Player) sender);
        }else if (args.length == 1){
            String subCmd = args[0];
            switch (subCmd) {
                case "":
                    return RmAnnouncer.self().welcome((Player) sender);
                case "reload":
                    return reload();
                case "about":
                    return about();
                default:
                    sender.sendMessage(STRING.CommandError);
                    return false;
            }
        }else {
            sender.sendMessage("§cCommand Error! Please check your input and options!");
            return false;
        }
    }

    private boolean reload() {
        sender.sendMessage("§a Reloading...");
        ((RmAnnouncer) PLUGIN).onReload();
        sender.sendMessage("§a Reload successfully!");
        return true;
    }

    private boolean about() {
        String[] text = new String[12];
        text[0] = PREFIX + "§6+-==============-[RmAnnouncer]-==============-+";
        text[1] = PREFIX + "§a Author: Levi_Marvin";
        text[2] = PREFIX + "§a Version: " + VERSION;
        if (i18nAuthor.equals("Levi_Marvin")){
            text[3] = PREFIX + "§a Language: " + i18n + " (Official)";
        }else {
            text[3] = PREFIX + "§a Language: " + i18n + "(" + i18nAuthor + ")";
        }
        text[4] = PREFIX + "§a UUID: " + UUID;
        text[5] = PREFIX + "§a ";
        text[6] = PREFIX + "§a ";
        text[7] = PREFIX + "§a ";
        text[8] = PREFIX + "§a ";
        text[9] = PREFIX + "§a ";
        text[10] = PREFIX + "§a ";
        text[11] = PREFIX + "§6+-==============-[RmAnnouncer]-==============-+";
        sender.sendMessage(text);
        return true;
    }

}
