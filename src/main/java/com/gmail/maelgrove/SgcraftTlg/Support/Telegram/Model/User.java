package com.gmail.maelgrove.SgcraftTlg.Support.Telegram.Model;

/**
 * Represents a Telegram user.
 */
public class User {
    private int id;
    private boolean is_bot;
    private String first_name;
    private String last_name;
    private String username;

    /**
     * @return The user identifier.
     */
    public int getId() {
        return id;
    }

    /**
     * @return True if the user is a bot, otherwise false.
     */
    public boolean getIsBot () {
        return is_bot;
    }

    /**
     * @return The first name of the user.
     */
    public String getFirstName() {
        return first_name;
    }


    /**
     * @return The last name of the user.
     */
    public String getLastName() {
        return last_name;
    }

    /**
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

}
