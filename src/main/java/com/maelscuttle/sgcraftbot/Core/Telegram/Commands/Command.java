package com.maelscuttle.sgcraftbot.Core.Telegram.Commands;
import com.pengrad.telegrambot.model.Update;

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
