package net.rmplugins.rmannouncer.data.plugin.info;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class ReleaseLoader {
    Properties prop = new Properties();
    InputStream is;

    public ReleaseLoader() throws IOException {
        is = getClass().getResourceAsStream("/release.ver");
        prop.load(is);
    }

    public Object get(Object key) {
        return prop.get(key);
    }
}
