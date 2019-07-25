package com.gmail.maelgrove.SgcraftTlg.Server.Events;

import com.gmail.maelgrove.SgcraftTlg.PluginConfig;
import com.gmail.maelgrove.SgcraftTlg.PluginMessages;
import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.Methods.SendMessage;
import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.TelegramBot;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Represents a listener which listens for player events.
 */
public class PlayerEventListener implements Listener {

    private PluginConfig config;
    private TelegramBot bot;

    public PlayerEventListener(PluginConfig config, TelegramBot bot) {
        this.config = config;
        this.bot    = bot;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!bot.isReady() || !config.isPlayerEventsEnabled())
            return;
        bot.sendMessage(new SendMessage()
                .setChatId(config.getEventChatId())
                .setText(PluginMessages.formatMessage(PluginMessages.PLAYER_JOINED, e.getPlayer().getName())[0]));
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (!bot.isReady() || !config.isPlayerEventsEnabled())
            return;
        bot.sendMessage(new SendMessage()
                .setChatId(config.getEventChatId())
                .setText(PluginMessages.formatMessage(PluginMessages.PLAYER_DIED, e.getDeathMessage())[0]));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (!bot.isReady() || !config.isPlayerEventsEnabled())
            return;
        bot.sendMessage(new SendMessage()
                .setChatId(config.getEventChatId())
                .setText(PluginMessages.formatMessage(PluginMessages.PLAYER_LEFT, e.getPlayer().getName())[0]));
    }

}
