package de.kinglol12345.AntiAFKPlus.actions;

import org.bukkit.entity.Player;

class ActionCommand extends Action {
    private String cmd;

    ActionCommand(String text) {
        cmd = text;
    }


    public void run(Player player) {
        org.bukkit.Bukkit.getServer().dispatchCommand(org.bukkit.Bukkit.getConsoleSender(), cmd.replace("%player%", player.getName()));

        if ( de.kinglol12345.AntiAFKPlus.AntiAFKPlus.players.containsKey(player) ) {
            de.kinglol12345.AntiAFKPlus.AntiAFKPlus.players.put(player, player.getLocation().getDirection());
        }
    }
}
