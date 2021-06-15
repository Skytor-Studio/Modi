package fr.wdss.modi.listeners;

import fr.wdss.modi.Main;
import fr.wdss.modi.utils.modiPermissions;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class mainListener implements Listener {
    private Main main;
    public mainListener(Main main) { this.main = main; }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        FileConfiguration banConfig = YamlConfiguration.loadConfiguration(main.getFile("ban"));
        if(banConfig.contains("Players." + p.getName() + ".status")){
            if(banConfig.getString("Players." + p.getName() + ".status").equalsIgnoreCase("on")){
                p.kickPlayer(banConfig.getString("Players." + p.getName() + ".reason"));
            }
        }
        if(Main.Maintenance == true){
            if(p.isOp() || p.hasPermission(new modiPermissions().maintenanceEnter)){
                p.sendMessage(Main.prefix + "§aLe serveur est actuellement en §6Maintenance §a!");
            }else {
                p.kickPlayer("§cLe serveur est en §6Maintenance§c, veuilliez revenir quand la maintenance sera terminé !");
            }
        }
        if(p.isOp()){
            p.sendMessage(Main.prefix + "§aCe serveur, est propulsée par le plugin §6Modi§a, crée par §6Mr.Wabezeter §ade la §6W.D.S.S. §a!");
        }

    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();

        if(Main.Mute.contains(p.getName())){
            e.setCancelled(true);
            p.sendMessage(Main.prefix_error + "Tu est §6Mute§c, donc tu ne peux pas parler !");
        }else {
            e.setFormat("§7 " + e.getPlayer().getName() + " §f> " + e.getMessage());
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();

        if(Main.Freeze.contains(p.getName())){
            e.setCancelled(true);
            p.sendMessage(Main.prefix_error + "Tu est §6Freeze§c, donc tu ne peux pas bouger");
        }
    }

}
