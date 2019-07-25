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
     * @param id The user identifier.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return True if the user is a bot, otherwise false.
     */
    public boolean getIsBot () {
        return is_bot;
    }

    /**
     * @param is_bot True if the user is a bot, otherwise false.
     */
    public void setIsBot(boolean is_bot) {
        this.is_bot = is_bot;
    }

    /**
     * @return The first name of the user.
     */
    public String getFirstName() {
        return first_name;
    }

    /**
     * @param firstName The first name of the user.
     */
    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    /**
     * @return The last name of the user.
     */
    public String getLastName() {
        return last_name;
    }

    /**
     * @param lastName The last name of the user.
     */
    public void setLastName(String lastName) {
        this.last_name = lastName;
    }

    /**
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
