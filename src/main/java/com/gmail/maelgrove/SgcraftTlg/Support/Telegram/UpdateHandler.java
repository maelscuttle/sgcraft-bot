package com.gmail.maelgrove.SgcraftTlg.Support.Telegram;

import com.gmail.maelgrove.SgcraftTlg.Support.Telegram.Model.Update;

/**
 * Represents a service which handles Telegram updates.
 */
public interface UpdateHandler {

    /**
     * @param update The update to check.
     * @return True if the handler can handle the update, otherwise false.
     */
    boolean canHandleUpdate(Update update);


    /**
     * @param update The update to handle.
     */
    void handleUpdate(Update update);

}
