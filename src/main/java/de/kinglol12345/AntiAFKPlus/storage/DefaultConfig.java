package de.kinglol12345.AntiAFKPlus.storage;

import de.kinglol12345.AntiAFKPlus.AntiAFKPlus;
import de.kinglol12345.AntiAFKPlus.actions.Action;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DefaultConfig {
    private File file;
    private FileConfiguration config;
    private Plugin plugin;

    public DefaultConfig() {
        plugin = AntiAFKPlus.getInstance().getPlugin();
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
    }

    public void loadConfig() {
        try {
            plugin.saveDefaultConfig();
            try {
                file = new File(plugin.getDataFolder() + "/config.yml");
                config.load(file);
            } catch (InvalidConfigurationException | java.io.IOException localInvalidConfigurationException) {
            }


            getConfig().getInt("version");
            AntiAFKPlus.CHECK_INTERVAL = getConfig().getInt("check_delay");


            for (String s : getConfig().getConfigurationSection("actions").getKeys(false)) {
                try {
                    int i = Integer.parseInt(s);
                    List<Action> actions = new ArrayList();

                    List<String> text = getConfig().getStringList("actions." + s);

                    if ( text != null ) {
                        for (String f : text) {
                            actions.add(Action.initAction(f));
                        }

                        AntiAFKPlus.actions.put(Integer.valueOf(i), actions);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("[AntiAFK+] Invalid actions number: " + s);
                }
            }
        } catch (NullPointerException e) {
            System.err.println("[AntiAFKPlus] Incorrect config.yml! Disabling Plugin.");
            e.printStackTrace();
            org.bukkit.Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void saveConfig() {
        plugin.saveConfig();
    }
}
