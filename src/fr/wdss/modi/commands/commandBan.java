package fr.wdss.modi.commands;

import fr.wdss.modi.Main;
import fr.wdss.modi.utils.modiPermissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class commandBan implements CommandExecutor {
    public Main main;
    public commandBan(Main main) { this.main = main; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(label.equalsIgnoreCase("ban")){
            if(sender.hasPermission(new modiPermissions().ban) || sender.isOp() || Main.Staff.contains(sender)){
                if(args.length == 0){
                    sender.sendMessage(Main.errorCmd + "/ban [player] <reason>");
                    return false;
                }else {
                    Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
                    if(targetPlayer == null){
                        sender.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                        return false;
                    }

                    StringBuilder reason = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        reason.append(args[i] + " ");
                    }

                    FileConfiguration banConfig = YamlConfiguration.loadConfiguration(main.getFile("ban"));
                    if(banConfig.contains("Players." + targetPlayer + ".status")){
                        if(banConfig.getString("Players." + targetPlayer.getName() + ".status").equalsIgnoreCase("on")){
                            sender.sendMessage(Main.prefix_error + "Le joueur " + targetPlayer.getName() + " est déjà banni");
                            return false;
                        }else {
                            banConfig.set("Players." + targetPlayer.getName() + ".status", "on");
                            banConfig.set("Players." + targetPlayer.getName() + ".reason", reason.toString());
                            try {
                                banConfig.save(main.getFile("ban"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            sender.sendMessage(Main.prefix_success + "Le joueur §2" + targetPlayer.getName() + " §fvient d'être §9Banni");
                            targetPlayer.kickPlayer(reason.toString());
                            return true;
                        }
                    }else {
                        banConfig.set("Players." + targetPlayer.getName() + ".status", "on");
                        banConfig.set("Players." + targetPlayer.getName() + ".reason", reason.toString());
                        try {
                            banConfig.save(main.getFile("ban"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        sender.sendMessage(Main.prefix_success + "Le joueur §2" + targetPlayer.getName() + " §fvient d'être §9Banni");
                        targetPlayer.kickPlayer(reason.toString());
                        return true;
                    }
                }
            }else {
                sender.sendMessage(Main.errorPerm);
                return false;
            }
        }

        if(label.equalsIgnoreCase("unban")){
            if(sender.hasPermission(new modiPermissions().unban) || sender.isOp() || Main.Staff.contains(sender)){
                if(args.length == 0){
                    sender.sendMessage(Main.errorCmd + "/unban [player]");
                    return false;
                }else if(args.length == 1){
                    String targetPlayer = args[0];

                    FileConfiguration unbanConfig = YamlConfiguration.loadConfiguration(main.getFile("ban"));
                    if(unbanConfig.contains("Players." + targetPlayer + ".status")){
                        if(unbanConfig.getString("Players." + targetPlayer + ".status").equalsIgnoreCase("on")){
                            unbanConfig.set("Players." + targetPlayer + ".status", "off");
                            try {
                                unbanConfig.save(main.getFile("ban"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            sender.sendMessage(Main.prefix_success + "Le joueur §2" + targetPlayer + " §fvient d'être §9Débanni");
                            return true;
                        }else {
                            sender.sendMessage(Main.prefix_error + "Le joueur " + targetPlayer + " n'est pas banni");
                            return false;
                        }
                    }else {
                        sender.sendMessage(Main.prefix_error + "Le joueur " + targetPlayer + " n'est pas contenue dans les Bases de Données");
                        return false;
                    }
                }else{
                    sender.sendMessage(Main.errorCmd + "/unban [player]");
                    return false;
                }
            }else {
                sender.sendMessage(Main.errorPerm);
            }
        }

        return false;
    }
}
