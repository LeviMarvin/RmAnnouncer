package net.rmplugins.rmannouncer.core.cron.task;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

import static net.rmplugins.rmannouncer.core.util.SenderUtil.translateString;
import static net.rmplugins.rmannouncer.data.plugin.Main.PLUGIN;
import static net.rmplugins.rmannouncer.data.plugin.Main.chatTexts;
import static net.rmplugins.rmannouncer.core.util.SenderUtil.sendChat;
import static net.rmplugins.rmannouncer.util.JsonUtil.isJson;
import static net.rmplugins.rmannouncer.util.JsonUtil.toTextJson;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class ChatSender extends BukkitRunnable {
    private static ChatSender sender;
    public static ChatSender self() {
        if (sender == null) {
            sender = new ChatSender();
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
        int textsMax = chatTexts.size();
        // Get original json text.
        String jsonText = chatTexts.get(textIndex);
        if (!isJson(jsonText)) {
            jsonText = toTextJson(jsonText);
        }

        for (Player player : players) {
            // Translate Text's value.
            String finalText = translateString(player, jsonText);
            // Send the final text.
            sendChat(player, finalText);
        }
        if (textIndex == textsMax - 1){
            textIndex = 0;
        }
        textIndex++;
    }

    public boolean isRunning() {
        return this.isRunning;
    }
}
