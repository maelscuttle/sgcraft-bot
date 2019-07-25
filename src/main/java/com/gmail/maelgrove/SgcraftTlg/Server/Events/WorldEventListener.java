package com.gmail.maelgrove.SgcraftTlg.Server.Events;

import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.Methods.SendMessage;
import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.TelegramBot;
import com.gmail.maelgrove.SgcraftTlg.PluginConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;

/**
 * Represents a listener for world events.
 */
public class WorldEventListener implements Listener {

    private PluginConfig config;
    private TelegramBot bot;

    /**
     * @param config The config.
     * @param bot The bot.
     */
    public WorldEventListener(PluginConfig config, TelegramBot bot) {
        this.config = config;
        this.bot    = bot;
    }

    @EventHandler
    public void onWorldLoaded(WorldLoadEvent e) {
        if (!bot.isReady() || !config.isWorldEventsEnabled())
            return;

        String worldName = e.getWorld().getName();
        bot.sendMessage(new SendMessage()
                .setChatId(config.getEventChatId())
                .setText(String.format("The world '%s' has been loaded.", worldName)));
    }

    @EventHandler
    public void onWorldUnloaded(WorldUnloadEvent e) {
        if (!bot.isReady() || !config.isWorldEventsEnabled())
            return;

        String worldName = e.getWorld().getName();
        bot.sendMessage(new SendMessage()
                .setChatId(config.getEventChatId())
                .setText(String.format("The world '%s' has been unloaded.", worldName)));

    }

    @EventHandler
    public void onPortalCreated(PortalCreateEvent e) {
        if (!bot.isReady() || !config.isWorldEventsEnabled())
            return;

        PortalCreateEvent.CreateReason reason = e.getReason();
        if(reason == PortalCreateEvent.CreateReason.FIRE) {
            bot.sendMessage(new SendMessage()
                    .setChatId(config.getEventChatId())
                    .setText(String.format("Portal has been created. Off to the Nether!")));
        }
    }

}
