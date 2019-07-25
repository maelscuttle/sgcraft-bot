package com.gmail.maelgrove.SgcraftTlg;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * Represents the plugin configuration.
 */
public class PluginConfig {

    private FileConfiguration config;

    public PluginConfig(FileConfiguration config) {
        this.config = config;
    }

    public void setDefaults() {
        config.addDefault("bottoken", "BOT_TOKEN");
        config.addDefault("chatid", "CHAT_ID");
        config.addDefault("botname", "BOT_NAME");
        config.options().copyDefaults(true);
    }

    public String getBotName() {
        return config.getString("botname");
    }

    public String getBotToken() {
        return config.getString("bottoken");
    }

    public Long getChatId() { return  config.getLong("chatid"); }
}
