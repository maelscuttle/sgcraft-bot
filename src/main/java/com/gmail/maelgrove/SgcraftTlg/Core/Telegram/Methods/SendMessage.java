package com.gmail.maelgrove.SgcraftTlg.Core.Telegram.Methods;



/**
 * Represents a Telegram send message payload.
 */
public class SendMessage {

    private int message_id;
    private Long chat_id;
    private String text;
    private int reply_to_message_id;
    private String parse_mode;

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

    /**
     * @return The message id to reply to.
     */
    public int getReplygetReplyToMessageId() {
        return reply_to_message_id;
    }

    /**
     * @param messageId The message id to reply to.
     * @return The message.
     */
    public SendMessage setReplyToMessageId(int messageId) {
        this.reply_to_message_id = messageId;
        return this;
    }

    /**
     * @return The parse mode.
     */
    public String getParseMode() {
        return parse_mode;
    }

    /**
     * @param parse_mode The parse mode.
     * @return The message.
     */
    public SendMessage setParseMode(String parse_mode) {
        this.parse_mode = parse_mode;
        return this;
    }
}
