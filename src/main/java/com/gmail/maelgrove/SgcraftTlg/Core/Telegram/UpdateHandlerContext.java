package com.gmail.maelgrove.SgcraftTlg.Core.Telegram;

import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.Model.SendMessage;
import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.Model.Update;

public class UpdateHandlerContext {

    private Update update;
    private TelegramBot bot;

    public UpdateHandlerContext(TelegramBot bot, Update update) {
        this.bot = bot;
        this.update = update;
    }

    public Update getUpdate() {
        return update;
    }

    public TelegramBot getBot() {
        return bot;
    }

    public void sendMessageToChat(String message) {
        Long chatId = update.getMessage().getChat().getId();
        bot.sendMessage(new SendMessage()
            .setText(message)
            .setChatId(chatId));
    }
}
