package com.gmail.maelgrove.SgcraftTlg.Core.Telegram;

/**
 * Represents a service which handles Telegram updates.
 */
public interface UpdateHandler {

    /**
     * @param context The context of the update.
     */
    void handleUpdate(UpdateHandlerContext context);
}
