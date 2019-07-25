package com.gmail.maelgrove.SgcraftTlg.Bot;

import com.gmail.maelgrove.SgcraftTlg.Support.Telegram.Commands.Command;
import com.gmail.maelgrove.SgcraftTlg.Support.Telegram.Commands.CommandHandler;
import com.gmail.maelgrove.SgcraftTlg.Support.Telegram.Model.SendMessage;
import com.gmail.maelgrove.SgcraftTlg.Support.Telegram.UpdateHandlerContext;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Represents a command handler which handles the /whereis PLAYER command
 */
public class WhereIsCommandHandler extends CommandHandler {

    private static final String PLAYER_NOT_ONLINE = "Player %s is not online.";

    private static final String PLAYER_COORDINATES = "Player %1$s is at (%2$s x, %3$s y, %4$s z).";

    @Override
    protected void handleCommand(Command command) {

        if(command.getName() != "whereis" || command.getParameters().length != 1)
            return;

        String playerName   = command.getParameters()[0];
        Player player       = Bukkit.getPlayer(playerName);


        UpdateHandlerContext context = getContext();

        // TODO impl helpers
        Long chatId = context.getUpdate().getMessage().getChat().getId();

        if(!player.isOnline())
        {
            context.getBot().sendMessage(new SendMessage()
                .setChatId(chatId)
                .setText(String.format(PLAYER_NOT_ONLINE, playerName)));
            return;
        }

        Location location = Bukkit.getPlayer(playerName).getLocation();

        context.getBot().sendMessage(new SendMessage()
                .setChatId(chatId)
                .setText(String.format(PLAYER_COORDINATES,
                        playerName, location.getBlockX(), location.getBlockY(), location.getBlockZ())));
    }
}
