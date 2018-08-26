package de.kinglol12345.AntiAFKPlus;

import de.kinglol12345.AntiAFKPlus.actions.Action;
import de.kinglol12345.AntiAFKPlus.commands.Reload;
import de.kinglol12345.AntiAFKPlus.listener.PlayerListener;
import de.kinglol12345.AntiAFKPlus.runnables.AFKCheckTimer;
import de.kinglol12345.AntiAFKPlus.storage.DefaultConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Iterator;

public class AntiAFKPlus extends JavaPlugin {
    public static DefaultConfig config;
    public static int CHECK_INTERVAL;
    public static int afkchecktimer = -1;


    public static HashMap<Integer, java.util.List<Action>> actions = new HashMap();
    public static HashMap<Player, Vector> players = new HashMap();
    private static AntiAFKPlus plugin;

    public AntiAFKPlus() {
    }

    public static AntiAFKPlus getInstance() {
        return plugin;
    }

    public static void removePlayer(Player p) {
        if ( players.containsKey(p) ) {
            players.remove(p);
        }

        if ( AFKPlayer.isAFK(p) ) {
            AFKPlayer.getPlayer(p).remove();
        }
    }

    public static void restartTimer() {
        Bukkit.getScheduler().cancelTask(afkchecktimer);
        afkchecktimer = Bukkit.getScheduler().scheduleSyncRepeatingTask(getInstance(), new AFKCheckTimer(), 20L, CHECK_INTERVAL * 20);
    }

    public Plugin getPlugin() {
        return this;
    }

    public void onEnable() {
        plugin = this;
        config = new DefaultConfig();
        config.loadConfig();

        restartTimer();
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        getCommand("antiafk").setExecutor(new Reload());
    }

    public void onDisable() {
        Iterator var1 = Bukkit.getOnlinePlayers().iterator();
        while (var1.hasNext()) {
            Player p = (Player) var1.next();
            removePlayer(p);
        }

    }
}
