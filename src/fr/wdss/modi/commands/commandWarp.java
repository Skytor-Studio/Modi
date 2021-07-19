package fr.wdss.modi.commands;

import fr.wdss.modi.Main;
import fr.wdss.modi.utils.modiPermissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class commandWarp implements CommandExecutor {
    public Main main;
    public commandWarp(Main main) { this.main = main; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Main.notAPlayer);
        }

        Player p = (Player) sender;

        if(label.equalsIgnoreCase("warp")){
            if(args.length == 0){
                p.sendMessage(Main.errorCmd + "/warp <set/[args]> [name_of_warp] <true>");
                return false;
            }else if(args.length == 1){
                if(args[0].equalsIgnoreCase("set")){
                    p.sendMessage(Main.errorCmd + "/warp <set/[args]> [name_of_warp] <true>");
                    return false;
                }else {
                    String warpName = args[0];
                    FileConfiguration warpConfiguration = YamlConfiguration.loadConfiguration(main.getFile("warp"));
                    if(warpConfiguration.contains("Warp." + warpName)){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "teleport " + p.getName() + " " + warpConfiguration.getString("Warp." + warpName));
                        p.sendMessage(Main.prefix_success + "Téléportation vers: §9" + warpName);
                    }else{
                        p.sendMessage(Main.prefix_error + "Ce warp n'existe pas");
                        return false;
                    }
                }
            }else if(args.length == 2){
                if(args[0].equalsIgnoreCase("set")){
                    if(p.hasPermission(new modiPermissions().warp) || p.isOp()){
                        String warpName = args[1];
                        FileConfiguration warpConfiguration = YamlConfiguration.loadConfiguration(main.getFile("warp"));
                        if(warpConfiguration.contains("Warp." + warpName)){
                            p.sendMessage(Main.prefix_error + "Ce warp est déja existant");
                            return false;
                        }else{
                            warpConfiguration.set("Warp." + warpName, p.getLocation().getX() + " " + p.getLocation().getY() + " " + p.getLocation().getZ());
                            p.sendMessage(Main.prefix_success + "Vous venez de creer le warp: §9" + warpName);
                            try {
                                warpConfiguration.save(main.getFile("warp"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }else {
                        p.sendMessage(Main.errorPerm);
                        return false;
                    }
                }else {
                    p.sendMessage(Main.errorCmd + "/warp <set/[args]> [name_of_warp] <true>");
                    return false;
                }
            }else if(args.length == 3){
                if(args[0].equalsIgnoreCase("set")){
                    if(p.hasPermission(new modiPermissions().warp) || p.isOp()){
                        String warpName = args[1];
                        FileConfiguration warpConfiguration = YamlConfiguration.loadConfiguration(main.getFile("warp"));
                        if(warpConfiguration.contains("Warp." + warpName)){
                            if(args[2].equalsIgnoreCase("true")){
                                warpConfiguration.set("Warp." + warpName, p.getLocation().getX() + " " + p.getLocation().getY() + " " + p.getLocation().getZ());
                                p.sendMessage(Main.prefix_success + "Vous venez de creer le warp: §9" + warpName);
                                try {
                                    warpConfiguration.save(main.getFile("warp"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }else {
                                p.sendMessage(Main.prefix_error + "Ce warp est déja existant");
                                return false;
                            }
                        }else{
                            warpConfiguration.set("Warp." + warpName, p.getLocation().getX() + " " + p.getLocation().getY() + " " + p.getLocation().getZ());
                            p.sendMessage(Main.prefix_success + "Vous venez de creer le warp: §9" + warpName);
                            try {
                                warpConfiguration.save(main.getFile("warp"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }else {
                        p.sendMessage(Main.errorPerm);
                        return false;
                    }
                }else {
                    p.sendMessage(Main.errorCmd + "/warp <set/[args]> [name_of_warp] <true>");
                    return false;
                }
            }else {
                p.sendMessage(Main.errorCmd + "/warp <set/[args]> [name_of_warp] <true>");
                return false;
            }
        }

        return false;
    }
}
