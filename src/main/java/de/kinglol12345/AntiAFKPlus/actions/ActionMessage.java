package de.kinglol12345.AntiAFKPlus.actions;

import de.kinglol12345.AntiAFKPlus.AntiAFKPlus;
import org.bukkit.entity.Player;

class ActionMessage extends Action {
    private String msg;

    ActionMessage(String text) {
        try {
            msg = AntiAFKPlus.config.getConfig().getString(text);
            msg = org.bukkit.ChatColor.translateAlternateColorCodes('&', msg);
        } catch (NullPointerException e) {
            System.err.println("[AntiAFKPlus] Unvalid message path: " + text);
            msg = null;
            e.printStackTrace();
        }
    }

    public void run(Player player) {
        if ( msg != null ) {
            player.sendMessage(msg);
        }
    }
}
