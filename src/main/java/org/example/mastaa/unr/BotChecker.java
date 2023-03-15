package org.example.mastaa.unr;

import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class BotChecker implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerLogin(LoginEvent event) {
        PendingConnection connection = event.getConnection();
        String playerName = connection.getName();

        // Проверяем, состоит ли имя игрока только из цифр
        boolean isBot = playerName.matches("\\d+");

        if (isBot) {
            event.setCancelled(true);
            event.setCancelReason("§7[§cCore§7]§f Ваш аккаунт был определен как бот. Если это ошибка, обратитесь к администратору.");
        }
    }
}
