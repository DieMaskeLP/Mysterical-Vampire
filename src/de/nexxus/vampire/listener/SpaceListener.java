package de.nexxus.vampire.listener;

import de.nexxus.vampire.gamestate.GameState;
import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.manager.Manager;
import de.nexxus.vampire.manager.Roles;
import de.robingrether.idisguise.api.DisguiseAPI;
import de.robingrether.idisguise.disguise.Disguise;
import de.robingrether.idisguise.disguise.DisguiseType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

//Created by Frederic | DieMaskeLP at 31.10.2019, 14:54
public class SpaceListener implements Listener {

    @EventHandler
    public void onSpace(PlayerToggleFlightEvent e){
        if (Main.getManager().getGameStateManager().isCurrentGameState(GameState.INGAME_STATE)){
            if (Main.getManager().getRoleManager().getPlayerRole(e.getPlayer()) == Roles.VAMPIRE){
                DisguiseAPI api = Main.getManager().getDisguiseAPI();
                Player p = e.getPlayer();
                if (!api.isDisguised(p)){
                    Disguise disguise = DisguiseType.BAT.newInstance();
                    disguise.setVisibility(Disguise.Visibility.EVERYONE);
                    api.disguise(p, disguise);
                } else {
                    api.undisguise(p);
                }
            }
        }
    }


}
