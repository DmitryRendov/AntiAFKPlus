package de.kinglol12345.AntiAFKPlus.actions;

import org.bukkit.entity.Player;

public abstract class Action {
    public Action() {
    }

    public static Action initAction(String text) {
        if ( text.startsWith("messages.") )
            return new ActionMessage(text);
        if ( text.startsWith("Sound.") ) {
            return new ActionSound(text);
        }
        return new ActionCommand(text);
    }

    public abstract void run(Player paramPlayer);
}
