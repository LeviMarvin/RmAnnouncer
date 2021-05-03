package net.rmplugins.rmannouncer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static net.rmplugins.rmannouncer.util.MessageUtil.sendAlert;
import static net.rmplugins.rmannouncer.util.MessageUtil.sendMsg;
import static net.rmplugins.rmannouncer.data.plugin.Extension.*;

/**
 * @author Levi Marvin
 * @version 1.0
 */
public final class RmAnnouncer extends JavaPlugin {
    private static JavaPlugin PLUGIN;
    public static JavaPlugin get() {
        return PLUGIN;
    }

    @Override
    public void onEnable() {
        PLUGIN = this;
        //Init config.
        //TODO
        //Init extensions.
        initExtension();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void initExtension() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            PAPI_support = true;
            sendMsg("Enable the support of PlaceholderAPI successfully!");
        } else {
            sendAlert("Could not find PlaceholderAPI! Will not enable the support of PlaceholderAPI.");
        }

    }
}
