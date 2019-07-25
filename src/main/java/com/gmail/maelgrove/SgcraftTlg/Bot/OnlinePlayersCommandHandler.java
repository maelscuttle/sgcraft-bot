package com.gmail.maelgrove.SgcraftTlg.Bot;

import com.gmail.maelgrove.SgcraftTlg.Support.Telegram.Commands.AbstractCommandHandler;
import com.gmail.maelgrove.SgcraftTlg.Support.Telegram.Commands.Command;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a command handler who shows all currently online players.
 */
public class OnlinePlayersCommandHandler extends AbstractCommandHandler {
    @Override
    protected void handleCommand(Command command) {
        if(!command.getName().equals("online"))
            return;
        List<String> names =
                Bukkit.getOnlinePlayers()
                .stream().map(player -> player.getName()).collect(Collectors.toList());
        getContext().sendMessageToChat(String.join("\\n", names));
    }
}
