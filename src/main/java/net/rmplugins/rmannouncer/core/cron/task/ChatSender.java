package net.rmplugins.rmannouncer.core.cron.task;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

import static net.rmplugins.rmannouncer.core.util.StringUtil.*;
import static net.rmplugins.rmannouncer.data.plugin.Main.PLUGIN;
import static net.rmplugins.rmannouncer.data.plugin.Main.chatTexts;
import static net.rmplugins.rmannouncer.core.util.SenderUtil.sendChat;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class ChatSender extends BukkitRunnable {
    private boolean isRunning = false;

    @Override
    public void run() {
        this.isRunning = true;
        send();
    }

    private void send() {
        int textIndex = 0;
        // Get online players.
        Collection<? extends Player> players = PLUGIN.getServer().getOnlinePlayers();
        // Get texts' max size.
        int textMax = chatTexts.size();
        // Get original json text.
        String jsonText = chatTexts.get(textIndex);
        // Get text's value.
        String text = getKeyValue(jsonText, "text");

        for (Player player : players) {
            // Translate Text's value.
            String translatedText = translateString(player, text);
            // Set translated text to jsonObject.
            String finalJsonText = setKeyValue(jsonText, "text", translatedText);
            sendChat(player, finalJsonText);
        }
    }

    public boolean isRunning() {
        return this.isRunning;
    }
}
