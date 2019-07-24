package com.gmail.maelgrove.SgcraftTlg;

import com.gmail.maelgrove.SgcraftTlg.Telegram.Telegram;
import com.gmail.maelgrove.SgcraftTlg.Utils.Config;
import com.gmail.maelgrove.SgcraftTlg.Utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class SgcraftPlugin extends JavaPlugin implements Listener {

    private Telegram bot;

    @Override
    public void onEnable() {

        // initialize
        Bukkit.getPluginManager().registerEvents(this, this);
        Config.initialize(getConfig());
        saveConfig();

        bot = new Telegram();
        bot.auth(Config.Default.getBotToken());
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (bot.authJson == null)
            return;
        bot.sendMsg(Messages.formatMSG(Messages.PlayerJoined, e.getPlayer().getName())[0]);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (bot.authJson == null)
            return;
        bot.sendMsg(Messages.formatMSG(Messages.PlayerDied, e.getDeathMessage())[0]);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (bot.authJson == null)
            return;
        bot.sendMsg(Messages.formatMSG(Messages.PlayerLeft, e.getPlayer().getName())[0]);
    }

}
