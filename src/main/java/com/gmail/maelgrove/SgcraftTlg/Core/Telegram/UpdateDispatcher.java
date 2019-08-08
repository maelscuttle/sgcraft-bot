package com.gmail.maelgrove.SgcraftTlg.Core.Telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the updates listener responsible for dispatching updates.
 */
public class UpdateDispatcher implements UpdatesListener {

    private TelegramBot bot;
    private List<UpdateHandler> updateHandlers = new ArrayList<UpdateHandler>();

    public void addUpdateHandler(UpdateHandler handler) {
        updateHandlers.add(handler);
    }

    public UpdateDispatcher(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public int process(List<Update> updates) {
       for(Update update : updates) {
           UpdateHandlerContext context = new UpdateHandlerContext(bot, update);
           for(UpdateHandler handler : updateHandlers) {
               handler.handleUpdate(context);
           }
       }

       return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
