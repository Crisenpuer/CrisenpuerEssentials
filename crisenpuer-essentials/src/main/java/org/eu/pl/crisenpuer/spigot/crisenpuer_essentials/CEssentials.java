package org.eu.pl.crisenpuer.spigot.crisenpuer_essentials;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.json.JSONArray;
import org.json.JSONObject;

public class CEssentials extends JavaPlugin implements Listener {

    private String token = "MTIwOTg4MTE5NTg1MzM4NTgzOA.GGWluo.2FakhlYDmevOOXpAsCNe8PhYV_0kGpQT8_bQxM";
    private String channelId = "YOUR_DISCORD_CHANNEL_ID";
    private JDA jda;

    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");

        try {
            this.jda = JDABuilder.createLight(token, EnumSet.noneOf(GatewayIntent.class)).build().awaitReady();
            getLogger().info("JDA Initialized!");
        } catch (InterruptedException e) {
            e.printStackTrace();
            getLogger().severe("Failed to initialize JDA!");
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
        if (this.jda != null) {
            this.jda.shutdown();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("menu")) {
            openMenu((Player) sender);
        }
        return false;
    }

    public void openMenu(Player player) {
        
        Inventory menu = Bukkit.createInventory(null, 54, "Touching Mentally Jawfish");

        ItemStack shopMenuItem = createItemWithName(Material.EMERALD, "Sklep");
        ItemStack hubWarpItem = createItemWithName(Material.COMPASS, "Idź do HUB");

        menu.setItem(39, shopMenuItem);
        menu.setItem(41, hubWarpItem);

        player.openInventory(menu);
    }

    private ItemStack createItemWithName(Material type, String name) {
        ItemStack itemStack = new ItemStack(type);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta); 
        return itemStack;
    }

}
