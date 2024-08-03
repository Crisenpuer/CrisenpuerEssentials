package org.eu.pl.crisenpuer.spigot.crisenpuer_essentials.JDA;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class ReportPlayer {
    
    static public void report(Player reporter, String reportedPlayerName, JDA jda, String reportsChannelId, String modsRoleId) {

        Player reported = Bukkit.getPlayerExact(reportedPlayerName);
        if (reported == null) {
            reporter.sendMessage("Player not found: " + reportedPlayerName);
            return;
        }

        Location reporterLoc = reporter.getLocation();
        Location reportedLoc = reported.getLocation();

        MessageEmbed embed = new EmbedBuilder()
            .setTitle("__**Zgłoszenie**__")
            .setDescription("**"+reported.getName()+"**\n"+reported.getWorld().getName()+"\nX: "+reportedLoc.getX()+"     Z: "+reportedLoc.getZ()+"     Y: "+reportedLoc.getY())
            .addField("__**Zgłaszający**__", "**"+reporter.getName()+"**\n"+reporter.getWorld().getName()+"\nX: "+reporterLoc.getX()+"     Z: "+reporterLoc.getZ()+"     Y: "+reporterLoc.getY(), false)
            .build();

        TextChannel reportsChannel = jda.getTextChannelById(reportsChannelId);
        if (reportsChannel != null) {
            reportsChannel.sendMessage("<&@" + modsRoleId + ">");
            reportsChannel.sendMessageEmbeds(embed);
        } else {
            Bukkit.getLogger().severe("Reports channel not found");
        }
    }

}
