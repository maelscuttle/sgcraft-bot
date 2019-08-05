package com.gmail.maelgrove.SgcraftTlg.Server.Events;

import com.gmail.maelgrove.SgcraftTlg.PluginConfig;
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
        if (!bot.isReady() || !config.isPlayerEventsEnabled())
            return;
        bot.sendMessage(new SendMessage()
                .setChatId(config.getEventChatId())
                .setText(String.format(PLAYER_JOINED, e.getPlayer().getName())));
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (!bot.isReady() || !config.isPlayerEventsEnabled())
            return;
        bot.sendMessage(new SendMessage()
                .setChatId(config.getEventChatId())
                .setText(String.format(PLAYER_DIED, e.getDeathMessage())));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (!bot.isReady() || !config.isPlayerEventsEnabled())
            return;
        bot.sendMessage(new SendMessage()
                .setChatId(config.getEventChatId())
                .setText(String.format(PLAYER_LEFT, e.getPlayer().getName())));
    }

}
