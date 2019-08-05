package com.gmail.maelgrove.SgcraftTlg.Server.Commands;

import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.Methods.SendMessage;
import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.TelegramBot;
import com.gmail.maelgrove.SgcraftTlg.PluginConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Represents a Minecraft command sending a chat message to the bot.
 */
public class ChatCommand implements CommandExecutor {

    private TelegramBot bot;
    private PluginConfig config;

    /**
     * Specifies the command permission.
     */
    public static final String PERMISSION = "telegram.chat";

    /**
     * Specifies the command name.
     */
    public static final String COMMAND = "tgchat";

    public ChatCommand(PluginConfig config, TelegramBot bot) {
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
            sender.sendMessage(String.format("§c/%s [message]", COMMAND));
            return true;
        }

        String message = String.join(" ", args);

        bot.sendMessage(new SendMessage()
            .setChatId(config.getEventChatId())
            .setText(String.format("%1$s: %2$s", sender.getName(), message)));
        return true;
    }
}