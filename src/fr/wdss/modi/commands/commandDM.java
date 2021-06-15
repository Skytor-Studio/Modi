package fr.wdss.modi.commands;

import fr.wdss.modi.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandDM implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Main.notAPlayer);
        }

        Player p = (Player) sender;

        if(label.equalsIgnoreCase("dm")){
            if(args.length <= 1){
                p.sendMessage(Main.prefix_error + "/dm [player] <votre_message>");
                return false;
            }else {
                Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
                if(targetPlayer == null){
                    sender.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                    return false;
                }

                StringBuilder dm = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    dm.append(args[i] + " ");
                }

                targetPlayer.sendMessage("§8[§f" + p.getName() + " §7> §f" + targetPlayer.getName() + "§8]  §f► " + dm.toString());
                p.sendMessage("§8[§f" + p.getName() + " §7> §f" + targetPlayer.getName() + "§8]  §f► " + dm.toString());

                for (Player playersOnline : Bukkit.getOnlinePlayers()){
                    if(Main.Staff.contains(playersOnline)){
                        playersOnline.sendMessage("§8[§9DM§8] §f" + p.getName() + " §7> §f" + targetPlayer.getName() + "  §f► " + dm.toString());
                    }
                }



            }
        }

        return false;
    }
}
