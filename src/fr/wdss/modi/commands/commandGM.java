package fr.wdss.modi.commands;

import fr.wdss.modi.Main;
import fr.wdss.modi.utils.modiPermissions;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandGM implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Main.notAPlayer);
        }

        Player p = (Player) sender;

        if(label.equalsIgnoreCase("gm")){
            if(p.isOp() || p.hasPermission(new modiPermissions().gm)){
                if(args.length == 0){
                    p.sendMessage(Main.errorCmd + "/gm <0/1/2/3/s/c/a/sp/[player]> <0/1/2/3/s/c/a/sp>");
                    return false;
                }else if(args.length == 1){
                    if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")){
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(Main.prefix_success + "Votre mode de jeu a été défini sur §9SURVIVAL");
                        return true;
                    }else if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")){
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(Main.prefix_success + " Votre mode de jeu a été défini sur §9CREATIVE");
                        return true;
                    }else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")){
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage(Main.prefix_success + "Votre mode de jeu a été défini sur §9ADVENTURE");
                        return true;
                    }else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("sp")){
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage(Main.prefix_success + "Votre mode de jeu a été défini sur §9SPECTATOR");
                        return true;
                    }else {
                        p.sendMessage(Main.errorCmd + "/gm <0/1/2/3/s/c/a/sp/[player]> <0/1/2/3/s/c/a/sp>");
                        return false;
                    }
                }else if(args.length == 2){
                    Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
                    if(targetPlayer == null){
                        p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                        return false;
                    }
                    Player target = targetPlayer;

                    if(args[1].equalsIgnoreCase("0") || args[1].equalsIgnoreCase("s")){
                        target.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(Main.prefix_success + "Le mode de jeu de " + target.getDisplayName() + " §fa été défini sur §9SURVIVAL");
                        target.sendMessage(Main.prefix_success + "Votre mode de jeu a été défini sur §9SURVIVAL");
                        return true;
                    }else if(args[1].equalsIgnoreCase("1") || args[1].equalsIgnoreCase("c")){
                        target.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(Main.prefix_success + "Le mode de jeu de §2" + target.getDisplayName() + " §fa été défini sur §9CREATIVE");
                        target.sendMessage(Main.prefix_success + "Votre mode de jeu a été défini sur §9CREATIVE");
                        return true;
                    }else if(args[1].equalsIgnoreCase("2") || args[1].equalsIgnoreCase("a")){
                        target.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage(Main.prefix_success + "Le mode de jeu de §2" + target.getDisplayName() + " §fa été défini sur §9ADVENTURE");
                        target.sendMessage(Main.prefix_success + "Votre mode de jeu a été défini sur §9ADVENTURE");
                        return true;
                    }else if(args[1].equalsIgnoreCase("3") || args[1].equalsIgnoreCase("sp")){
                        target.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage(Main.prefix_success + "Le mode de jeu de §2" + target.getDisplayName() + " §fa été défini sur §9SPECTATOR");
                        target.sendMessage(Main.prefix_success + "Votre mode de jeu a été défini sur §9SPECTATOR");
                        return true;
                    }else {
                        p.sendMessage(Main.errorCmd + "/gm <0/1/2/3/s/c/a/sp/[player]> <0/1/2/3/s/c/a/sp>");
                        return false;
                    }
                }else {
                    p.sendMessage(Main.errorCmd + "/gm <0/1/2/3/s/c/a/sp/[player]> <0/1/2/3/s/c/a/sp>");
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
