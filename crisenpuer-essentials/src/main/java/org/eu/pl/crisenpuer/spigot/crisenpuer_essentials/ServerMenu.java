package org.eu.pl.crisenpuer.spigot.crisenpuer_essentials;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ServerMenu {
    
    public static void openMenu(Player player) {

        Inventory menu = Bukkit.createInventory(null, 54, "Touching Mentally Jawfish");

        ItemStack shopMenuItem = createItemWithName(Material.EMERALD, "Sklep");
        ItemStack hubWarpItem = createItemWithName(Material.COMPASS, "Idź do HUB");

        menu.setItem(39, shopMenuItem);
        menu.setItem(41, hubWarpItem);

        player.openInventory(menu);
    }

    public static ItemStack createItemWithName(Material type, String name) {
        ItemStack itemStack = new ItemStack(type);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta); 
        return itemStack;
    }

}
