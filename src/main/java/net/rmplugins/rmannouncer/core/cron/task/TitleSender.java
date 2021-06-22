package net.rmplugins.rmannouncer.core.cron.task;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

import static net.rmplugins.rmannouncer.core.util.SenderUtil.sendTitle;
import static net.rmplugins.rmannouncer.core.util.SenderUtil.translateString;
import static net.rmplugins.rmannouncer.data.plugin.Main.*;
import static net.rmplugins.rmannouncer.util.JsonUtil.isJson;
import static net.rmplugins.rmannouncer.util.JsonUtil.toTextJson;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class TitleSender extends BukkitRunnable {
    private static TitleSender sender;
    public static TitleSender self() {
        if (sender == null) {
            sender = new TitleSender();
        }
        return sender;
    }
    private boolean isRunning = false;
    int textIndex = 0;
    int subTextIndex = 0;

    @Override
    public void run() {
        this.isRunning = true;
        send();
    }

    private void send() {
        // Get online players.
        Collection<? extends Player> players = PLUGIN.getServer().getOnlinePlayers();
        // Get texts' max size.
        int textsMax = titleTexts.size();
        int subTextsMax = subTitleTexts.size();
        // Get original json text.
        String jsonText = titleTexts.get(textIndex);
        if (!isJson(jsonText)) {
            jsonText = toTextJson(jsonText);
        }
        String subJsonText = subTitleTexts.get(subTextIndex);
        if (!isJson(subJsonText)) {
            subJsonText = toTextJson(subJsonText);
        }

        for (Player player : players) {
            // Translate Text's value.
            String finalText = translateString(player, jsonText);
            String subFinalText = translateString(player, subJsonText);
            // Send the final text.
            sendTitle(player, finalText, subFinalText, titleFadeIn, titleStay, titleFadeOut);
        }
        if (textIndex == textsMax - 1){
            textIndex = 0;
        }
        if (subTextIndex == subTextsMax - 1){
            subTextIndex = 0;
        }
        textIndex++;
        subTextIndex++;
    }

    public boolean isRunning() {
        return this.isRunning;
    }
}
