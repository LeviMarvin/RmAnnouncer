package net.rmplugins.rmannouncer.data.plugin.i18n;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static net.rmplugins.rmannouncer.data.plugin.Main.PLUGIN;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class I18nLoader {
    Properties langFile = new Properties();
    InputStream is;

    public I18nLoader(String i18n) throws IOException {
        load(i18n);
    }

    public void load(String i18n) throws IOException {
        is = new FileInputStream(PLUGIN.getDataFolder() + "\\lang\\" + i18n + ".lang");
        langFile.load(new InputStreamReader(is, StandardCharsets.UTF_8));
    }

    public Properties getProp() {
        return this.langFile;
    }
}
