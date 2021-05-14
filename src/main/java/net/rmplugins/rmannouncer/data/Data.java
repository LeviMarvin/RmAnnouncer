package net.rmplugins.rmannouncer.data;

import net.rmplugins.rmannouncer.data.server.nms.NmsClass;
import net.rmplugins.rmannouncer.data.server.obc.ObcClass;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class Data {
    private static Data data;
    public static Data self(){
        if (data == null) {
            data = new Data();
        }
        return data;
    }

    /**
     * !IF YOU NEED TO USE THE CLASS OF THIS PACKAGE!
     * !YOU MUST RUN THIS FUNCTION AT FIRST!
     *
     * Init classes witch under this package.
     * If plugin need reload, I suggested you implement this method.
     */
    public void init() {
        // Initialize NMS and OBS classes.
        NmsClass.getNms().init();
        ObcClass.getObc().init();
    }

    public void reload() {
        // Reinitialize NMS and OBC Classes.
    }
}
