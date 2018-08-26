package de.kinglol12345.AntiAFKPlus;

import de.kinglol12345.AntiAFKPlus.actions.Action;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;


public class AFKPlayer {
    public static HashMap<Player, AFKPlayer> all = new HashMap();
    private Player player;
    private int checksFailed;
    private Location lastLoc;

    private AFKPlayer(Player player) {
        all.put(player, this);

        this.player = player;
        checksFailed = 0;
    }

    public static boolean isAFK(Player p) {
        return all.containsKey(p);
    }

    public static AFKPlayer getPlayer(Player p) {
        if ( !all.containsKey(p) ) {
            return new AFKPlayer(p);
        }
        return (AFKPlayer) all.get(p);
    }

    public void setTeleported(Location loc) {
        lastLoc = loc;
    }

    public void remove() {
        if ( lastLoc != null ) {
            player.teleport(lastLoc);
        }
        all.remove(player);
    }

    public void addFail() {
        checksFailed += 1;

        if ( AntiAFKPlus.actions.containsKey(Integer.valueOf(checksFailed)) ) {
            List<Action> actions = (List) AntiAFKPlus.actions.get(Integer.valueOf(checksFailed));
            for (Action a : actions) {
                a.run(player);
            }
        }
    }
}
