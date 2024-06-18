package org.example.bot;


import lombok.Getter;
import lombok.Setter;
import org.example.configuration.ApplicationConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;


@Component
@Getter
@Setter
public class NotificationBot extends TelegramLongPollingBot {

    private final String botName;
    private final String telegramToken;


    public NotificationBot(ApplicationConfig applicationConfig) {
        this.botName = applicationConfig.botName();
        this.telegramToken = applicationConfig.telegramToken();
    }

    @Override
    public String getBotUsername() {
        return this.getBotName();
    }

    @Override
    public String getBotToken() {
        return this.getTelegramToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
    }
}


