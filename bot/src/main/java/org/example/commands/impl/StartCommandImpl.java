package org.example.commands.impl;


import lombok.Getter;
import lombok.Setter;
import org.example.commands.BaseCommand;
import org.example.commands.info.AdditionalInfo;
import org.example.commands.info.CommandInfo;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
@Getter
@Setter
public class StartCommandImpl implements BaseCommand {

    public static final CommandInfo commandInfo = CommandInfo.START;

    private final Integer CURRENT_COUNT_ARGS = 0;


    @Override
    public void executeCommand(String[] commandArgs, Update update, AbsSender bot) {
        Long userId = update.getMessage().getChatId();
        sendText(userId, AdditionalInfo.START_MESSAGE.getMessage(), bot);
    }

    @Override
    public Boolean checkArgs(String[] commandArgs) {
        return commandArgs.length == CURRENT_COUNT_ARGS;
    }

    @Override
    public String getName() {
        return commandInfo.getCommandName();
    }

    @Override
    public String getDescription() {
        return commandInfo.getDescription();
    }
}
