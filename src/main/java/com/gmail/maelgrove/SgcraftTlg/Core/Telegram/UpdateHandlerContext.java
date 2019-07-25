package com.gmail.maelgrove.SgcraftTlg.Core.Telegram;

import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.Methods.SendMessage;
import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.Model.Update;

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
        int messageId = update.getMessage().getMessageId();
        bot.sendMessage(new SendMessage()
        .setText(message)
        .setReplyToMessageId(messageId));
    }

    /**
     * @param message Sends a message to the chat this message came from.
     */
    public void sendMessageToChat(String message) {
        Long chatId = update.getMessage().getChat().getId();
        bot.sendMessage(new SendMessage()
            .setText(message)
            .setChatId(chatId));
    }
}
