package com.gmail.maelgrove.SgcraftTlg.Server.Commands;

import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.TelegramBot;
import com.gmail.maelgrove.SgcraftTlg.PluginConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Represents a Minecraft command for setting the Telegram bot token.
 */
public class TgSetTokenCommand implements CommandExecutor {


    private TelegramBot bot;
    private PluginConfig config;

    /**
     * Specifies the command permission.
     */
    public static final String PERMISSION = "telegram.settoken";

    /**
     * Specifies the command name.
     */
    public static final String COMMAND = "tgsettoken";

    public TgSetTokenCommand(PluginConfig config, TelegramBot bot) {
        this.config = config;
        this.bot = bot;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission(PERMISSION)) {
            sender.sendMessage("§cYou don't have permissions to use this!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(String.format("§c/%s [token]", COMMAND));
            return true;
        }

        String token = args[0];

        boolean success = false;
        try {
            success = bot.tryAuthenticate(token);
        } catch (Exception exception) {
            // TODO
        }

        if(success) {
            config.setBotToken(token);
            sender.sendMessage("§cSuccessfully connected to Telegram!");
        } else {
            sender.sendMessage("§cFailed to connect to Telegram. Wrong token?");
        }

        return true;
    }
}
