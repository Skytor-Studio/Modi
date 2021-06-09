package fr.wdss.modi.commands;

import fr.wdss.modi.Main;
import fr.wdss.modi.utils.modiPermissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandFreeze implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Main.notAPlayer);
        }
        Player p = (Player) sender;

        if(label.equalsIgnoreCase("freeze")){
            if(p.hasPermission(new modiPermissions().freeze) || p.isOp()){
                if(args.length == 0){
                    p.sendMessage(Main.errorCmd + "/freeze <on/off/[player]> [player]");
                    return false;
                }else if(args.length == 1){
                    if(args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("off")){
                        p.sendMessage(Main.errorCmd + "/freeze <on/off/[player]> [player]");
                        return false;
                    }else {
                        Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
                        if(targetPlayer == null){
                            p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                            return false;
                        }
                        String target = targetPlayer.getName();

                        if(Main.Freeze.contains(target)){
                            Main.Freeze.remove(target);
                            if(Main.Freeze.contains(target)){
                                p.sendMessage(Main.prefix_error + "Il y a eu une erreur, merci de réessayer");
                                return false;
                            }else {
                                p.sendMessage(Main.prefix + "§aLe joueur : §6" + target + " §aà bien été §6unfreeze §a!");
                                return true;
                            }
                        }else {
                            Main.Freeze.add(target);
                            if(Main.Freeze.contains(target)){
                                p.sendMessage(Main.prefix + "§aLe joueur : §6" + target + " §aà bien été §6freeze §a!");
                                return true;
                            }else {
                                p.sendMessage(Main.prefix_error + "Il y a eu une erreur, merci de réessayer");
                                return false;
                            }
                        }
                    }
                }else if(args.length == 2){
                    if(args[0].equalsIgnoreCase("on")){
                        Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
                        if(targetPlayer == null){
                            p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                            return false;
                        }
                        String target = targetPlayer.getName();

                        if(Main.Freeze.contains(target)){
                            p.sendMessage(Main.prefix_error + "Le joueur : " + target + " est déja freeze !");
                            return false;
                        }else {
                            Main.Freeze.add(target);
                            if(Main.Freeze.contains(target)){
                                p.sendMessage(Main.prefix + "§aLe joueur : §6" + target + " §aà bien été §6freeze §a!");
                                return true;
                            }else {
                                p.sendMessage(Main.prefix_error + "Il y a eu une erreur, merci de réessayer");
                                return false;
                            }
                        }
                    }else if(args[0].equalsIgnoreCase("off")){
                        Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
                        if(targetPlayer == null){
                            p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                            return false;
                        }
                        String target = targetPlayer.getName();

                        if(Main.Freeze.contains(target)){
                            Main.Freeze.remove(target);
                            if(Main.Freeze.contains(target)){
                                p.sendMessage(Main.prefix_error + "Il y a eu une erreur, merci de réessayer");
                                return false;
                            }else {
                                p.sendMessage(Main.prefix + "§aLe joueur : §6" + target + " §aà bien été §6unfreeze §a!");
                                return true;
                            }
                        }else {
                            p.sendMessage(Main.prefix_error + "Le joueur : " + target + " est déja freeze !");
                            return false;
                        }
                    }else {
                        p.sendMessage(Main.errorCmd + "/freeze <on/off/[player]> [player]");
                        return false;
                    }
                }else {
                    p.sendMessage(Main.errorCmd + "/freeze <on/off/[player]> [player]");
                    return false;
                }
            }else {
                p.sendMessage(Main.errorCmd + "/freeze <on/off/[player]> [player]");
                return false;
            }
        }

        return false;
    }
}
