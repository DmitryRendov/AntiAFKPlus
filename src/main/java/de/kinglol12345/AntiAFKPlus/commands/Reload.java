package de.kinglol12345.AntiAFKPlus.commands;

import de.kinglol12345.AntiAFKPlus.storage.DefaultConfig;
import de.kinglol12345.AntiAFKPlus.AntiAFKPlus;
import de.kinglol12345.AntiAFKPlus.AFKPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {
    public Reload() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if ( (args.length == 1) &&
                (args[0].equalsIgnoreCase("reload")) &&
                (sender.hasPermission("antiafkplus.reload")) ) {
            for (org.bukkit.entity.Player p : org.bukkit.Bukkit.getOnlinePlayers()) {
                AntiAFKPlus.removePlayer(p);
            }

            AntiAFKPlus.actions.clear();
            AntiAFKPlus.players.clear();
            AFKPlayer.all.clear();

            AntiAFKPlus.config = new DefaultConfig();
            AntiAFKPlus.config.loadConfig();

            AntiAFKPlus.restartTimer();

            sender.sendMessage("§6[AntiAFK+] §7Reload complete");
        }

        return true;
    }
}
