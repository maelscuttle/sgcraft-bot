package com.maelscuttle.sgcraftbot.Core.Telegram;

import com.maelscuttle.sgcraftbot.PluginContext;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the updates listener responsible for dispatching updates.
 */
public class UpdateDispatcher {

    private TelegramBot bot;
    private List<UpdateHandler> updateHandlers = new ArrayList<UpdateHandler>();

    public void addUpdateHandler(UpdateHandler handler) {
        updateHandlers.add(handler);
    }

    public UpdateDispatcher(TelegramBot bot) {
        this.bot = bot;
    }

    public void start() {
        bot.setUpdatesListener(updates -> {
            for(Update update : updates) {
               try {
                   UpdateHandlerContext context = new UpdateHandlerContext(bot, update);
                   for(UpdateHandler handler : updateHandlers) {
                       handler.handleUpdate(context);
                   }
               } catch(Exception exception) {
                   // ignore
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    public void stop() {
        bot.removeGetUpdatesListener();
    }
}
