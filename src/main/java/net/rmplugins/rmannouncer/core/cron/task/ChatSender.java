package net.rmplugins.rmannouncer.core.cron.task;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

import static net.rmplugins.rmannouncer.data.plugin.Main.PLUGIN;
import static net.rmplugins.rmannouncer.data.plugin.ChatSender.*;
import static net.rmplugins.rmannouncer.core.util.SenderUtil.sendChat;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class ChatSender extends BukkitRunnable {
    int textIndex = 0;

    @Override
    public void run() {

    }

    private void send() {
        // Get online players.
        Collection<? extends Player> players = PLUGIN.getServer().getOnlinePlayers();
        // Get texts' max size.
        int textMax = texts.size();
        // Get original text.
        String text = texts.get(textIndex);
        // Create Json object.
        JsonObject jsonObject = new JsonParser().parse(text).getAsJsonObject();
        // Get original text.
        String originText = jsonObject.get("text").getAsString();

        jsonObject.addProperty("text", "");

        for (Player player : players) {
            sendChat(player, text);
        }
    }
}
