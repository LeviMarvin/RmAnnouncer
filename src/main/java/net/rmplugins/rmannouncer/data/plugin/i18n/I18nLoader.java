package net.rmplugins.rmannouncer.data.plugin.i18n;

import java.io.*;
import java.util.Properties;

import static net.rmplugins.rmannouncer.data.plugin.Main.PLUGIN;
import static net.rmplugins.rmannouncer.data.plugin.Main.i18n;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class I18nLoader {
    Properties langFile = new Properties();
    InputStream is;

    public I18nLoader() throws IOException {
        is = new FileInputStream(PLUGIN.getDataFolder() + i18n + ".lang");
        langFile.load(is);
    }

    public Properties getProp() {
        return this.langFile;
    }
}
