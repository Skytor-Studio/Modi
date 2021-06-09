package fr.wdss.modi.commands;

import fr.wdss.modi.Main;
import fr.wdss.modi.utils.modiPermissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandMaintenance implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("maintenance")){
            if(sender.hasPermission(new modiPermissions().maintenance) || sender.isOp()){
                if(args.length == 0){
                    sender.sendMessage(Main.errorCmd + "/maintenance <on/off>");
                    return false;
                }else if(args.length == 1){
                    if(args[0].equalsIgnoreCase("on")){
                        if(Main.Maintenance == false){
                            Main.Maintenance = true;
                            if(Main.Maintenance == true){
                                sender.sendMessage(Main.prefix + "§aLa §6Maintenance§a, à bien été §6activé §a!");
                                return true;
                            }else {
                                sender.sendMessage(Main.prefix_error + "Il y a eu une erreur, merci de réessayer");
                                return false;
                            }
                        }else {
                            sender.sendMessage(Main.prefix_error + "La §6Maintenance§c, est déja activé !");
                            return false;
                        }
                    }else if(args[0].equalsIgnoreCase("off")){
                        if(Main.Maintenance == true){
                            Main.Maintenance = false;
                            if(Main.Maintenance == false){
                                sender.sendMessage(Main.prefix + "§aLa §6Maintenance§a, à bien été §6désactivé §a!");
                                return true;
                            }else{
                                sender.sendMessage(Main.prefix_error + "Il y a eu une erreur, merci de réessayer");
                                return false;
                            }
                        }else {
                            sender.sendMessage(Main.prefix_error + "La §6Maintenance§c, est déja désactivé !");
                            return false;
                        }
                    }else {
                        sender.sendMessage(Main.errorCmd + "/maintenance <on/off>");
                        return false;
                    }
                }else {
                    sender.sendMessage(Main.errorCmd + "/maintenance <on/off>");
                    return false;
                }
            }else {
                sender.sendMessage(Main.errorPerm);
                return false;
            }
        }

        return false;
    }
}