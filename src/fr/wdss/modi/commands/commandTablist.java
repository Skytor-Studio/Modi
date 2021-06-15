package fr.wdss.modi.commands;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import fr.wdss.modi.Main;
import fr.wdss.modi.utils.modiPermissions;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.org.apache.commons.codec.binary.Base64;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class commandTablist implements CommandExecutor {
    public Main main;
    public commandTablist(Main main) { this.main = main; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Main.notAPlayer);
        }
        Player p = (Player) sender;

        if(label.equalsIgnoreCase("tablist")){
            if(p.hasPermission(new modiPermissions().tablist) || p.isOp()){
                if(args.length == 0){
                    int scale = 5*9;
                    Inventory tablistInventory = Bukkit.createInventory(null, scale, "Tablist");
                    int i = 0;
                    for(Player playersOnlines : Bukkit.getOnlinePlayers()){
                        ItemStack player = new ItemStack(Material.PLAYER_HEAD, 1);
                        ItemMeta playerMeta = player.getItemMeta();
                        playerMeta.setDisplayName(playersOnlines.getName());
                        player.setItemMeta(playerMeta);
                        tablistInventory.setItem(i, player);
                        System.out.println(i);
                        i = i + 1;
                        System.out.println(i);
                    }

                    p.openInventory(tablistInventory);
                    return true;
                }else if(args.length == 1){
                    Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
                    if(targetPlayer == null){
                        p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                        return false;
                    }

                    if(Main.Staff.contains(p)){
                        //INV MODERATION
                        int scale = 6*9;
                        Inventory tablistInventoryModeration = Bukkit.createInventory(null, scale, targetPlayer.getName());

                        tablistInventoryModeration.setItem(0, (ItemStack) main.createItemStackStaff(Material.PAPER, "§fDirect Message"));
                        tablistInventoryModeration.setItem(10, (ItemStack) main.createItemStackStaff(Material.NETHERITE_AXE, "§cBan"));
                        tablistInventoryModeration.setItem(13, (ItemStack) main.createItemStackStaff(Material.PAPER, "§9Mute"));
                        tablistInventoryModeration.setItem(16, (ItemStack) main.createItemStackStaff(Material.ANVIL, "§6Kick"));
                        tablistInventoryModeration.setItem(20, (ItemStack) main.createItemStackStaff(Material.COBBLESTONE, "§8GM 0"));
                        tablistInventoryModeration.setItem(21, (ItemStack) main.createItemStackStaff(Material.GRASS_BLOCK, "§2GM 1"));
                        tablistInventoryModeration.setItem(23, (ItemStack) main.createItemStackStaff(Material.SPRUCE_LOG, "§cGM 2"));
                        tablistInventoryModeration.setItem(24, (ItemStack) main.createItemStackStaff(Material.BARRIER, "§fGM 3"));
                        tablistInventoryModeration.setItem(30, (ItemStack) main.createItemStackStaff(Material.ENDER_PEARL, "§fGoto"));
                        tablistInventoryModeration.setItem(32, (ItemStack) main.createItemStackStaff(Material.ENDER_EYE, "§fBring"));
                        tablistInventoryModeration.setItem(49, (ItemStack) main.createItemStackStaff(Material.GLASS, "§fVanish"));
                        tablistInventoryModeration.setItem(53, (ItemStack) main.createItemStackStaff(Material.BEDROCK, "§9Salle de Modération"));

                        p.openInventory(tablistInventoryModeration);
                        return true;
                    }else {
                        int scale = 1*9;
                        Inventory tablistInventory = Bukkit.createInventory(null, scale, targetPlayer.getName());

                        tablistInventory.setItem(0, (ItemStack) main.createItemStackStaff(Material.PAPER, "§fDirect Message"));

                        p.openInventory(tablistInventory);
                        return true;
                    }

                }else {
                    p.sendMessage(Main.errorCmd + "/tablist [player]");
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
