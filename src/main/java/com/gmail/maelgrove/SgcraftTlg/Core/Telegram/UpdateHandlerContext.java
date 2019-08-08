package com.gmail.maelgrove.SgcraftTlg.Core.Telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

/**
 * Represents the context of the current bot update handler.
 */
public class UpdateHandlerContext {

    private Update update;
    private TelegramBot bot;

    /**
     * @param bot The bot.
     * @param update The update.
     */
    public UpdateHandlerContext(TelegramBot bot, Update update) {
        this.bot = bot;
        this.update = update;
    }

    /**
     * @return The update.
     */
    public Update getUpdate() {
        return update;
    }

    /**
     * @return The bot.
     */
    public TelegramBot getBot() {
        return bot;
    }

    /**
     * Sends a reply to the source message.
     * @param message The message to reply with.
     */
    public void reply(String message) {
        int messageId   = update.message().messageId();
        Long chatId     = update.message().chat().id();

        SendMessage request = new SendMessage(chatId, message)
                .replyToMessageId(messageId);
        bot.execute(request);
    }

    /**
     * @param message Sends a message to the chat this message came from.
     */
    public void sendMessageToChat(String message) {
        Long chatId     = update.message().chat().id();

        SendMessage request = new SendMessage(chatId, message);
        bot.execute(request);
    }
}
