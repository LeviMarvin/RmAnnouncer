package net.rmplugins.rmannouncer.core.command.cmd;

import net.rmplugins.rmannouncer.RmAnnouncer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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
            sender.sendMessage(welcomeInfo);
            return true;
        }else if (args.length == 1){
            String subCmd = args[0];
            switch (subCmd) {
                case "":
                    sender.sendMessage(welcomeInfo);
                    return true;
                case "reload":
                    return reload();
                case "about":
                    return about();
                default:
                    sender.sendMessage("§c" + _cmd_nf);
                    return false;
            }
        }else {
            sender.sendMessage("§c" + _cmd_err);
            return false;
        }
    }

    private boolean reload() {
        sender.sendMessage("§a" + _reloading);
        ((RmAnnouncer) PLUGIN).onReload();
        sender.sendMessage("§a" + _reload_suc);
        return true;
    }

    private boolean about() {
        String[] text = new String[12];
        text[0] = PREFIX + "§6+-==============-[RmAnnouncer]-==============-+";
        text[1] = PREFIX + "§a " + _author + ": Levi_Marvin";
        text[2] = PREFIX + "§a " + _version + ": " + VERSION;
        if (i18nAuthor.equals("Levi_Marvin")){
            text[3] = PREFIX + "§a " + _language + ": " + i18n + " (Official)";
        }else {
            text[3] = PREFIX + "§a " + _language + ": " + i18n + "(" + i18nAuthor + ")";
        }
        text[4] = PREFIX + "§a " + _uuid + ": " + UUID;
        text[5] = PREFIX + "§a " + _isDev + ": " + ISDEV;
        text[6] = PREFIX + "§a " + _timestamp + ": " + TIMESTAMP;
        text[7] = PREFIX + "§a " + _symbol + ": " + SYMBOL;
        text[8] = PREFIX + "§a ";
        text[9] = PREFIX + "§a ";
        text[10] = PREFIX + "§a ";
        text[11] = PREFIX + "§6+-==============-[RmAnnouncer]-==============-+";
        sender.sendMessage(text);
        return true;
    }

    String _cmd_nf = i18nFile.getProperty("cmd.base.notfound");
    String _cmd_err = i18nFile.getProperty("cmd.base.error");

    String _reloading = i18nFile.getProperty("cmd.reload.reloading");
    String _reload_suc = i18nFile.getProperty("cmd.reload.successfully");

    String _author = i18nFile.getProperty("cmd.about.author");
    String _version = i18nFile.getProperty("cmd.about.version");
    String _language = i18nFile.getProperty("cmd.about.language");
    String _uuid = i18nFile.getProperty("cmd.about.uuid");
    String _isDev = i18nFile.getProperty("cmd.about.isdev");
    String _timestamp = i18nFile.getProperty("cmd.about.timestamp");
    String _symbol = i18nFile.getProperty("cmd.about.symbol");

}
