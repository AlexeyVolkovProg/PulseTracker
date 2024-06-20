package org.example.commands;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Класс, отвечающий за запуск и парсинг команд из чата
 */
@Component
public class CommandManager {
    Map <String,BaseCommand> availableCommands;

    public CommandManager(List<BaseCommand> commands){
        availableCommands = commands.stream().collect(Collectors.toMap(BaseCommand::getName, command -> command));
    }

    public void commandHandler(Update update, AbsSender bot) {
        String currentCommandName = parseCommandName(update);
        String[] commandArgs = parseCommandArgs(update);
        if (availableCommands.containsKey(currentCommandName)){
            System.out.println("Была распознана команда " + currentCommandName);
            availableCommands.get(currentCommandName).executeCommand(commandArgs, update, bot);
        }else{
            try {
                bot.execute(SendMessage.builder().chatId(update.getMessage().getChatId().toString()).text("Была введена неизвестная команда").build());
            }catch (TelegramApiException e){
                System.out.println(e);
            }

        }
    }


    public String parseCommandName(Update update){
        return update.getMessage().getText().split("\\s+")[0];
    }

    public String[] parseCommandArgs(Update update){
        String currentMessage = update.getMessage().getText();
        String[] parts = currentMessage.split("\\s+");
        Arrays.stream(parts).forEach(System.out::println);
        return Arrays.copyOfRange(parts, 1, parts.length);
    }

}
