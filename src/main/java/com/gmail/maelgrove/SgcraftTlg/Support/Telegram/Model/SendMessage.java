package com.gmail.maelgrove.SgcraftTlg.Support.Telegram.Model;



/**
 * Represents a Telegram send message object.
 */
public class SendMessage {

    private int message_id;
    private Long chat_id;
    private String text;

    /**
     * @return The message id.
     */
    public int getMessageId() {
        return this.message_id;
    }

    /**
     * @return The chat id.
     */
    public Long getChatId(){
        return this.chat_id;
    }

    /**
     * @return The text.
     */
    public String getText() {
        return this.text;
    }

    /**
     * @param messageId The message id.
     * @return The message.
     */
    public SendMessage setMessageId(int messageId) {
        this.message_id = messageId;
        return this;
    }

    /**
     * @param chatId The chat id.
     * @return The message.
     */
    public SendMessage setChatId(Long chatId) {
        this.chat_id = chatId;
        return this;
    }

    /**
     * @param text The text.
     * @return The message.
     */
    public SendMessage setText(String text) {
        this.text = text;
        return this;
    }

}
