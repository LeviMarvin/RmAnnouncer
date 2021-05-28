package net.rmplugins.rmannouncer.util;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.Properties;

import static net.rmplugins.rmannouncer.util.MessageUtil.sendError;

/**
 * @author Levi Marvin
 * @version 1.0
 * @since 1.0
 */
public class UpdateUtil {
    public static boolean checkUpdate() {
        return false;
    }

    public static String getMainVersion() {
        URL url;
        HttpsURLConnection connection;
        String version;
        try {
            Properties props=System.getProperties();
            String osName = props.getProperty("os.name");
            String osArch = props.getProperty("os.arch");
            String osVersion = props.getProperty("os.version");
            url = new URL("https://api.rmplugins.net/v1/static/check");
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "RmAnnouncer/1.0 (release; " + osName + " " + osArch + " " + osVersion + ") RmPlugins-Agent/1.0 (static; SSL; Java 11)");
            connection.setInstanceFollowRedirects(false);
            connection.connect();
        }catch (Exception e) {
            sendError(e);
        }
        return "";
    }

    public static String getSafetyVersion() {
        return "";
    }

    public static String getFunctionVersion() {
        return "";
    }
}
