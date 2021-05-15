package net.rmplugins.rmannouncer.data;

import net.rmplugins.rmannouncer.data.io.File;
import net.rmplugins.rmannouncer.data.server.nms.NmsClass;
import net.rmplugins.rmannouncer.data.server.ob.ObcClass;
import org.bukkit.configuration.file.YamlConfiguration;

import static net.rmplugins.rmannouncer.data.plugin.Main.dataPath;
import static net.rmplugins.rmannouncer.data.plugin.Main.i18n;
import static net.rmplugins.rmannouncer.util.MessageUtil.sendError;

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
        // Initialize NMS, OBC and OB Classes.
        NmsClass.getNms().init();
        ObcClass.getObc().init();
        // Initialize files.
        try {
            File.configFile = YamlConfiguration.loadConfiguration(new java.io.File(dataPath, "config.yml"));
            File.i18nFile = YamlConfiguration.loadConfiguration(new java.io.File(dataPath, "language\\" + i18n + ".yml"));
            File.chatTextFile = YamlConfiguration.loadConfiguration(new java.io.File(dataPath, "message\\" + "chat.yml"));
            File.titleTextFile = YamlConfiguration.loadConfiguration(new java.io.File(dataPath, "message\\" + "title.yml"));
            File.actionBarTextFile = YamlConfiguration.loadConfiguration(new java.io.File(dataPath, "message\\" + "actionbar.yml"));
            File.bossBarTextFile = YamlConfiguration.loadConfiguration(new java.io.File(dataPath, "message\\" + "bossbar.yml"));
        }catch (Exception e) {
            sendError(e);
        }
    }

    public void reload() {
        // Reinitialize NMS, OBC and OB Classes.
        NmsClass.getNms().reload();
        ObcClass.getObc().reload();
    }
}
