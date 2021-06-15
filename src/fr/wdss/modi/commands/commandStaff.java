package fr.wdss.modi.commands;

import fr.wdss.modi.Main;
import fr.wdss.modi.utils.modiPermissions;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.Arrays;

public class commandStaff implements CommandExecutor {
    public Main main;
    public commandStaff(Main main) { this.main = main; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Main.notAPlayer);
        }

        Player p = (Player) sender;

        if(label.equalsIgnoreCase("staff")){
            if(p.hasPermission(new modiPermissions().staff) || p.isOp()){
                if(args.length == 0){
                    p.sendMessage(Main.errorCmd + "/staff <on/off/[player]> <on/off>");
                    return false;
                }else if(args.length == 1){
                    if(args[0].equalsIgnoreCase("on")){
                        FileConfiguration staffConfig = YamlConfiguration.loadConfiguration(main.getFile("staff"));
                        if(staffConfig.contains("Staff." + p.getName())){
                            if(staffConfig.getString("Staff." + p.getName()).equalsIgnoreCase("off")){
                                staffConfig.set("Staff." + p.getName(), "on");
                                Main.Staff.add(p);
                                p.sendMessage(Main.prefix_success + "Votre mode Staff est maintenant §9Activé");
                                try {
                                    staffConfig.save(main.getFile("staff"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            }else {
                                p.sendMessage(Main.prefix_error + "Vous êtes déjà en mode Staff");
                                return false;
                            }
                        }else {
                            staffConfig.set("Staff." + p.getName(), "on");
                            Main.Staff.add(p);
                            p.sendMessage(Main.prefix_success + "Votre mode Staff est maintenant §9Activé");
                            try {
                                staffConfig.save(main.getFile("staff"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return true;
                        }
                    }else if(args[0].equalsIgnoreCase("off")){
                        FileConfiguration staffConfig = YamlConfiguration.loadConfiguration(main.getFile("staff"));
                        if(staffConfig.contains("Staff." + p.getName())){
                            if(staffConfig.getString("Staff." + p.getName()).equalsIgnoreCase("on")){
                                staffConfig.set("Staff." + p.getName(), "off");
                                Main.Staff.remove(p);
                                p.sendMessage(Main.prefix_success + "Votre mode Staff est maintenant §9Désactivé");
                                try {
                                    staffConfig.save(main.getFile("staff"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            }else {
                                p.sendMessage(Main.prefix_error + "Vous n'êtes déjà pas en mode Staff");
                                return false;
                            }
                        }else {
                            staffConfig.set("Staff." + p.getName(), "off");
                            Main.Staff.remove(p);
                            p.sendMessage(Main.prefix_success + "Votre mode Staff est maintenant §9Désactivé");
                            try {
                                staffConfig.save(main.getFile("staff"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return true;
                        }
                    }else {
                        p.sendMessage(Main.errorCmd + "/staff <on/off/[player]> <on/off>");
                        return false;
                    }
                }else if(args.length == 2){
                    if(p.hasPermission(new modiPermissions().admin) || p.isOp()){
                        Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
                        if(targetPlayer == null){
                            sender.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                            return false;
                        }
                        if(args[1].equalsIgnoreCase("on")){
                            FileConfiguration staffConfig = YamlConfiguration.loadConfiguration(main.getFile("staff"));
                            if(staffConfig.contains("Staff." + targetPlayer.getName())){
                                if(staffConfig.getString("Staff." + targetPlayer.getName()).equalsIgnoreCase("off")){
                                    staffConfig.set("Staff." + targetPlayer.getName(), "on");
                                    Main.Staff.add(p);
                                    p.sendMessage(Main.prefix_success + "Le mode de Staff de §2" + targetPlayer.getName() + " §fest maintenant §9Activé");
                                    targetPlayer.sendMessage(Main.prefix_success + "Votre mode Staff est maintenant §9Activé");
                                    try {
                                        staffConfig.save(main.getFile("staff"));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    return true;
                                }else {
                                    p.sendMessage(Main.prefix_error + "Le mode de Staff de " + targetPlayer.getName() + " est déjà Activé");
                                    return false;
                                }
                            }else {
                                staffConfig.set("Staff." + targetPlayer.getName(), "on");
                                Main.Staff.add(p);
                                p.sendMessage(Main.prefix_success + "Le mode de Staff de §2" + targetPlayer.getName() + " §fest maintenant §9Activé");
                                targetPlayer.sendMessage(Main.prefix_success + "Votre mode Staff est maintenant §9Activé");
                                try {
                                    staffConfig.save(main.getFile("staff"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            }
                        }else if(args[1].equalsIgnoreCase("off")){
                            FileConfiguration staffConfig = YamlConfiguration.loadConfiguration(main.getFile("staff"));
                            if(staffConfig.contains("Staff." + targetPlayer.getName())){
                                if(staffConfig.getString("Staff." + targetPlayer.getName()).equalsIgnoreCase("on")){
                                    staffConfig.set("Staff." + targetPlayer.getName(), "off");
                                    Main.Staff.remove(p);
                                    p.sendMessage(Main.prefix_success + "Le mode de Staff de §2" + targetPlayer.getName() + " §fest maintenant §9Désactivé");
                                    targetPlayer.sendMessage(Main.prefix_success + "Votre mode Staff est maintenant §9Désactivé");
                                    try {
                                        staffConfig.save(main.getFile("staff"));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    return true;
                                }else {
                                    p.sendMessage(Main.prefix_error + "Le mode de Staff de " + targetPlayer.getName() + " est déjà Désactivé");
                                    return false;
                                }
                            }else {
                                staffConfig.set("Staff." + targetPlayer.getName(), "off");
                                Main.Staff.remove(p);
                                p.sendMessage(Main.prefix_success + "Le mode de Staff de §2" + targetPlayer.getName() + " §fest maintenant §9Désactivé");
                                targetPlayer.sendMessage(Main.prefix_success + "Votre mode Staff est maintenant §9Désactivé");
                                try {
                                    staffConfig.save(main.getFile("staff"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            }
                        }else {
                            p.sendMessage(Main.errorCmd + "/staff <on/off/[player]> <on/off>");
                            return false;
                        }
                    }else {
                        p.sendMessage(Main.errorPerm);
                        return false;
                    }
                }else {
                    p.sendMessage(Main.errorCmd + "/staff <on/off/[player]> <on/off>");
                    return false;
                }
            }else {
                p.sendMessage(Main.errorPerm);
                return false;
            }
        }
        return false;
    }
}
