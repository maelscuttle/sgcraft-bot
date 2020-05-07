package com.maelscuttle.sgcraftbot.Server.Events;

import com.maelscuttle.sgcraftbot.PluginConfig;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * Represents a listener for weather events.
 */
public class WeatherEventListener implements Listener {

    private PluginConfig config;
    private TelegramBot bot;

    /**
     * @param config The config.
     * @param bot The bot.
     */
    public WeatherEventListener(PluginConfig config, TelegramBot bot) {
        this.config = config;
        this.bot    = bot;
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        if (!config.isWeatherEventsEnabled())
            return;

        if(e.toWeatherState()){
            SendMessage sendMessage = new SendMessage(config.getEventChatId(), "It started raining.");
            bot.execute(sendMessage);
        }
    }

    @EventHandler
    public void onLightingStrike(LightningStrikeEvent e) {
        if (!config.isWeatherEventsEnabled())
            return;

        LightningStrike strike = e.getLightning();
        if(!strike.isEffect()) {
            SendMessage sendMessage = new SendMessage(config.getEventChatId(), "A lightning struck somewhere!");
            bot.execute(sendMessage);
        }
    }

}
