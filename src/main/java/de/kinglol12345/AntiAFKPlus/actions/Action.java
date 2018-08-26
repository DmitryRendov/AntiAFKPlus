package de.kinglol12345.AntiAFKPlus.actions;

import org.bukkit.entity.Player;
import de.kinglol12345.AntiAFKPlus.actions.ActionMessage;
import de.kinglol12345.AntiAFKPlus.actions.ActionSound;
import de.kinglol12345.AntiAFKPlus.actions.ActionCommand;

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
