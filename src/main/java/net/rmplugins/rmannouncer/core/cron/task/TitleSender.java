package net.rmplugins.rmannouncer.core.cron.task;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

import static net.rmplugins.rmannouncer.core.util.SenderUtil.sendTitle;
import static net.rmplugins.rmannouncer.core.util.StringUtil.*;
import static net.rmplugins.rmannouncer.data.plugin.Main.PLUGIN;
import static net.rmplugins.rmannouncer.data.plugin.Main.titleFadeIn;
import static net.rmplugins.rmannouncer.data.plugin.Main.titleStay;
import static net.rmplugins.rmannouncer.data.plugin.Main.titleFadeOut;
import static net.rmplugins.rmannouncer.data.plugin.Main.titleTexts;

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
        int textMax = titleTexts.size();
        // Get original json text.
        String jsonText = titleTexts.get(textIndex);
        // Get text's value.
        String text = getKeyValue(jsonText, "text");

        for (Player player : players) {
            // Translate Text's value.
            String translatedText = translateString(player, text);
            // Set translated text to jsonObject.
            String finalJsonText = setKeyValue(jsonText, "text", translatedText);
            sendTitle(player, finalJsonText, null, titleFadeIn, titleStay, titleFadeOut);
        }
    }

    public boolean isRunning() {
        return this.isRunning;
    }
}
