package com.gmail.maelgrove.SgcraftTlg.Support.Telegram.Model;

/**
 * Represents a Telegram update.
 */
public class Update {
    private int update_id;
    private Message message;
    private Message edited_message;
    private Message channel_post;
    private Message edited_channel_post;

    /**
     * @return The update id.
     */
    public int getUpdateId() {
        return update_id;
    }

    /**
     * @return The message.
     */
    public Message getMessage() {
        return message;
    }

    /**
     * @return The message if it has been edited.
     */
    public Message getEditedMessage() {
        return edited_message;
    }

    /**
     * @return The message if posted to a channel.
     */
    public Message getChannelPost() {
        return channel_post;
    }

    /**
     * @return The edited channel post.
     */
    public Message getEditedChannelPost() {
        return edited_channel_post;
    }

    /**
     * @return Whether the update has a message.
     */
    public boolean hasMessage() {
        return message != null;
    }
}
