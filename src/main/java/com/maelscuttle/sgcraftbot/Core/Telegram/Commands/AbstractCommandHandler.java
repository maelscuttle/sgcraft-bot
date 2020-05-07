package com.maelscuttle.sgcraftbot.Core.Telegram.Commands;

import com.maelscuttle.sgcraftbot.Core.Telegram.AbstractUpdateHandler;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.MessageEntity;
import com.pengrad.telegrambot.model.Update;

import java.util.Arrays;

/**
 * Represents a base type for command handlers.
 */
public abstract class AbstractCommandHandler extends AbstractUpdateHandler {

    @Override
    public final void handleUpdate(Update update) {
        if(update != null && update.message() != null) {
            Message message = update.message();
            for(MessageEntity entity : message.entities()) {
                if(entity != null && entity.offset() == 0 && entity.type() == MessageEntity.Type.bot_command) {
                    String text = message.text();
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
    }

    /**
     * @param command The command to handle.
     */
    protected abstract void handleCommand(Command command);
}
