package net.rmplugins.rmannouncer.core.cron.task;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

import static net.rmplugins.rmannouncer.core.util.SenderUtil.sendBossBar;
import static net.rmplugins.rmannouncer.core.util.StringUtil.*;
import static net.rmplugins.rmannouncer.data.plugin.Main.*;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class BossBarSender extends BukkitRunnable {
    private static ActionBarSender sender;
    public static ActionBarSender self() {
        if (sender == null) {
            sender = new ActionBarSender();
        }
        return sender;
    }
    private boolean isRunning = false;
    int textIndex = 0;
    BossBar bossBar;

    @Override
    public void run() {
        this.isRunning = true;
        send();
    }

    private void send() {
        // Get online players.
        Collection<? extends Player> players = PLUGIN.getServer().getOnlinePlayers();
        // Get texts' max size.
        int textsMax = bossbarTexts.size();
        // Get original text.
        String text = bossbarTexts.get(textIndex);

        for (Player player : players) {
            // Translate Text's value.
            String translatedText = translateString(player, text);
            // Send translated text to player.
            sendBossBar(bossBar ,barStyle, barColor, player, translatedText);
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
