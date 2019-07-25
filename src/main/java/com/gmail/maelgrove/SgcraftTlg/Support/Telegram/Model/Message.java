package com.gmail.maelgrove.SgcraftTlg.Support.Telegram.Model;


/**
 * Represents a Telegram message.
 */
public class Message {
    private int message_id;
    private User from;
    private int date;
    private Chat chat;
    private String text;

    /**
     * @return The message id
     */
    public int getMessageId() {
        return message_id;
    }


    /**
     * @param messageId The message id.
     */
    public void setMessageId(int messageId) {
        this.message_id = messageId;
    }

    /**
     * @return The user who sent the message.
     */
    public User getFrom() {
        return from;
    }

    /**
     * @param from The user who sent the message.
     */
    public void setFrom(User from) {
        this.from = from;
    }

    /**
     * @return The message date.
     */
    public int getDate() {
        return date;
    }

    /**
     * @param date The message date.
     */
    public void setDate(int date) {
        this.date = date;
    }

    /**
     * @return The chat from where the message originated.
     */
    public Chat getChat() {
        return chat;
    }

    /**
     * @param chat The chat from where the message originated.
     */
    public void setChat(Chat chat) {
        this.chat = chat;
    }

    /**
     * @return The text.
     */
    public String getText() {
        return text;
    }

    /**
     * @param text The text.
     */
    public void setText(String text) {
        this.text = text;
    }
}
