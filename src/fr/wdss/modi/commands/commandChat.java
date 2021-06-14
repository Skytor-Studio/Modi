package fr.wdss.modi.commands;

import fr.wdss.modi.Main;
import fr.wdss.modi.utils.modiPermissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandChat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Main.notAPlayer);
        }

        Player p = (Player) sender;

        if(label.equalsIgnoreCase("chat")){
            if(p.hasPermission(new modiPermissions().chat) || p.isOp()){
                if(args.length == 0){
                    p.sendMessage(Main.errorCmd + "/chat <clear/mute/unmute> [player]");
                    return false;
                }else if(args.length == 1){
                    if(args[0].equalsIgnoreCase("clear")){
                        Integer i = 0;
                        while(i <= 25){
                            Bukkit.broadcastMessage(" ");
                            i = i + 1;
                        }
                        p.sendMessage(Main.prefix_success + "Le chat a bien été §9Efface");
                        return true;
                    }else {
                        p.sendMessage(Main.errorCmd + "/chat <clear/mute/unmute> [player]");
                        return false;
                    }
                }else if(args.length == 2){
                    if(args[0].equalsIgnoreCase("mute")){
                        if(p.hasPermission(new modiPermissions().mute) || p.isOp()){
                            Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
                            if(targetPlayer == null){
                                p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                                return false;
                            }
                            String target = targetPlayer.getName();

                            if(Main.Mute.contains(target)){
                                p.sendMessage(Main.prefix_error + "Le joueur " + targetPlayer.getDisplayName() + " est deja mute");
                                return false;
                            }else {
                                Main.Mute.add(target);
                                if(Main.Mute.contains(target)){
                                    p.sendMessage(Main.prefix_success + "Le joueur " + targetPlayer.getDisplayName() + " a ete §9Mute");
                                    return true;
                                }else {
                                    p.sendMessage(Main.prefix_error + "Il y a eu une erreur, merci de reessayer");
                                    return false;
                                }
                            }
                        }else {
                            p.sendMessage(Main.errorPerm);
                            return false;
                        }
                    }else if(args[0].equalsIgnoreCase("unmute")){
                        if(p.hasPermission(new modiPermissions().mute) || p.isOp()){
                            Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
                            if(targetPlayer == null){
                                p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                                return false;
                            }
                            String target = targetPlayer.getName();

                            if(!(Main.Mute.contains(target))){
                                p.sendMessage(Main.prefix_error + "Le joueur " + targetPlayer.getDisplayName() + " n'est pas mute");
                                return false;
                            }else {
                                Main.Mute.remove(target);
                                if(Main.Mute.contains(target)){
                                    p.sendMessage(Main.prefix_error + "Il y a eu une erreur, merci de reessayer");
                                    return false;
                                }else {
                                    p.sendMessage(Main.prefix_success + "Le joueur " + targetPlayer.getDisplayName() + " a ete §9Unmmute");
                                    return true;
                                }
                            }
                        }else {
                            p.sendMessage(Main.errorPerm);
                            return false;
                        }
                    }else {
                        p.sendMessage(Main.errorCmd + "/chat <clear/mute/unmute> [player]");
                        return false;
                    }
                }else {
                    p.sendMessage(Main.errorCmd + "/chat <clear/mute/unmute> [player]");
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
