package com.gmail.maelgrove.SgcraftTlg.Core.Telegram.Commands;

import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.Model.Update;

/**
 * Represents a bot command.
 */
public class Command {

    /**
     * Specifies the default initial character for commands.
     */
    public final static String COMMAND_INIT_CHARACTER = "/";

    /**
     * Specifies the default command parameter separator.
     */
    public static final String COMMAND_PARAMETER_SEPARATOR_REGEXP = "\\s+";

    /**
     * Specifies the default command length.
     */
    private final static int COMMAND_MAX_LENGTH = 32;

    private Update update;
    private String name;
    private String[] parameters;

    public Command(Update update, String name, String[] parameters) {
        this.update = update;
        this.name = name;
        this.parameters = parameters;
    }

    public Update getUpdate() {
        return update;
    }

    public String getName() {
        return name;
    }

    public String[] getParameters() {
        return parameters;
    }
}
