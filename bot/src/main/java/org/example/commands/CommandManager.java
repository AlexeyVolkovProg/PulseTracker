package org.example.commands;


import lombok.extern.slf4j.Slf4j;
import org.example.commands.info.AdditionalInfo;
import org.example.commands.info.CommandInfo;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScope;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Класс, отвечающий за запуск и парсинг команд из чата
 */
@Component
@Slf4j
public class CommandManager {
    Map <String,BaseCommand> availableCommands;

    public CommandManager(List<BaseCommand> commands){
        availableCommands = commands.stream().collect(Collectors.toMap(BaseCommand::getName, command -> command));
    }

    public void commandHandler(Update update, AbsSender bot) {
        String currentCommandName = parseCommandName(update);
        String[] commandArgs = parseCommandArgs(update);
        if (availableCommands.containsKey(currentCommandName)){
            log.info("Была распознана команда " + currentCommandName);
            availableCommands.get(currentCommandName).executeCommand(commandArgs, update, bot);
        }else{
            try {
                bot.execute(SendMessage.builder().chatId(update.getMessage().getChatId().toString()).text(AdditionalInfo.UNKNOWN_COMMAND.getMessage()).build());
            }catch (TelegramApiException e){
                System.out.println(e);
            }

        }
    }


    /**
     * Инициализация кнопки меню в боте со списком всех доступных команд
     * @param absSender
     */
    public void menuInitializer(AbsSender absSender) {
        List<BotCommand> baseCommands = new ArrayList<>();
        for(CommandInfo commandInfo: CommandInfo.values()){
            baseCommands.add( new BotCommand(commandInfo.getCommandName(), commandInfo.getDescription()));
        }
        try {
            absSender.execute(new SetMyCommands(baseCommands, new BotCommandScopeDefault(), null){
            });
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
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
