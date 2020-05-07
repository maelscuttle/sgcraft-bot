package com.maelscuttle.sgcraftbot.Core.Telegram;

import com.pengrad.telegrambot.model.Update;
import org.bukkit.Bukkit;

import java.util.logging.Logger;

/**
 * Represents a base type for update handlers.
 */
public abstract class AbstractUpdateHandler implements UpdateHandler {

    private UpdateHandlerContext context;

    @Override
    public void handleUpdate(UpdateHandlerContext context) {
        this.context = context;
        handleUpdate(context.getUpdate());
    }

    protected abstract void handleUpdate(Update update);

    protected UpdateHandlerContext getContext() {
        return context;
    }

    protected Logger getLogger() {
        return Bukkit.getLogger();
    }
}
