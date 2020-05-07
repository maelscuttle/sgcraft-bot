package com.maelscuttle.sgcraftbot.Bot.Commands;

import com.maelscuttle.sgcraftbot.Core.Telegram.Commands.AbstractCommandHandler;
import com.maelscuttle.sgcraftbot.Core.Telegram.Commands.Command;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a command handler who shows all currently online players.
 */
public class OnlinePlayersCommandHandler extends AbstractCommandHandler {
    @Override
    protected void handleCommand(Command command) {
        if(!command.getName().equals("mconline"))
            return;
        List<String> names =
                Bukkit.getOnlinePlayers()
                .stream().map(player -> player.getName()).collect(Collectors.toList());
        getContext().sendMessageToChat(String.format("Players online:\n%s",
                String.join("\n", names)));
    }
}
