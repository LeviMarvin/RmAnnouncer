package net.rmplugins.rmannouncer.data.server.obc;

import net.rmplugins.rmannouncer.util.ReflectUtil;

import static net.rmplugins.rmannouncer.util.MessageUtil.sendReflectError;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class ObcClass {
    private static ObcClass OBC;
    public static ObcClass getObc() {
        if (OBC == null) OBC = new ObcClass();
        return OBC;
    }

    public Class<?> craftPlayer;

    public void init() {
        load();
    }

    public void reload() {
        load();
    }

    private void load() {
        try {
            craftPlayer = ReflectUtil.getObcClass("CraftPlayer");
        } catch (ClassNotFoundException e) {
            sendReflectError(e, "CraftPlayer");
        }
    }
}
