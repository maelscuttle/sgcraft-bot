package com.maelscuttle.sgcraftbot.Server.Events;

import com.maelscuttle.sgcraftbot.PluginConfig;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
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
        if (!config.isWorldEventsEnabled())
            return;

        String worldName = e.getWorld().getName();

        SendMessage sendMessage = new SendMessage(config.getEventChatId(), String.format("The world '%s' has been loaded.", worldName));
        bot.execute(sendMessage);
    }

    @EventHandler
    public void onWorldUnloaded(WorldUnloadEvent e) {
        if (!config.isWorldEventsEnabled())
            return;

        String worldName = e.getWorld().getName();

        SendMessage sendMessage = new SendMessage(config.getEventChatId(), String.format("The world '%s' has been unloaded.", worldName));
        bot.execute(sendMessage);
    }

    @EventHandler
    public void onPortalCreated(PortalCreateEvent e) {
        if (!config.isWorldEventsEnabled())
            return;

        PortalCreateEvent.CreateReason reason = e.getReason();
        if(reason == PortalCreateEvent.CreateReason.FIRE) {
            SendMessage sendMessage = new SendMessage(config.getEventChatId(), "Portal has been created. Off to the Nether!");
            bot.execute(sendMessage);
        }
    }

}
