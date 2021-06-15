package fr.wdss.modi.listeners;

import fr.wdss.modi.Main;
import fr.wdss.modi.utils.modiPermissions;
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

        if(e.getView().getTitle().equalsIgnoreCase("Tablist")){
            if(p.hasPermission(new modiPermissions().staff) || p.isOp()){
                if(current.getType() == Material.PLAYER_HEAD){
                    String targetPlayer = current.getItemMeta().getDisplayName();
                    e.setCancelled(true);
                    p.closeInventory();
                    p.chat("/tablist " + targetPlayer);
                }
            }else {
                e.setCancelled(true);
                p.closeInventory();
            }
        }

        if(current.getType() == Material.PAPER){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§fDirect Message")){
                e.setCancelled(true);
                String targetName = e.getView().getTitle();
                Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                if(targetPlayer == null){
                    p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                }
                p.sendMessage(Main.prefix_success + "Faite la commande : §e/dm " + targetPlayer.getName() + " <votre_message>");
                p.closeInventory();
            }
        }

        if(current.getType() == Material.NETHERITE_AXE){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§cBan")){
                if(Main.Staff.contains(p)){
                    e.setCancelled(true);
                    String targetName = e.getView().getTitle();
                    Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                    if(targetPlayer == null){
                        p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                    }
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + targetPlayer.getName() + " §cVous avez été §9Banni §cpar un Modérateur !");
                    p.sendMessage(Main.prefix_success + "Le joueur §2" + targetName + " §fvient d'être §9Ban");
                    p.closeInventory();
                }
            }
        }

        if(current.getType() == Material.PAPER){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§9Mute")){
                if(Main.Staff.contains(p)){
                    e.setCancelled(true);
                    String targetName = e.getView().getTitle();
                    Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                    if(targetPlayer == null){
                        p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                    }
                    if(Main.Mute.contains(targetPlayer.getName())){
                        Main.Mute.remove(targetPlayer.getName());
                        p.sendMessage(Main.prefix_success + "Le joueur §2" + targetPlayer.getName() + " §fvient d'être §9Unmute");
                    }else {
                        Main.Mute.add(targetPlayer.getName());
                        p.sendMessage(Main.prefix_success + "Le joueur §2" + targetPlayer.getName() + " §fvient d'être §9Mute");
                    }
                }
            }
        }

        if(current.getType() == Material.ANVIL){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Kick")){
                if(Main.Staff.contains(p)){
                    e.setCancelled(true);
                    String targetName = e.getView().getTitle();
                    Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                    if(targetPlayer == null){
                        p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                    }
                    targetPlayer.kickPlayer("§cVous avez été §9Kick §cpar un Modérateur !");
                    p.sendMessage(Main.prefix_success + "Le joueur §2" + targetPlayer.getName() + " §fvient d'être §9Kick");
                    p.closeInventory();
                }
            }
        }

        if(current.getType() == Material.COBBLESTONE){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§8GM 0")){
                if(Main.Staff.contains(p)){
                    e.setCancelled(true);
                    String targetName = e.getView().getTitle();
                    Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                    if(targetPlayer == null){
                        p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                    }
                    targetPlayer.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage(Main.prefix_success + "Le mode de jeu de §2" + targetPlayer.getDisplayName() + " §fa été défini sur §9SURVIVAL");
                    targetPlayer.sendMessage(Main.prefix_success + "Votre mode de jeu a été défini sur §9SURVIVAL");
                }
            }
        }

        if(current.getType() == Material.GRASS_BLOCK){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§2GM 1")){
                if(Main.Staff.contains(p)){
                    e.setCancelled(true);
                    String targetName = e.getView().getTitle();
                    Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                    if(targetPlayer == null){
                        p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                    }
                    targetPlayer.setGameMode(GameMode.CREATIVE);
                    p.sendMessage(Main.prefix_success + "Le mode de jeu de §2" + targetPlayer.getDisplayName() + " §fa été défini sur §9CREATIVE");
                    targetPlayer.sendMessage(Main.prefix_success + "Votre mode de jeu a été défini sur §9CREATIVE");
                }
            }
        }

        if(current.getType() == Material.SPRUCE_LOG){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§cGM 2")){
                if(Main.Staff.contains(p)){
                    e.setCancelled(true);
                    String targetName = e.getView().getTitle();
                    Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                    if(targetPlayer == null){
                        p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                    }
                    targetPlayer.setGameMode(GameMode.ADVENTURE);
                    p.sendMessage(Main.prefix_success + "Le mode de jeu de §2" + targetPlayer.getDisplayName() + " §fa été défini sur §9ADVENTURE");
                    targetPlayer.sendMessage(Main.prefix_success + "Votre mode de jeu a été défini sur §9ADVENTURE");
                }
            }
        }

        if(current.getType() == Material.BARRIER){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§fGM 3")){
                if(Main.Staff.contains(p)){
                    e.setCancelled(true);
                    String targetName = e.getView().getTitle();
                    Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                    if(targetPlayer == null){
                        p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                    }
                    targetPlayer.setGameMode(GameMode.SPECTATOR);
                    p.sendMessage(Main.prefix_success + "Le mode de jeu de §2" + targetPlayer.getDisplayName() + " §fa été défini sur §9SPECTATOR");
                    targetPlayer.sendMessage(Main.prefix_success + "Votre mode de jeu a été défini sur §9SPECTATOR");
                }
            }
        }

        if(current.getType() == Material.ENDER_PEARL){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§fGoto")){
                if(Main.Staff.contains(p)){
                    e.setCancelled(true);
                    String targetName = e.getView().getTitle();
                    Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                    if(targetPlayer == null){
                        p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                    }
                    p.teleport(targetPlayer.getLocation());
                    p.sendMessage(Main.prefix_success + "Tu viens de te §9Téléporter §fsur §2" + targetPlayer.getName());
                    p.closeInventory();
                }
            }
        }

        if(current.getType() == Material.ENDER_EYE){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§fBring")){
                if(Main.Staff.contains(p)){
                    e.setCancelled(true);
                    String targetName = e.getView().getTitle();
                    Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                    if(targetPlayer == null){
                        p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                    }
                    targetPlayer.teleport(p.getLocation());
                    p.sendMessage(Main.prefix_success + "Tu viens de §9Téléporter §2" + targetPlayer.getName() + " §fsur toi");
                    p.closeInventory();
                }
            }
        }

        if(current.getType() == Material.GLASS){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§fVanish")){
                if(Main.Staff.contains(p)){
                    e.setCancelled(true);
                    String targetName = e.getView().getTitle();
                    Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                    if(targetPlayer == null){
                        p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                    }
                    if(targetPlayer.isInvisible() == true){
                        targetPlayer.setInvisible(false);
                        p.sendMessage(Main.prefix_success + "Tu viens de passer §2" + targetPlayer.getName() + " §fen §9Visible");
                    }else {
                        targetPlayer.setInvisible(true);
                        p.sendMessage(Main.prefix_success + "Tu viens de passer §2" + targetPlayer.getName() + " §fen §9Invisible");
                    }
                }
            }
        }

        if(current.getType() == Material.BEDROCK){
            if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§9Salle de Modération")){
                if(Main.Staff.contains(p)){
                    e.setCancelled(true);
                    String targetName = e.getView().getTitle();
                    Player targetPlayer = Bukkit.getServer().getPlayer(targetName);
                    if(targetPlayer == null){
                        p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                    }
                    String location = main.getConfig().getString("modspace");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "teleport " + p.getName() + " " + location);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "teleport " + targetPlayer.getName() + " " + location);
                    p.sendMessage(Main.prefix_success + "Tu viens de téléporter §2" + targetPlayer.getName() + " §fest toi-même à la §9Salle de Modération");
                    p.closeInventory();
                }
            }
        }

    }

}
