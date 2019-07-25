package com.gmail.maelgrove.SgcraftTlg;

import com.gmail.maelgrove.SgcraftTlg.Bot.Commands.OnlinePlayersCommandHandler;
import com.gmail.maelgrove.SgcraftTlg.Bot.Commands.WhereIsCommandHandler;
import com.gmail.maelgrove.SgcraftTlg.Server.Events.PlayerEventListener;
import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.TelegramBot;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

@SuppressWarnings("unused")
public class PluginContext extends JavaPlugin {

    private PluginConfig config;
    private TelegramBot bot;
    private Logger logger;

    @Override
    public void onEnable() {
        // configuration
        config = new PluginConfig(getConfig());
        config.setDefaults();
        saveConfig();

        // event listeners
        Bukkit.getPluginManager().registerEvents(new PlayerEventListener(config, bot), this);

        // bot
        bot = new TelegramBot();
        bot.addUpdateHandler(new WhereIsCommandHandler());
        bot.addUpdateHandler(new OnlinePlayersCommandHandler());

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            if (!bot.isConnected())
                bot.tryReconnect();
            else
                bot.tryProcessNextUpdate();
        }, 10L, 10L);
    }

    @Override
    public void onDisable() {
        saveConfig();
    }



}
