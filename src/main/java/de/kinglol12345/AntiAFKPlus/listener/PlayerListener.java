package de.kinglol12345.AntiAFKPlus.listener;

import de.kinglol12345.AntiAFKPlus.AFKPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerListener implements org.bukkit.event.Listener {
    public PlayerListener() {
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if ( AFKPlayer.isAFK(p) ) {
            if ( de.kinglol12345.AntiAFKPlus.AntiAFKPlus.players.containsKey(p) ) {
                org.bukkit.util.Vector v = (org.bukkit.util.Vector) de.kinglol12345.AntiAFKPlus.AntiAFKPlus.players.get(p);
                if ( (!v.equals(p.getLocation().getDirection())) &&
                        (AFKPlayer.isAFK(p)) ) {
                    AFKPlayer.getPlayer(p).remove();
                }
            }
        }
    }


    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        if ( !e.isCancelled() ) {
            Player p = e.getPlayer();
            if ( AFKPlayer.isAFK(p) ) {
                AFKPlayer.getPlayer(p).setTeleported(e.getFrom());
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        de.kinglol12345.AntiAFKPlus.AntiAFKPlus.removePlayer(p);
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        Player p = e.getPlayer();
        de.kinglol12345.AntiAFKPlus.AntiAFKPlus.removePlayer(p);
    }
}
