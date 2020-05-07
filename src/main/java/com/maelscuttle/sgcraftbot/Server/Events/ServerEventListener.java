package com.maelscuttle.sgcraftbot.Server.Events;

import com.maelscuttle.sgcraftbot.PluginConfig;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerLoadEvent;

/**
 * Represents a listener which listens for server events.
 */
public class ServerEventListener implements Listener {

    private PluginConfig config;
    private TelegramBot bot;

    public ServerEventListener(PluginConfig config, TelegramBot bot) {
        this.config = config;
        this.bot    = bot;
    }

    @EventHandler
    public void onServerLoad(ServerLoadEvent e) {
        SendMessage sendMessage = new SendMessage(config.getEventChatId(), "Server has been started.");
        bot.execute(sendMessage);
    }

}
