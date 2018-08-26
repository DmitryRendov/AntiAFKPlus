package de.kinglol12345.AntiAFKPlus.commands;

import de.kinglol12345.AntiAFKPlus.storage.DefaultConfig;
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
                de.kinglol12345.AntiAFKPlus.AntiAFKPlus.removePlayer(p);
            }

            de.kinglol12345.AntiAFKPlus.AntiAFKPlus.actions.clear();
            de.kinglol12345.AntiAFKPlus.AntiAFKPlus.players.clear();
            de.kinglol12345.AntiAFKPlus.AFKPlayer.all.clear();

            de.kinglol12345.AntiAFKPlus.AntiAFKPlus.config = new DefaultConfig();
            de.kinglol12345.AntiAFKPlus.AntiAFKPlus.config.loadConfig();

            de.kinglol12345.AntiAFKPlus.AntiAFKPlus.restartTimer();

            sender.sendMessage("§6[AntiAFK+] §7Reload complete");
        }


        return true;
    }
}
