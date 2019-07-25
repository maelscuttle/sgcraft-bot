package com.gmail.maelgrove.SgcraftTlg.Support.Telegram;

import com.gmail.maelgrove.SgcraftTlg.Support.Telegram.Model.Update;

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
}
