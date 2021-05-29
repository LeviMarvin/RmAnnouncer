package net.rmplugins.rmannouncer.data.server.nms;

import net.rmplugins.rmannouncer.util.ReflectUtil;

/**
 * Location: net.minecraft.server
 *
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class NmsClass {
    private static NmsClass NMS;
    public static NmsClass getNms() {
        if (NMS == null) NMS = new NmsClass();
        return NMS;
    }
    /**
     * Common component:
     *  - iChatBaseComponent
     *  - packet
     * Title component:
     *  - packetPlayOutTitle;
     *  - enumTitleAction;
     * Chat component:
     *  - packetPlayOutChat;
     *  - chatMessageType;
     */
    public Class<?> iChatBaseComponent;
    public Class<?> packet;
    public Class<?> packetPlayOutTitle;
    public Class<?> enumTitleAction;
    public Class<?> packetPlayOutChat;
    public Class<?> chatMessageType;

    public void init() {
        load();
    }

    public void reload() {
        load();
    }

    private void load() {
        try {
            iChatBaseComponent = ReflectUtil.getNmsClass("IChatBaseComponent");
            packet = ReflectUtil.getNmsClass("Packet");
            packetPlayOutTitle = ReflectUtil.getNmsClass("PacketPlayOutTitle");
            enumTitleAction = ReflectUtil.getNmsClass("PacketPlayOutTitle$EnumTitleAction");
            packetPlayOutChat = ReflectUtil.getNmsClass("PacketPlayOutChat");
            chatMessageType = ReflectUtil.getNmsClass("ChatMessageType");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
