package com.gmail.maelgrove.SgcraftTlg.Server.Events;

import com.gmail.maelgrove.SgcraftTlg.PluginConfig;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Represents a listener which listens for player events.
 */
public class PlayerEventListener implements Listener {


    private static final String PLAYER_JOINED = "%s joined the game.";

    private static final String PLAYER_LEFT = "%s left the game.";

    private static final String PLAYER_DIED = "%s";

    private PluginConfig config;
    private TelegramBot bot;

    public PlayerEventListener(PluginConfig config, TelegramBot bot) {
        this.config = config;
        this.bot    = bot;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!config.isPlayerEventsEnabled())
            return;

        SendMessage sendMessage = new SendMessage(config.getEventChatId(), String.format(PLAYER_JOINED, e.getPlayer().getName()));
        bot.execute(sendMessage);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (!config.isPlayerEventsEnabled())
            return;

        SendMessage sendMessage = new SendMessage(config.getEventChatId(), String.format(PLAYER_DIED, e.getDeathMessage()));
        bot.execute(sendMessage);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (!config.isPlayerEventsEnabled())
            return;

        SendMessage sendMessage = new SendMessage(config.getEventChatId(), String.format(PLAYER_LEFT, e.getPlayer().getName()));
        bot.execute(sendMessage);
    }

}
