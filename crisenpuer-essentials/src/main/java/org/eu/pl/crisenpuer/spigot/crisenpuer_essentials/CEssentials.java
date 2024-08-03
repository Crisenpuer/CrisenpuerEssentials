package org.eu.pl.crisenpuer.spigot.crisenpuer_essentials;

import java.util.EnumSet;
import java.awt.Color;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import org.eu.pl.crisenpuer.spigot.crisenpuer_essentials.JDA.JdaMessageListener;
import org.eu.pl.crisenpuer.spigot.crisenpuer_essentials.JDA.ReportPlayer;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class CEssentials extends JavaPlugin implements Listener {

    private String token;
    private String chatChannelId;
    private String reportsChannelId;
    private String mcModsRoleID;
    private String botPrefix;

    private JDA jda;

    public TextChannel chatChannel;

    @Override
    public void onEnable() {

        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();

        this.token = config.getString("jda.token");
        this.chatChannelId = config.getString("jda.channels.chat");
        this.reportsChannelId = config.getString("jda.channels.reports");
        this.mcModsRoleID = config.getString("jda.roles.mc_mod");
        this.botPrefix = config.getString("jda.bot_prefix");

        try {
            this.jda = JDABuilder.createLight(token, EnumSet.noneOf(GatewayIntent.class))
                .addEventListeners(new JdaMessageListener(this.botPrefix))
                .build()
                .awaitReady();
            getLogger().info("JDA Initialized!");

            this.chatChannel = this.jda.getTextChannelById(chatChannelId);

            if (this.chatChannel != null) {
                MessageEmbed embed = new EmbedBuilder()
                    .setDescription("Serwer jest znowu online!")
                    .setColor(Color.getColor("#00ff00"))
                    .build();
                this.chatChannel.sendMessageEmbeds(embed);
            } else {
                getLogger().warning("Chat channel with id (" + chatChannelId + ") could not be found!");
            }

        } catch (InterruptedException e) {
            getLogger().severe("Failed to initialize JDA!");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        if (this.jda != null) {
            if (this.chatChannel != null) {
                MessageEmbed embed = new EmbedBuilder()
                .setDescription("Serwer jest teraz offline")
                .setColor(Color.getColor("#ff0000"))
                .build();
                this.chatChannel.sendMessageEmbeds(embed);
            }
            this.jda.shutdown();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("menu")) {
            ServerMenu.openMenu((Player) sender);
        } else if (command.getName().equalsIgnoreCase("report")) {
            ReportPlayer.report((Player) sender, args[0], this.jda, reportsChannelId, mcModsRoleID);
        }
        return false;
    }

    public JDA getJda() {
        return this.jda;
    }

}
