package com.gmail.maelgrove.SgcraftTlg.Utils;

public class Messages {

    public static final String PlayerJoined = "%s joined the game.";

    public static final String PlayerLeft = "%s left the game.";

    public static final String PlayerDied = "%s";

    public static String[] formatMSG(String format, Object... args) {
        if (args != null && args.length > 0)
            format = String.format(format, args);
        format = format.replace("&", "§");

        return format.split("\\n");
    }

}
