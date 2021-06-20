package net.rmplugins.rmannouncer.core.util;

import net.rmplugins.rmannouncer.data.plugin.Main;
import net.rmplugins.rmannouncer.data.server.nms.NmsClass;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import static net.rmplugins.rmannouncer.data.plugin.Main.PLUGIN;
import static net.rmplugins.rmannouncer.data.server.Server.majorVersion;
import static net.rmplugins.rmannouncer.data.server.Server.minorVersion;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class SenderUtil {
    /**
     * Send chat message to the player who you want.
     *
     * @param player +Player+ The player who accept message.
     * @param jsonText +String+ | +{JSON}+ The message that you want to show.
     */
    public static void sendChat(Player player, String jsonText){
        try {
            // Create chat text object.
            Object chatText = NmsClass.getNms().iChatBaseComponent
                    .getDeclaredClasses()[0]
                    .getDeclaredMethod("a",String.class)
                    .invoke(null, jsonText);
            // Create the text packet.
            Object packet = createPacket(0, majorVersion, minorVersion, chatText);
            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
            // Get PlayerConnection.
            Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
            // Send chats.
            playerConnection.getClass().getMethod("sendPacket", NmsClass.getNms().packet)
                    .invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Send title message to the player who you want.
     *
     * @param player +Player+ The player who accept message.
     * @param jsonTitleText +String+ | +{JSON}+ The main-title message that you want to show.
     * @param jsonSubTitleText +String+ | +{JSON}+ The sub-title message that you want to show.
     * @param in +int+ The time that message fade in costs.
     * @param stay +int+ The time that message stay costs.
     * @param out +int+ The time that message fade out costs.
     */
    public static void sendTitle(Player player, String jsonTitleText, String jsonSubTitleText, int in, int stay, int out) {
        try {
            // Create main-title text object.
            Object mainTitle = NmsClass.getNms().iChatBaseComponent
                    .getDeclaredClasses()[0]
                    .getDeclaredMethod("a", String.class)
                    .invoke(null, jsonTitleText);
            Object enumTITLE = NmsClass.getNms().enumTitleAction
                    .getEnumConstants()[0];
            Object packetPlayOutMainTitle = NmsClass.getNms().packetPlayOutTitle.getConstructor(
                    NmsClass.getNms().enumTitleAction,
                    NmsClass.getNms().iChatBaseComponent,
                    int.class, int.class, int.class
            ).newInstance(enumTITLE, mainTitle, in, stay, out);
            // Create sub-title text object.
            Object subTitle = NmsClass.getNms().iChatBaseComponent
                    .getDeclaredClasses()[0]
                    .getDeclaredMethod("a", String.class)
                    .invoke(null, jsonSubTitleText);
            Object enumSUBTITLE = NmsClass.getNms().enumTitleAction
                    .getEnumConstants()[1];
            Object packetPlayOutSubTitle = NmsClass.getNms().packetPlayOutTitle.getConstructor(
                    NmsClass.getNms().enumTitleAction,
                    NmsClass.getNms().iChatBaseComponent,
                    int.class, int.class, int.class
            ).newInstance(enumSUBTITLE, subTitle, in, stay, out);
            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
            // Get PlayerConnection.
            Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
            // Send titles.
            playerConnection.getClass().getMethod("sendPacket", NmsClass.getNms().packet)
                    .invoke(playerConnection, packetPlayOutMainTitle);
            playerConnection.getClass().getMethod("sendPacket", NmsClass.getNms().packet)
                    .invoke(playerConnection, packetPlayOutSubTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Send actionbar message to the player who you want.
     *
     * @param player +Player+ The player who accept message.
     * @param jsonText +String+ | +{JSON}+ The message that you want to show.
     */
    public static void sendActionBar(Player player, String jsonText) {
        try {
            // Create ActionBar text object.
            Object iChatBaseComponent = NmsClass.getNms().iChatBaseComponent
                    .getDeclaredClasses()[0]
                    .getMethod("a", String.class)
                    .invoke(null, jsonText);
            // Create the text packet.
            Object packet = createPacket(2, majorVersion, minorVersion, iChatBaseComponent);
            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
            // Get PlayerConnection.
            Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
            // Send ActionBar.
            playerConnection.getClass().getMethod("sendPacket", NmsClass.getNms().packet)
                    .invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Send bossbar message to the player who you want.
     *
     * @param bossBar +BossBar+ The instance of accepting message.
     * @param style +BarStyle+ The style of the instance.
     * @param color +BarColor+ The color of the instance.
     * @param text +String+ The message text.
     * @param player +Player+ The player who can see the message.
     */
    public static void sendBossBar(BossBar bossBar, BarStyle style, BarColor color, Player player, String text) {
        if (bossBar == null) {
            bossBar = PLUGIN.getServer().createBossBar(text, color, style, (BarFlag) null);
        }
        try {
            bossBar.setStyle(style);
            bossBar.setColor(color);
            bossBar.setTitle(text);
            bossBar.setVisible(true);
            bossBar.addPlayer(player);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create an data packet with NMS Class.
     *
     * @param msgTypeEnumIndex +int+ The index of enum type in enum class "chatMessageType".
     * @param major +int+ The server major version. (This will help plugin to generate an right packet)
     * @param minor +int+ The server minor version. (This will help plugin to generate an right packet)
     * @param iChatBaseComponent +Object+ The object of iChatBaseComponent.
     * @return +Object+ The packet instance.
     */
    private static Object createPacket(
            int msgTypeEnumIndex, int major, int minor,
            Object iChatBaseComponent
    ) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object packet;
        if (major >= 1 && minor >= 16) {
            packet = NmsClass.getNms().packetPlayOutChat.getConstructor(
                    NmsClass.getNms().iChatBaseComponent,
                    NmsClass.getNms().chatMessageType,
                    UUID.class
            ).newInstance(iChatBaseComponent, NmsClass.getNms().chatMessageType.getEnumConstants()[msgTypeEnumIndex], Main.UUID);
        }else {
            packet = NmsClass.getNms().packetPlayOutChat.getConstructor(
                    NmsClass.getNms().iChatBaseComponent,
                    NmsClass.getNms().chatMessageType
            ).newInstance(iChatBaseComponent, NmsClass.getNms().chatMessageType.getEnumConstants()[msgTypeEnumIndex]);
        }
        return packet;
    }
}
