package com.gmail.maelgrove.SgcraftTlg;

import com.gmail.maelgrove.SgcraftTlg.Support.Telegram.Model.SendMessage;
import com.gmail.maelgrove.SgcraftTlg.Support.Telegram.TelegramBot;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

@SuppressWarnings("unused")
public class PluginContext extends JavaPlugin implements Listener {

    private PluginConfig config;
    private TelegramBot bot;
    private Logger logger;

    @Override
    public void onEnable() {
        logger = getLogger();

        // initialize
        Bukkit.getPluginManager().registerEvents(this, this);

        // configuration
        config = new PluginConfig(getConfig());
        config.setDefaults();
        saveConfig();

        bot = new TelegramBot();
        bot.tryAuthenticate(config.getBotToken());

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            if (!bot.getIsConnected())
                bot.tryReconnect();
            else
                bot.tryProcessNextUpdate();
        }, 10L, 10L);

        logger.info("SgcraftTlg initialized.");
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!bot.getIsConnected() || !bot.getIsAuthenticated())
            return;
        logger.info("Player joined, send message.");
        bot.sendMessage(new SendMessage()
            .setChatId(config.getChatId())
            .setText(PluginMessages.formatMessage(PluginMessages.PLAYER_JOINED, e.getPlayer().getName())[0]));
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (!bot.getIsConnected() || !bot.getIsAuthenticated())
            return;
        logger.info("Player died, send message.");
        bot.sendMessage(new SendMessage()
                        .setChatId(config.getChatId())
                .setText(PluginMessages.formatMessage(PluginMessages.PLAYER_DIED, e.getDeathMessage())[0]));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (!bot.getIsConnected() || !bot.getIsAuthenticated())
            return;
        logger.info("Player disconnected, send message.");
        bot.sendMessage(new SendMessage()
                        .setChatId(config.getChatId())
                        .setText(PluginMessages.formatMessage(PluginMessages.PLAYER_LEFT, e.getPlayer().getName())[0]));
    }

}
