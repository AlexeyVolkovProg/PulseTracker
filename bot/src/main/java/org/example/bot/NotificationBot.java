package org.example.bot;


import lombok.Getter;
import lombok.Setter;
import org.example.commands.CommandManager;
import org.example.configuration.ApplicationConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.menubutton.MenuButton;


@Component
@Getter
@Setter
public class NotificationBot extends TelegramLongPollingBot {

    private final String botName;
    private final String telegramToken;

    private final CommandManager commandManager;


    public NotificationBot(ApplicationConfig applicationConfig, CommandManager commandManager) {
        this.botName = applicationConfig.botName();
        this.telegramToken = applicationConfig.telegramToken();
        this.commandManager = commandManager;
        this.commandManager.menuInitializer(this);
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
        if (update.getMessage() != null && update.getMessage().hasText()){
            commandManager.commandHandler(update, this);

        }
    }


}


