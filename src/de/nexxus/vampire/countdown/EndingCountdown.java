package de.nexxus.vampire.countdown;

import de.nexxus.vampire.gamestate.GameState;
import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

//Created by Frederic | DieMaskeLP at 31.10.2019, 15:47
public class EndingCountdown extends Countdown {


    private boolean isRunning = false;
    private int id, seconds = 10;


  @Override
  public void start() {
      if (!isRunning){
          isRunning = true;
          id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
              @Override
              public void run() {
                  Bukkit.getServer().broadcastMessage(Data.PREFIX + "§cServer is restarting in §6" + seconds + " §cSeconds");
                  if (seconds==0){
                      for (Player t : Bukkit.getServer().getOnlinePlayers()){
                          t.kickPlayer("§cServer is shutting down!");
                      }
                      Main.getManager().getEndingCountdown().stop();
                      Bukkit.getServer().reload();
                      Main.getManager().getGameStateManager().stopCurrentGameState();
                  } else seconds--;
              }
          }, 0, 20);
      }


  }

    @Override
    public void stop() {
        if (isRunning()){
            isRunning = false;
            Bukkit.getScheduler().cancelTask(id);
        }
    }

    public void setSeconds(int seconds){
      this.seconds = seconds;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }
}
