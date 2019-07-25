package com.gmail.maelgrove.SgcraftTlg.Support.Telegram.Model;


import java.util.List;

/**
 * Represents a Telegram message.
 */
public class Message {
    private int message_id;
    private User from;
    private int date;
    private Chat chat;
    private String text;
    private List<MessageEntity> entities;

    /**
     * @return The message id
     */
    public int getMessageId() {
        return message_id;
    }

    /**
     * @return The user who sent the message.
     */
    public User getFrom() {
        return from;
    }

    /**
     * @return The message date.
     */
    public int getDate() {
        return date;
    }

    /**
     * @return The chat from where the message originated.
     */
    public Chat getChat() {
        return chat;
    }

    /**
     * @return The text.
     */
    public String getText() {
        return text;
    }

    /**
     * @return Whether the message has text.
     */
    public boolean hasText() {
        return text != null && !text.isEmpty();
    }

    /**
     * @return Whether the message represents a command.
     */
    public boolean isCommand() {
        if (hasText() && entities != null) {
            for (MessageEntity entity : entities) {
                if (entity != null && entity.getOffset() == 0 &&
                        MessageEntityType.BOTCOMMAND.equals(entity.getType())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return All message entities within the message.
     */
    public List<MessageEntity> getEntities() {
        if (entities != null) {
            entities.forEach(x -> x.computeText(text));
        }
        return entities;
    }
}
