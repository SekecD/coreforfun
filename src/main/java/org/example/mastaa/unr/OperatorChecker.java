package org.example.mastaa.unr;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;
import net.md_5.bungee.api.plugin.Listener;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;

public class OperatorChecker implements Listener {

    private final Core core;
    private final TelegramBot telegramBot;
    private final HashMap<ProxiedPlayer, Integer> attempts;

    public OperatorChecker(Core core, String botToken) {
        this.core = core;
        this.telegramBot = new TelegramBot(botToken);
        this.attempts = new HashMap<>();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onLoginEvent(LoginEvent event) {
        String operatorPassword = core.generateRandomPassword();
        core.setOperatorPassword(operatorPassword);
        String message = "Новый пароль оператора: " + operatorPassword;
        telegramBot.sendPasswordToGroup(message);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onServerConnectEvent(ServerConnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        if (player.hasPermission("bungeecord.command.server")) {
            resetAttempts(player);
        }
    }

    public void resetAttempts(ProxiedPlayer player) {
        attempts.put(player, 0);
    }

    public void incrementAttempts(ProxiedPlayer player) {
        attempts.put(player, attempts.get(player) + 1);
    }

    public int getMaxAttempts() {
        return 3;
    }

    public int getAttempts(ProxiedPlayer player) {
        return attempts.get(player);
    }

    public TelegramBot getTelegramBot() {
        return telegramBot;
    }
}
