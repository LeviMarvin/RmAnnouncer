package net.rmplugins.rmannouncer.core.cron.task;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

import static net.rmplugins.rmannouncer.core.util.SenderUtil.sendTitle;
import static net.rmplugins.rmannouncer.core.util.StringUtil.*;
import static net.rmplugins.rmannouncer.data.plugin.Main.*;

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
        String subJsonText = subTitleTexts.get(subTextIndex);
        // Get text's value.
        String text = getKeyValue(jsonText, "text");
        String subText = getKeyValue(subJsonText, "text");

        for (Player player : players) {
            // Translate Text's value.
            String translatedText = translateString(player, text);
            String subTranslatedText = translateString(player, subText);
            // Set translated text to jsonObject.
            String finalJsonText = setKeyValue(jsonText, "text", translatedText);
            String subFinalJsonText = setKeyValue(subJsonText, "text", subTranslatedText);
            sendTitle(player, finalJsonText, subFinalJsonText, titleFadeIn, titleStay, titleFadeOut);
        }
        if (textIndex == textsMax){
            textIndex = 0;
        }
        if (subTextIndex == subTextsMax){
            subTextIndex = 0;
        }
        textIndex++;
        subTextIndex++;
    }

    public boolean isRunning() {
        return this.isRunning;
    }
}
