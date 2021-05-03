package net.rmplugins.rmannouncer.data.server.obc;

import net.rmplugins.rmannouncer.util.ReflectUtil;

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
        try {
            craftPlayer = ReflectUtil.getObcClass("CraftPlayer");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
