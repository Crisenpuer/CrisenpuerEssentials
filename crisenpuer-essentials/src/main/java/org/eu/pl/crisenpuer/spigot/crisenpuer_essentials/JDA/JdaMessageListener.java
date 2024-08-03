package org.eu.pl.crisenpuer.spigot.crisenpuer_essentials.JDA;

import java.awt.Color;

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;
import org.eu.pl.crisenpuer.spigot.crisenpuer_essentials.utils.ArrayUtils;
import org.eu.pl.crisenpuer.spigot.crisenpuer_essentials.utils.StringUtils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class JdaMessageListener extends ListenerAdapter {
    
    private final String botPrefix;

    public JdaMessageListener(String botPrefix) {
        this.botPrefix = botPrefix;
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        
        Message message = event.getMessage();
        String content = message.getContentRaw();

        if (!content.startsWith(this.botPrefix)) {
            return;
        }

        String[] args = content.split(" ");

        String cmd = StringUtils.removePrefix(args[0], this.botPrefix);
        ArrayUtils<String> stringUtils = new ArrayUtils<>();
        args = stringUtils.removeFirstElement(args);

        switch (cmd) {
            case "reportchannel":
                MessageEmbed res = setReportChannel(args);

                break;
        
            default:
                break;
        }
    }

    private MessageEmbed setReportChannel(String[] args) {
        if (args.length != 1) {
            MessageEmbed embed = new EmbedBuilder()
                .setTitle("Usage")
                .setDescription(botPrefix + "reportchannel <target channel>")
                .setColor(Color.getColor("#ffff00"))
                .build();
            return embed;
        }

        Bukkit.getLogger().info(args[0]);

        return null;
    }



}