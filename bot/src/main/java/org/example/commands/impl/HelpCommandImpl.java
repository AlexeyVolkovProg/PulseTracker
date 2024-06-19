package org.example.commands.impl;

import org.example.commands.BaseCommand;
import org.example.commands.info.CommandInfo;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class HelpCommandImpl implements BaseCommand {

    private static final CommandInfo commandInfo = CommandInfo.HELP;

    private final Integer CURRENT_COUNT_ARGS = 0;

    @Override
    public void executeCommand(String[] commandArgs, Update update, AbsSender bot) {
        Long userId = update.getMessage().getChatId();
        if (checkArgs(commandArgs)) {
            sendText(userId, "Пока что не сделана", bot);
        } else {
            sendText(userId, "Ошибка кол-ва аргументов команды. Корректное кол-во: "
                    + CURRENT_COUNT_ARGS, bot);
        }
    }

    @Override
    public String getName() {
        return commandInfo.getCommandName();
    }

    @Override
    public String getDescription() {
        return commandInfo.getDescription();
    }

    @Override
    public Boolean checkArgs(String[] commandArgs) {
        return commandArgs.length == CURRENT_COUNT_ARGS;
    }
}
