package org.example.mastaa.unr;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.security.SecureRandom;
import java.util.Base64;

public class Core extends Plugin {

    private String operatorPassword;
    private OperatorChecker operatorChecker;
    private TelegramBot telegramBot;

    @Override
    public void onEnable() {
        // Обработчик команды
        getProxy().getPluginManager().registerCommand(this, new AdminPasswordCommand(this));

        // Обработчик событий
        String botToken = "6148454708:AAF5JTDQstyyYpV06zYBZ31qOTK2eI4cfnA";
        operatorChecker = new OperatorChecker(this, botToken);
        getProxy().getPluginManager().registerListener(this, operatorChecker);

        // Дополнительные команды из вашего кода
        getProxy().getPluginManager().registerCommand(this, new CheckMyLocationCommand());
        getProxy().getPluginManager().registerCommand(this, new GtpCommand());
    }

    public String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    public String getOperatorPassword() {
        return operatorPassword;
    }

    public void setOperatorPassword(String operatorPassword) {
        this.operatorPassword = operatorPassword;
    }

    public TelegramBot getTelegramBot(){
        return telegramBot;
    }
    public OperatorChecker getOperatorChecker() {
    return operatorChecker;
}
}
