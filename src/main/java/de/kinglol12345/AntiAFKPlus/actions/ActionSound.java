package de.kinglol12345.AntiAFKPlus.actions;

import org.bukkit.entity.Player;

class ActionSound extends Action {
    private org.bukkit.Sound sound;

    ActionSound(String text) {
        try {
            sound = org.bukkit.Sound.valueOf(text.replaceFirst("Sound.", ""));
        } catch (Exception e) {
            System.err.println("[AntiAFKPlus] Invalid sound: " + text);
            e.printStackTrace();
        }
    }

    public void run(Player player) {
        if ( sound != null ) {
            player.playSound(player.getLocation(), sound, 1.0F, 1.0F);
        }
    }
}
