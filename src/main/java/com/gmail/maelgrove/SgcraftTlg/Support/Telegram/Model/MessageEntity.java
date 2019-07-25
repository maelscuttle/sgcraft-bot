package com.gmail.maelgrove.SgcraftTlg.Support.Telegram.Model;

import org.omg.CORBA.INTERNAL;

/**
 * Represents a Telegram message entity.
 */
public class MessageEntity {

    private String type;
    private int offset;
    private int length;
    private String url;
    private User user;
    private String text;


    public String getType() {
        return type;
    }


    public int getOffset() {
        return offset;
    }

    public int getLength() {
        return length;
    }

    public String getUrl() {
        return url;
    }

    public User getUser() {
        return user;
    }


    public String getText() {
        return text;
    }


    protected void computeText(String message) {
        if (message != null) {
            text = message.substring(offset, offset + length);
        }
    }
}
