package fr.wdss.modi;

import fr.wdss.modi.commands.*;
import fr.wdss.modi.listeners.mainListener;
import fr.wdss.modi.listeners.modListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {
    public static String version = "1.0";
    public static String prefix = " §6Modi §7=> §f";
    public static String prefix_error = " §6Modi§7/§4Error §7=> §c";
    public static String prefix_success = "§f► ";

    public static String notAPlayer = prefix_error + "Vous n'éte pas un joueur donc vous ne pouvez pas utiliser cette commande ! ";
    public static String errorPerm = prefix_error + "Tu n'as pas la permissions d'utiliser cette commande !";
    public static String errorCmd = prefix_error + "La commande est : §e";

    public static ArrayList<String> Mute = new ArrayList<String> ();
    public static ArrayList<String> Freeze = new ArrayList<String> ();
    public static Boolean Maintenance = false;

    @Override
    public void onEnable() {
        System.out.println(prefix + "Enable of Modi Moderation in version : " + version);
        System.out.println(prefix + "Made by Wabezeter, Director of W.D.S.S. !");

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new mainListener(this), this);
        getServer().getPluginManager().registerEvents(new modListener(this), this);

        getCommand("mod").setExecutor(new commandMod());
        getCommand("chat").setExecutor(new commandChat());
        getCommand("freeze").setExecutor(new commandFreeze());
        getCommand("maintenance").setExecutor(new commandMaintenance());
        getCommand("gm").setExecutor(new commandGM());
    }

    @Override
    public void onDisable() {
        System.out.println(prefix + "Disable of Modi Moderation in version : " + version);
        System.out.println(prefix + "Made by Wabezeter, Director of W.D.S.S. !");
    }

}
