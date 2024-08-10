package org.example.commands.impl;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.commands.BaseCommand;
import org.example.commands.info.AdditionalInfo;
import org.example.commands.info.CommandInfo;
import org.example.service.ScrapperService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
@Getter
@Setter
@Slf4j
public class StartCommandImpl implements BaseCommand {

    public static final CommandInfo commandInfo = CommandInfo.START;

    private final Integer CURRENT_COUNT_ARGS = 0;

    private ScrapperService scrapperService;

    public StartCommandImpl(ScrapperService scrapperService) {
        this.scrapperService = scrapperService;
    }



    @Override
    public void executeCommand(String[] commandArgs, Update update, AbsSender bot) {
        //todo не забудь проверить кол-во аргументов команды
        log.info("начал старт");
        Long userId = update.getMessage().getChatId();
        if (checkArgs(commandArgs)) {
            if (scrapperService.registerChat(userId).isSuccess()){
                sendText(userId, AdditionalInfo.START_MESSAGE.getMessage(), bot); // успешная регистрация чата
            }else{
                sendText(userId, AdditionalInfo.CHAT_ERROR.getMessage(), bot);
            }
        } else {
            sendText(userId, AdditionalInfo.INCORRECT_ARGS.getMessage() + CURRENT_COUNT_ARGS, bot);
        }
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
