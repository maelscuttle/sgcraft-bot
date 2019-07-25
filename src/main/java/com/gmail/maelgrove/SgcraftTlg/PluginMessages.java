package com.gmail.maelgrove.SgcraftTlg;

/**
 * Provides common messages.
 */
public class PluginMessages {

    public static final String PLAYER_JOINED = "%s joined the game.";

    public static final String PLAYER_LEFT = "%s left the game.";

    public static final String PLAYER_DIED = "%s";

    /**
     * @param format The format to format the message with.
     * @param args The format arguments.
     * @return A list of formatted messages.
     */
    public static String[] formatMessage(String format, Object... args) {
        if (args != null && args.length > 0)
            format = String.format(format, args);
        format = format.replace("&", "§");

        return format.split("\\n");
    }

}
