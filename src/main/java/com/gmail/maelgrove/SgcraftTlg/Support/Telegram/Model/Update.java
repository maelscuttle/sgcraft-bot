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
     * @param updateId The update id.
     */
    public void setUpdateId(int updateId) {
        this.update_id = updateId;
    }

    /**
     * @return The message.
     */
    public Message getMessage() {
        return message;
    }

    /**
     * @param message The message.
     */
    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * @return The message if it has been edited.
     */
    public Message getEditedMessage() {
        return edited_message;
    }

    /**
     * @param editedMessage The message if it has been edited.
     */
    public void setEditedMessage(Message editedMessage) {
        this.edited_message = editedMessage;
    }

    /**
     * @return The message if posted to a channel.
     */
    public Message getChannelPost() {
        return channel_post;
    }

    /**
     * @param channelPost The message if posted to a channel.
     */
    public void setChannelPost(Message channelPost) {
        this.channel_post = channelPost;
    }

    /**
     * @return The edited channel post.
     */
    public Message getEditedChannelPost() {
        return edited_channel_post;
    }

    /**
     * @param editedChannelPost The edited channel post.
     */
    public void setEditedChannelPost(Message editedChannelPost) {
        this.edited_channel_post = editedChannelPost;
    }


}
