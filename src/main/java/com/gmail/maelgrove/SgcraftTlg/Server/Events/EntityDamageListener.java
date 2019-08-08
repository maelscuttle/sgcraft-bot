package com.gmail.maelgrove.SgcraftTlg.Server.Events;

import com.gmail.maelgrove.SgcraftTlg.PluginConfig;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;


/**
 * Represents a listener for entity damage events.
 */
public class EntityDamageListener implements Listener {

    private static final String PLAYER_LAVA = "%s's ass on fire.";

    private static final String PLAYER_SUICIDE = "%s tried to do an hero.";

    private static final String PLAYER_FALLING_BLOCK = "%s: Oof ouch owie my head.";

    private static final String PLAYER_FALL = "%s thought they could fly.";

    private PluginConfig config;
    private TelegramBot bot;

    public EntityDamageListener(PluginConfig config, TelegramBot bot) {
        this.config = config;
        this.bot    = bot;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {

        if (!config.isDamageEventsEnabled())
            return;

        Entity entity = event.getEntity();
        if(!(entity instanceof Player))
            return;

        String messageFormat = "";
        switch (event.getCause()) {
            case FALL:
                messageFormat = PLAYER_FALL;
                break;
            case LAVA:
                messageFormat = PLAYER_LAVA;
                break;
            case SUICIDE:
                messageFormat = PLAYER_SUICIDE;
                break;
            case FALLING_BLOCK:
                messageFormat = PLAYER_FALLING_BLOCK;
            default:
                break;
        }

        String message = String.format(messageFormat, entity.getName());

        SendMessage sendMessage = new SendMessage(config.getEventChatId(), message);
        bot.execute(sendMessage);
    }

}
