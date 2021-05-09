package net.rmplugins.rmannouncer.core.cron.task;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

import static net.rmplugins.rmannouncer.core.util.SenderUtil.sendActionBar;
import static net.rmplugins.rmannouncer.core.util.StringUtil.*;
import static net.rmplugins.rmannouncer.data.plugin.Main.PLUGIN;
import static net.rmplugins.rmannouncer.data.plugin.Main.actionbarTexts;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class ActionBarSender extends BukkitRunnable {
    private static ActionBarSender sender;
    public static ActionBarSender self() {
        if (sender == null) {
            sender = new ActionBarSender();
        }
        return sender;
    }
    private boolean isRunning = false;
    int textIndex = 0;

    @Override
    public void run() {
        this.isRunning = true;
        send();
    }

    private void send() {
        // Get online players.
        Collection<? extends Player> players = PLUGIN.getServer().getOnlinePlayers();
        // Get texts' max size.
        int textsMax = actionbarTexts.size();
        // Get original json text.
        String jsonText = actionbarTexts.get(textIndex);
        // Get text's value.
        String text = getKeyValue(jsonText, "text");

        for (Player player : players) {
            // Translate Text's value.
            String translatedText = translateString(player, text);
            // Set translated text to jsonObject.
            String finalJsonText = setKeyValue(jsonText, "text", translatedText);
            sendActionBar(player, finalJsonText);
        }
        if (textIndex == textsMax){
            textIndex = 0;
        }
        textIndex++;
    }

    public boolean isRunning() {
        return this.isRunning;
    }
}
