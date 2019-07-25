package com.gmail.maelgrove.SgcraftTlg.Support.Telegram.Commands;

import com.gmail.maelgrove.SgcraftTlg.Support.Telegram.AbstractUpdateHandler;
import com.gmail.maelgrove.SgcraftTlg.Support.Telegram.Model.Message;
import com.gmail.maelgrove.SgcraftTlg.Support.Telegram.Model.Update;

import java.util.Arrays;

/**
 * Represents a base type for command handlers.
 */
public abstract class AbstractCommandHandler extends AbstractUpdateHandler {

    @Override
    public final void handleUpdate(Update update) {
        if(update != null && update.hasMessage()) {
            Message message = update.getMessage();
            if(message.isCommand() && message.hasText()) {
                String text = message.getText();
                if(text.startsWith(Command.COMMAND_INIT_CHARACTER)) {
                    String commandMessage = text.substring(1);
                    String[] commandParts = commandMessage.split(Command.COMMAND_PARAMETER_SEPARATOR_REGEXP);
                    String[] parameters = Arrays.copyOfRange(commandParts, 1, commandParts.length);

                    Command command = new Command(update, commandParts[0], parameters);
                    handleCommand(command);
                }
            }
        }
    }

    /**
     * @param command The command to handle.
     */
    protected abstract void handleCommand(Command command);
}
