package com.gmail.maelgrove.SgcraftTlg;

import com.gmail.maelgrove.SgcraftTlg.Bot.Commands.ChatCommandHandler;
import com.gmail.maelgrove.SgcraftTlg.Bot.Commands.OnlinePlayersCommandHandler;
import com.gmail.maelgrove.SgcraftTlg.Bot.Commands.WhereIsCommandHandler;
import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.UpdateDispatcher;
import com.gmail.maelgrove.SgcraftTlg.Server.Commands.ChatCommand;
import com.gmail.maelgrove.SgcraftTlg.Server.Events.EntityDamageListener;
import com.gmail.maelgrove.SgcraftTlg.Server.Events.PlayerEventListener;
import com.gmail.maelgrove.SgcraftTlg.Server.Events.WeatherEventListener;
import com.gmail.maelgrove.SgcraftTlg.Server.Events.WorldEventListener;
import com.pengrad.telegrambot.TelegramBot;
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
        bot = new TelegramBot(config.getBotToken());
        UpdateDispatcher dispatcher = new UpdateDispatcher(bot);
        dispatcher.addUpdateHandler(new ChatCommandHandler());
        dispatcher.addUpdateHandler(new OnlinePlayersCommandHandler());
        dispatcher.addUpdateHandler(new WhereIsCommandHandler(config));

        // event listeners
        Bukkit.getPluginManager().registerEvents(new PlayerEventListener(config, bot), this);
        Bukkit.getPluginManager().registerEvents(new WeatherEventListener(config, bot), this);
        Bukkit.getPluginManager().registerEvents(new WorldEventListener(config, bot), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageListener(config, bot), this);

        // mc commands
        Bukkit.getPluginCommand(ChatCommand.COMMAND).setExecutor(new ChatCommand(config, bot));
    }

    @Override
    public void onDisable() {
        saveConfig();
        Bukkit.getScheduler().cancelTasks(this);
    }



}
