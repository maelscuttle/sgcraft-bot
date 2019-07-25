package com.gmail.maelgrove.SgcraftTlg.Server.Events;

import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.Methods.SendMessage;
import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.TelegramBot;
import com.gmail.maelgrove.SgcraftTlg.PluginConfig;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldEventListener implements Listener {

    private PluginConfig config;
    private TelegramBot bot;

    public WorldEventListener(PluginConfig config, TelegramBot bot) {
        this.config = config;
        this.bot    = bot;
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        if (!bot.isReady() || !config.isPlayerEventsEnabled())
            return;

        if(e.toWeatherState()){
            bot.sendMessage(new SendMessage()
                .setChatId(config.getEventChatId())
                .setText("It started raining!"));
        }
    }

    @EventHandler
    public void onLightingStrike(LightningStrikeEvent e) {
        if (!bot.isReady() || !config.isPlayerEventsEnabled())
            return;

        LightningStrike strike = e.getLightning();
        if(!strike.isEffect()) {
            bot.sendMessage(new SendMessage()
                    .setChatId(config.getEventChatId())
                    .setText("A lightning struck somewhere!"));
        }
    }

}
