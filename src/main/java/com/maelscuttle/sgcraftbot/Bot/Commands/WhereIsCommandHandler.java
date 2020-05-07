package com.maelscuttle.sgcraftbot.Bot.Commands;

import com.maelscuttle.sgcraftbot.Core.Telegram.Commands.AbstractCommandHandler;
import com.maelscuttle.sgcraftbot.Core.Telegram.Commands.Command;
import com.maelscuttle.sgcraftbot.Core.Telegram.UpdateHandlerContext;
import com.maelscuttle.sgcraftbot.PluginConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Represents a command handler which handles the /whereis PLAYER command
 */
public class WhereIsCommandHandler extends AbstractCommandHandler {

    private static final String PLAYER_NOT_ONLINE = "Player %s is not online.";
    private static final String DYNMAP_LINK = "%1$s/?zoom=5&x=%2$s&y=%3$s&z=%4$s";
    private static final String PLAYER_COORDINATES = "Player %1$s is at %2$s/%3$s/%4$s (x/y/z)\n%5$s";

    private PluginConfig config;

    public WhereIsCommandHandler(PluginConfig config) {
        this.config = config;
    }

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

            String dynmapLink = String.format(DYNMAP_LINK,
                    config.getDynmapAddress(),
                    location.getBlockX(), location.getBlockY(), location.getBlockZ());
            context.sendMessageToChat(String.format(PLAYER_COORDINATES,
                    playerName, location.getBlockX(), location.getBlockY(), location.getBlockZ(),
                    dynmapLink));
        }
    }
}
