package com.gmail.maelgrove.SgcraftTlg.Utils;

import org.bukkit.configuration.file.FileConfiguration;

@SuppressWarnings("unused")
public class Config {
    public static Config Default;

    private static FileConfiguration config;

    private Config(FileConfiguration config) {
        this.config = config;
    }

    public static void initialize(FileConfiguration config) {
        Default = new Config(config);
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

    public Long getChatId() {
        return config.getLong("chatid");
    }
}
