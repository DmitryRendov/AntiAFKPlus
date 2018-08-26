package de.kinglol12345.AntiAFKPlus.runnables;

import de.kinglol12345.AntiAFKPlus.AFKPlayer;
import de.kinglol12345.AntiAFKPlus.AntiAFKPlus;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Iterator;

public class AFKCheckTimer implements Runnable {
    public AFKCheckTimer() {
    }

    public void run() {
        Iterator var1 = Bukkit.getOnlinePlayers().iterator();

        while (var1.hasNext()) {
            Player p = (Player) var1.next();
            if ( p.hasPermission("AntiAFKPlus.bypass") || p.isOp() ) {
                return;
            }

            if ( !AntiAFKPlus.players.containsKey(p) ) {
                AntiAFKPlus.players.put(p, p.getLocation().getDirection());
            } else  {
                Vector v = (Vector) AntiAFKPlus.players.get(p);
                if ( v.equals(p.getLocation().getDirection()) ) {
                    AFKPlayer.getPlayer(p).addFail();
                } else {
                    AntiAFKPlus.players.put(p, p.getLocation().getDirection());
                    if ( AFKPlayer.isAFK(p) ) {
                        AFKPlayer.getPlayer(p).remove();
                    }
                }
            }
        }
    }
}
