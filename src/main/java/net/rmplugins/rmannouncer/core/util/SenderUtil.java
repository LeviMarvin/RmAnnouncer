package net.rmplugins.rmannouncer.core.util;

import net.rmplugins.rmannouncer.data.server.nms.NmsClass;
import org.bukkit.entity.Player;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class SenderUtil {
    public static void sendChat(Player p, String jsonText){
        try {
            // Create chat text object.
            Object chatText = NmsClass.getNms().iChatBaseComponent
                    .getDeclaredClasses()[0]
                    .getDeclaredMethod("a",String.class)
                    .invoke(null, jsonText);
            Object packet = NmsClass.getNms().packetPlayOutChat.getConstructor(
                    NmsClass.getNms().iChatBaseComponent,
                    NmsClass.getNms().chatMessageType
            ).newInstance(chatText, NmsClass.getNms().chatMessageType.getEnumConstants()[0]);
            Object entityPlayer = p.getClass().getMethod("getHandle").invoke(p);
            // Get PlayerConnection.
            Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
            // Send chats.
            playerConnection.getClass().getMethod("sendPacket", NmsClass.getNms().packet)
                    .invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendTitle(Player p, String jsonTitleText, String jsonSubTitleText, int in, int stay, int out) {
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
            Object entityPlayer = p.getClass().getMethod("getHandle").invoke(p);
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

    public static void sendActionBar(Player p, String jsonText) {
        try {
            // Create ActionBar text object.
            Object iChatBaseComponent = NmsClass.getNms().iChatBaseComponent
                    .getDeclaredClasses()[0]
                    .getMethod("a", String.class)
                    .invoke(null, jsonText);
            Object packet = NmsClass.getNms().packetPlayOutChat.getConstructor(
                    NmsClass.getNms().iChatBaseComponent,
                    NmsClass.getNms().chatMessageType
            ).newInstance(iChatBaseComponent, NmsClass.getNms().chatMessageType.getEnumConstants()[2]);
            Object entityPlayer = p.getClass().getMethod("getHandle").invoke(p);
            // Get PlayerConnection.
            Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
            // Send chats.
            playerConnection.getClass().getMethod("sendPacket", NmsClass.getNms().packet)
                    .invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendBossBar() {}
}
