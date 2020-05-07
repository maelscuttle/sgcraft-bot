package com.maelscuttle.sgcraftbot;

import com.maelscuttle.sgcraftbot.Bot.Commands.ChatCommandHandler;
import com.maelscuttle.sgcraftbot.Bot.Commands.OnlinePlayersCommandHandler;
import com.maelscuttle.sgcraftbot.Bot.Commands.WhereIsCommandHandler;
import com.maelscuttle.sgcraftbot.Core.Telegram.UpdateDispatcher;
import com.maelscuttle.sgcraftbot.Server.Commands.ChatCommand;
import com.maelscuttle.sgcraftbot.Server.Events.*;
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
        Bukkit.getPluginManager().registerEvents(new ServerEventListener(config, bot), this);

        // mc commands
        Bukkit.getPluginCommand(ChatCommand.COMMAND).setExecutor(new ChatCommand(config, bot));
    }

    @Override
    public void onDisable() {
        saveConfig();
        Bukkit.getScheduler().cancelTasks(this);
    }



}
