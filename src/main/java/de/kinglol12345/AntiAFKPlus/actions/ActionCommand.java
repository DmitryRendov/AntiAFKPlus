package de.kinglol12345.AntiAFKPlus.actions;

import org.bukkit.entity.Player;
import de.kinglol12345.AntiAFKPlus.AntiAFKPlus;

class ActionCommand extends Action {
    private String cmd;

    ActionCommand(String text) {
        cmd = text;
    }


    public void run(Player player) {
        org.bukkit.Bukkit.getServer().dispatchCommand(org.bukkit.Bukkit.getConsoleSender(), cmd.replace("%player%", player.getName()));

        if ( AntiAFKPlus.players.containsKey(player) ) {
            AntiAFKPlus.players.put(player, player.getLocation().getDirection());
        }
    }
}
