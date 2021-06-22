package net.rmplugins.rmannouncer.core.cron.task;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

import static net.rmplugins.rmannouncer.core.util.SenderUtil.sendBossBar;
import static net.rmplugins.rmannouncer.core.util.SenderUtil.translateString;
import static net.rmplugins.rmannouncer.data.plugin.Main.*;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class BossBarSender extends BukkitRunnable {
    private static BossBarSender sender;
    public static BossBarSender self() {
        if (sender == null) {
            sender = new BossBarSender();
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
        if (text == null) {
            text = "WARNING BOSSBAR TEXT IS NULL!";
        }else {
            text = bossbarTexts.get(textIndex);
        }

        for (Player player : players) {
            // Translate Text's value.
            String finalText = translateString(player, text);
            // Send translated text to player.
            sendBossBar(bossBar ,barStyle, barColor, player, finalText);
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
