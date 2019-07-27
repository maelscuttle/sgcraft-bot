package com.gmail.maelgrove.SgcraftTlg;

import com.gmail.maelgrove.SgcraftTlg.Bot.Commands.OnlinePlayersCommandHandler;
import com.gmail.maelgrove.SgcraftTlg.Bot.Commands.WhereIsCommandHandler;
import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.Methods.SendMessage;
import com.gmail.maelgrove.SgcraftTlg.Server.Commands.ChatCommand;
import com.gmail.maelgrove.SgcraftTlg.Server.Commands.SetTokenCommand;
import com.gmail.maelgrove.SgcraftTlg.Server.Events.PlayerEventListener;
import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.TelegramBot;
import com.gmail.maelgrove.SgcraftTlg.Server.Events.WeatherEventListener;
import com.gmail.maelgrove.SgcraftTlg.Server.Events.WorldEventListener;
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

        // bot
        bot = new TelegramBot();
        bot.tryAuthenticate(config.getBotToken());
        bot.addUpdateHandler(new WhereIsCommandHandler(config));
        bot.addUpdateHandler(new OnlinePlayersCommandHandler());

        // event listeners
        Bukkit.getPluginManager().registerEvents(new PlayerEventListener(config, bot), this);
        Bukkit.getPluginManager().registerEvents(new WeatherEventListener(config, bot), this);
        Bukkit.getPluginManager().registerEvents(new WorldEventListener(config, bot), this);

        // mc commands
        Bukkit.getPluginCommand(SetTokenCommand.COMMAND).setExecutor(new SetTokenCommand(config, bot));
        Bukkit.getPluginCommand(ChatCommand.COMMAND).setExecutor(new ChatCommand(config, bot));

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            if(bot.hasToken()) {
                if (!bot.isConnected())
                    bot.tryReconnect();
                else
                    bot.tryProcessNextUpdate();
            }
        }, 10L, 10L);
    }

    @Override
    public void onDisable() {
        saveConfig();
    }



}
