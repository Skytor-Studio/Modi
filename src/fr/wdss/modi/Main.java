package fr.wdss.modi;

import fr.wdss.modi.commands.*;
import fr.wdss.modi.listeners.mainListener;
import fr.wdss.modi.listeners.modListener;
import fr.wdss.modi.utils.modiPermissions;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JavaPlugin {
    public static String version = "1.3";
    public static String prefix = " §6Modi §7=> §f";
    public static String prefix_error = " §6Modi/§4Error §7=> §c";
    public static String prefix_success = "§f► ";

    public static String notAPlayer = prefix_error + "Vous n'êtes pas un joueur donc vous ne pouvez pas utiliser cette commande ! ";
    public static String errorPerm = prefix_error + "Tu n'as pas la permissions d'utiliser cette commande !";
    public static String errorCmd = prefix_error + "La commande est : §e";

    public static ArrayList<String> Mute = new ArrayList<String> ();
    public static ArrayList<String> Freeze = new ArrayList<String> ();
    public static ArrayList<Player> Staff = new ArrayList<Player>();
    public static Boolean Maintenance = false;
    private Object ItemStack;

    @Override
    public void onEnable() {
        if(getConfig().getString("Mod.maintenance").equalsIgnoreCase("on")){
            Maintenance = true;
        }
        System.out.println("Modi => Enable of Modi Moderation in version : " + version);
        System.out.println("Modi => Made by Wabezeter, Director of W.D.S.S. !");

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new mainListener(this), this);
        getServer().getPluginManager().registerEvents(new modListener(this), this);

        getCommand("staff").setExecutor(new commandStaff(this));
        getCommand("chat").setExecutor(new commandChat());
        getCommand("freeze").setExecutor(new commandFreeze());
        getCommand("maintenance").setExecutor(new commandMaintenance(this));
        getCommand("gm").setExecutor(new commandGM());
        getCommand("tablist").setExecutor(new commandTablist(this));
        getCommand("dm").setExecutor(new commandDM());
        getCommand("ban").setExecutor(new commandBan(this));
        getCommand("unban").setExecutor(new commandBan(this));

        createFile("staff");
        createFile("ban");
    }

    @Override
    public void onDisable() {
        System.out.println("Modi => Disable of Modi Moderation in version : " + version);
        System.out.println("Modi => Made by Wabezeter, Director of W.D.S.S. !");
    }

    public void createFile(String fileName){
        File file = new File(getDataFolder(), fileName + ".yml");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public File getFile(String fileName){ return new File(getDataFolder(), fileName + ".yml"); }

    public Object createItemStackStaff(Material material, String name){
        ItemStack itemstack = new ItemStack(material, 1);
        ItemMeta itemMeta = itemstack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemstack.setItemMeta(itemMeta);
        return itemstack;
    }

}
