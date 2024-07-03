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
public class HelpCommandImpl implements BaseCommand {

    private static final CommandInfo commandInfo = CommandInfo.HELP;

    private final Integer CURRENT_COUNT_ARGS = 0;

    @Override
    public void executeCommand(String[] commandArgs, Update update, AbsSender bot) {
        Long userId = update.getMessage().getChatId();
        if (checkArgs(commandArgs)) {
            sendText(userId, buildAllCommandsDescription(), bot);
        } else {
            sendText(userId, AdditionalInfo.INCORRECT_ARGS.getMessage()
                    + CURRENT_COUNT_ARGS, bot);
        }
    }

    public String buildAllCommandsDescription(){
        StringBuilder stringBuilder = new StringBuilder();
        for(CommandInfo commandInfo1: CommandInfo.values()){
            stringBuilder.append(commandInfo1.getCommandName()).append(" ")
                    .append(commandInfo1.getDescription())
                    .append("\n");
        }
        return stringBuilder.toString();
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
