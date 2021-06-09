package fr.wdss.modi.listeners;

import fr.wdss.modi.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class modListener implements Listener {
    private Main main;
    public modListener(Main mainPlugin) {
        this.main = mainPlugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Inventory modInv = e.getInventory();
        Player p = (Player) e.getWhoClicked();
        ItemStack current = e.getCurrentItem();

        if(current == null) return;

        if(current.getType() == Material.BOW){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Kick")){
                e.setCancelled(true);
                String targetName = e.getView().getTitle();
                Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                if(targetPlayer == null){
                    p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                }
                targetPlayer.kickPlayer("§cVous avez été §6Kick §cpar un §bModérateur §c!");
                p.sendMessage(Main.prefix + "§aTu viens de §6Kick §7" + targetName + " §a!");
            }
        }
        if(current.getType() == Material.ANVIL){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Mute")){
                e.setCancelled(true);
                String targetName = e.getView().getTitle();
                Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                if(targetPlayer == null){
                    p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                }
                Main.Mute.add(targetName);
                p.sendMessage(Main.prefix + "§aTu viens de §6Mute §7" + targetName + " §a!");
            }
        }
        if(current.getType() == Material.DAMAGED_ANVIL){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§6UnMute")){
                e.setCancelled(true);
                String targetName = e.getView().getTitle();
                Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                if(targetPlayer == null){
                    p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                }
                Main.Mute.remove(targetName);
                p.sendMessage(Main.prefix + "§aTu viens de §6UnMute §7" + targetName + " §a!");
            }
        }
        if(current.getType() == Material.PAPER){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§6MP - Discord")){
                e.setCancelled(true);
                String targetName = e.getView().getTitle();
                Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                if(targetPlayer == null){
                    p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                }
                p.sendMessage(Main.prefix + "§aTu viens de §6MP §7" + targetName + " §apour qu'il passe sur Discord !");
                targetPlayer.sendMessage(Main.prefix + "§6" + p.getName() + " §ate demande de passer sur §6Discord §a! ");
            }
        }
        if(current.getType() == Material.REDSTONE){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Ban")){
                e.setCancelled(true);
                String targetName = e.getView().getTitle();
                Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                if(targetPlayer == null){
                    p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                }
                targetPlayer.kickPlayer("§cTu est maintenant §6BAN §cpar un §bModérateur §c!");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + targetName + " Tu as été BAN par un Modérateur !");
                p.sendMessage(Main.prefix + "§aTu viens de §6BAN §7" + targetName + " §a!");
            }
        }
        if(current.getType() == Material.GRASS_BLOCK){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Gamemode §a0")){
                e.setCancelled(true);
                String targetName = e.getView().getTitle();
                Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                if(targetPlayer == null){
                    p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                }
                targetPlayer.setGameMode(GameMode.SURVIVAL);
                p.sendMessage(Main.prefix + "§aTu viens de passer §6" + targetName + " §aen GameMode : §6SURVIVAL");
                targetPlayer.sendMessage(Main.prefix + "§aTu viens de passer en GameMode : §6SURVIVAL");
            }
        }
        if(current.getType() == Material.BEDROCK){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Gamemode §a1")){
                e.setCancelled(true);
                String targetName = e.getView().getTitle();
                Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                if(targetPlayer == null){
                    p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                }
                targetPlayer.setGameMode(GameMode.CREATIVE);
                p.sendMessage(Main.prefix + "§aTu viens de passer §6" + targetName + " §aen GameMode : §6CREATIVE");
                targetPlayer.sendMessage(Main.prefix + "§aTu viens de passer en GameMode : §6CREATIVE");
            }
        }
        if(current.getType() == Material.OAK_WOOD){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Gamemode §a2")){
                e.setCancelled(true);
                String targetName = e.getView().getTitle();
                Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                if(targetPlayer == null){
                    p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                }
                targetPlayer.setGameMode(GameMode.ADVENTURE);
                p.sendMessage(Main.prefix + "§aTu viens de passer §6" + targetName + " §aen GameMode : §6ADVENTURE");
                targetPlayer.sendMessage(Main.prefix + "§aTu viens de passer en GameMode : §6ADVENTURE");
            }
        }
        if(current.getType() == Material.BARRIER){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Gamemode §a3")){
                e.setCancelled(true);
                String targetName = e.getView().getTitle();
                Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                if(targetPlayer == null){
                    p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                }
                targetPlayer.setGameMode(GameMode.SPECTATOR);
                p.sendMessage(Main.prefix + "§aTu viens de passer §6" + targetName + " §aen GameMode : §6SPECTATOR");
                targetPlayer.sendMessage(Main.prefix + "§aTu viens de passer en GameMode : §6SPECTATOR");
            }
        }
        if(current.getType() == Material.ENDER_PEARL){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Goto")){
                e.setCancelled(true);
                String targetName = e.getView().getTitle();
                Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                if(targetPlayer == null){
                    p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                }
                p.teleport(targetPlayer);
                p.sendMessage(Main.prefix + "§aTu viens de te §6Teleporter §aa §6" + targetName + " §a!");
            }
        }
        if(current.getType() == Material.DIAMOND_SWORD){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Kill")){
                e.setCancelled(true);
                String targetName = e.getView().getTitle();
                Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                if(targetPlayer == null){
                    p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                }
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill " + targetName);
                p.sendMessage(Main.prefix + "§aTu viens de §6Kill §7" + targetName + " §a!");
            }
        }
        if(current.getType() == Material.LAVA_BUCKET){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Clear")){
                e.setCancelled(true);
                String targetName = e.getView().getTitle();
                Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                if(targetPlayer == null){
                    p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                }
                targetPlayer.getInventory().clear();
                p.sendMessage(Main.prefix + "§aTu viens de §6Clear §7" + targetName + " §a!");
            }
        }
        if(current.getType() == Material.ENDER_EYE){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Bring")){
                e.setCancelled(true);
                String targetName = e.getView().getTitle();
                Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                if(targetPlayer == null){
                    p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                }
                targetPlayer.teleport(p);
                p.sendMessage(Main.prefix + "§aTu viens de §6Teleporter §7" + targetName + " §asur toi !");
            }
        }
        if(current.getType() == Material.GLASS){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Vanish")){
                e.setCancelled(true);
                String targetName = e.getView().getTitle();
                Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                if(targetPlayer == null){
                    p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                }
                if(targetPlayer.isInvisible() == true){
                    targetPlayer.setInvisible(false);
                    p.sendMessage(Main.prefix + "§aTu viens de §6UnVanish §7" + targetName + " §a!");
                }else {
                    targetPlayer.setInvisible(true);
                    p.sendMessage(Main.prefix + "§aTu viens de §6Vanish §7" + targetName + " §a!");
                }
            }
        }
        if(current.getType() == Material.SPRUCE_LOG){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Salle Modération")){
                e.setCancelled(true);
                String targetName = e.getView().getTitle();
                Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                if(targetPlayer == null){
                    p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                }
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "teleport " + targetName + " " + main.getConfig().getString("modspace"));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "teleport " + p.getName() + " " + main.getConfig().getString("modspace"));
                p.sendMessage(Main.prefix + "§aTu viens de te §6Teleporter §aà la §6Salle de Moderation §aavec §7" + targetName + " §a!");
                targetPlayer.sendMessage(Main.prefix + "§aTu viens de te faire §6Teleporter §a à la §6Salle de Moderation §a!");
            }
        }

    }

}
