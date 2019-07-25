package com.gmail.maelgrove.SgcraftTlg.Bot.Commands;

import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.Commands.AbstractCommandHandler;
import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.Commands.Command;
import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.UpdateHandlerContext;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Represents a command handler which handles the /whereis PLAYER command
 */
public class WhereIsCommandHandler extends AbstractCommandHandler {

    private static final String PLAYER_NOT_ONLINE = "Player %s is not online.";
    private static final String PLAYER_COORDINATES = "Player %1$s is at (%2$s x, %3$s y, %4$s z).";

    @Override
    protected void handleCommand(Command command) {
        if(!command.getName().equals("mcwhereis") || command.getParameters().length < 1)
            return;

        String playerName   = command.getParameters()[0];
        Player player       = Bukkit.getPlayer(playerName);

        UpdateHandlerContext context = getContext();

        if(!player.isOnline()) {
            context.sendMessageToChat(String.format(PLAYER_NOT_ONLINE, playerName));
        } else {
            Location location = Bukkit.getPlayer(playerName).getLocation();
            context.sendMessageToChat(String.format(PLAYER_COORDINATES,
                    playerName, location.getBlockX(), location.getBlockY(), location.getBlockZ()));
        }
    }
}
