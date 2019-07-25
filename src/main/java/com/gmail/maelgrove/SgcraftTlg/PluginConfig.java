package com.gmail.maelgrove.SgcraftTlg;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * Represents the plugin configuration.
 */
public class PluginConfig {

    private FileConfiguration config;

    private static final String EVENT_CHAT_ID           = "event-chat-id";
    private static final String BOT_TOKEN               = "bot-token";
    private static final String ENABLE_PLAYER_EVENTS = "enable-player-events";
    private static final String ENABLE_WORLD_EVENTS = "enable-world-events";
    private static final String ENABLE_WEATHER_EVENTS = "enable-weather-events";

    public PluginConfig(FileConfiguration config) {
        this.config = config;
    }

    public void setDefaults() {
        config.addDefault(BOT_TOKEN, "BOT_TOKEN");
        config.addDefault(EVENT_CHAT_ID, "EVENT_CHATID");
        config.addDefault(ENABLE_PLAYER_EVENTS, true);
        config.addDefault(ENABLE_WORLD_EVENTS, true);
        config.addDefault(ENABLE_WEATHER_EVENTS, true);
        config.options().copyDefaults(true);
    }

    public void setBotToken(String token) {
        config.set(BOT_TOKEN, token);
    }

    public String getBotToken() {
        return config.getString(BOT_TOKEN);
    }

    public Long getEventChatId() { return config.getLong(EVENT_CHAT_ID); }

    public boolean isPlayerEventsEnabled() {
        return config.getBoolean(ENABLE_PLAYER_EVENTS);
    }

    public boolean isWorldEventsEnabled() {return config.getBoolean(ENABLE_WORLD_EVENTS);}

    public boolean isWeatherEventsEnabled() { return config.getBoolean(ENABLE_WEATHER_EVENTS);}
}
