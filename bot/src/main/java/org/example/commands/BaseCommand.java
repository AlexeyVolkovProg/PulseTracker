package org.example.commands;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface BaseCommand {

    String getName();

    String getDescription();

    void executeCommand(String[] commandArgs, Update update, AbsSender bot);

    Boolean checkArgs(String[] commandArgs);

    default void sendText(Long userId, String messageText, AbsSender bot) {
        SendMessage currentMessage = SendMessage.builder().chatId(userId.toString()).text(messageText).build();
        try {
            bot.execute(currentMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
