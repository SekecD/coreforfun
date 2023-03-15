package org.example.mastaa.unr;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TelegramBot extends TelegramLongPollingBot {

    private String botToken;

    public TelegramBot(String botToken) {
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Можете добавить обработку сообщений от пользователей, если это необходимо
    }

    public void sendPasswordToGroup(String operatorPassword) {
        SendMessage message = new SendMessage();
        message.setChatId("-1001847483154");
        message.setText("PASSWORD IS: " + operatorPassword);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

        public void sendIncorrectPasswordMessage(String playerName) {
            SendMessage message = new SendMessage();
            message.setChatId("-1001847483154"); // Замените на ID вашего чата в Telegram
            message.setText("Неверный пароль введен игроком: " + playerName + "\nВремя (GMT+3): " + getCurrentTime());

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        public void sendSuccessfulLoginMessage(String playerName, String ipAddress, String geoIpAddress) {
    SendMessage message = new SendMessage();
    message.setChatId("-1001847483154"); // Замените на ID вашего чата в Telegram
    message.setText("Удачный вход:\nИгрок: " + playerName + "\nВремя (GMT+3): " + getCurrentTime() + "\nIP: " + ipAddress + "\nРегион IP: " + geoIpAddress);

    try {
        execute(message);
    } catch (TelegramApiException e) {
        e.printStackTrace();
    }
}

    public void sendSuspiciousActivityMessage(String playerName, String playerIP) {
        SendMessage message = new SendMessage();
        message.setChatId("-1001847483154");
        message.setText("Похоже на взлом:\nИгрок: " + playerName + "\nIP: " + playerIP + "\nВремя (GMT+3): " + getCurrentTime());

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("GMT+3"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return now.format(formatter);
    }

    @Override
    public String getBotUsername() {
        return "sdfljksdfkjlsdflkjsdflkjsfd_bot"; // Замените на имя вашего бота
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
