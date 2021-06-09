package fr.wdss.modi.commands;

import fr.wdss.modi.Main;
import fr.wdss.modi.utils.modiPermissions;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class commandMod implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Main.notAPlayer);
        }

        Player p = (Player) sender;

        if(label.equalsIgnoreCase("mod")){
            if(p.hasPermission(new modiPermissions().admin) || p.isOp()){
                if(args.length == 0){
                    p.sendMessage(Main.errorCmd + "/mod <player>");
                    return false;
                }else if(args.length == 1){
                    Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);

                    if(targetPlayer == null){
                        p.sendMessage(Main.prefix_error + "Veuilliez mettre un joueur en ligne sur le serveur !");
                        return false;
                    }

                    Player target = targetPlayer;

                    int scale = 5*9;
                    Inventory modStaffInventory = Bukkit.createInventory(null, scale, target.getName());

                    //Add of All Items in the inventory
                    ItemStack kick = new ItemStack(Material.BOW, 1);
                    ItemMeta kickmeta = kick.getItemMeta();
                    kickmeta.setDisplayName("§6Kick");
                    kick.setItemMeta(kickmeta);
                    modStaffInventory.setItem(0, kick);

                    ItemStack ban = new ItemStack(Material.REDSTONE, 1);
                    ItemMeta banmeta = ban.getItemMeta();
                    banmeta.setDisplayName("§6Ban");
                    ban.setItemMeta(banmeta);
                    modStaffInventory.setItem(8, ban);

                    ItemStack mute = new ItemStack(Material.ANVIL, 1);
                    ItemMeta mutemeta = mute.getItemMeta();
                    mutemeta.setDisplayName("§6Mute");
                    mute.setItemMeta(mutemeta);
                    modStaffInventory.setItem(2, mute);

                    ItemStack mp = new ItemStack(Material.PAPER, 1);
                    ItemMeta mpmeta = mp.getItemMeta();
                    mpmeta.setDisplayName("§6MP - Discord");
                    mp.setItemMeta(mpmeta);
                    modStaffInventory.setItem(4, mp);

                    ItemStack unmute = new ItemStack(Material.DAMAGED_ANVIL, 1);
                    ItemMeta unmutemeta = unmute.getItemMeta();
                    unmutemeta.setDisplayName("§6UnMute");
                    unmute.setItemMeta(unmutemeta);
                    modStaffInventory.setItem(6, unmute);


                    ItemStack gms = new ItemStack(Material.GRASS_BLOCK, 1);
                    ItemMeta gmsm = gms.getItemMeta();
                    gmsm.setDisplayName("§6Gamemode §a0");
                    gms.setItemMeta(gmsm);
                    modStaffInventory.setItem(11, gms);

                    ItemStack gmc = new ItemStack(Material.BEDROCK, 1);
                    ItemMeta gmcm = gmc.getItemMeta();
                    gmcm.setDisplayName("§6Gamemode §a1");
                    gmc.setItemMeta(gmcm);
                    modStaffInventory.setItem(12, gmc);

                    ItemStack gma = new ItemStack(Material.OAK_WOOD, 1);
                    ItemMeta gmam = gma.getItemMeta();
                    gmam.setDisplayName("§6Gamemode §a2");
                    gma.setItemMeta(gmam);
                    modStaffInventory.setItem(14, gma);

                    ItemStack gmsp = new ItemStack(Material.BARRIER, 1);
                    ItemMeta gmspm = gmsp.getItemMeta();
                    gmspm.setDisplayName("§6Gamemode §a3");
                    gmsp.setItemMeta(gmspm);
                    modStaffInventory.setItem(15, gmsp);

                    ItemStack gto = new ItemStack(Material.ENDER_PEARL, 1);
                    ItemMeta gtometa = gto.getItemMeta();
                    gtometa.setDisplayName("§6Goto");
                    gtometa.setLore(Arrays.asList(p.getDisplayName()));
                    gto.setItemMeta(gtometa);
                    modStaffInventory.setItem(28, gto);

                    ItemStack k = new ItemStack(Material.DIAMOND_SWORD, 1);
                    ItemMeta kmeta = k.getItemMeta();
                    kmeta.setDisplayName("§6Kill");
                    k.setItemMeta(kmeta);
                    modStaffInventory.setItem(30, k);

                    ItemStack hu = new ItemStack(Material.LAVA_BUCKET, 1);
                    ItemMeta humeta = hu.getItemMeta();
                    humeta.setDisplayName("§6Clear");
                    hu.setItemMeta(humeta);
                    modStaffInventory.setItem(32, hu);

                    ItemStack bring = new ItemStack(Material.ENDER_EYE, 1);
                    ItemMeta bringmeta = gto.getItemMeta();
                    bringmeta.setDisplayName("§6Bring");
                    bringmeta.setLore(Arrays.asList(p.getDisplayName()));
                    bring.setItemMeta(bringmeta);
                    modStaffInventory.setItem(34, bring);

                    ItemStack va = new ItemStack(Material.GLASS, 1);
                    ItemMeta vameta = va.getItemMeta();
                    vameta.setDisplayName("§6Vanish");
                    vameta.setLore(Arrays.asList(p.getDisplayName()));
                    va.setItemMeta(vameta);
                    modStaffInventory.setItem(40, va);

                    ItemStack sa = new ItemStack(Material.SPRUCE_LOG, 1);
                    ItemMeta sameta = sa.getItemMeta();
                    sameta.setDisplayName("§6Salle Modération");
                    sameta.setLore(Arrays.asList(p.getDisplayName()));
                    sa.setItemMeta(sameta);
                    modStaffInventory.setItem(44, sa);

                    //Opening of the Inventory
                    p.openInventory(modStaffInventory);
                    return true;
                }else {
                    p.sendMessage(Main.errorCmd + "/mod <player>");
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
