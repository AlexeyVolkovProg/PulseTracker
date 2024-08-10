package org.example.commands.impl;

import lombok.Getter;
import lombok.Setter;
import org.example.commands.BaseCommand;
import org.example.commands.info.AdditionalInfo;
import org.example.commands.info.CommandInfo;
import org.example.dto.ResponseResult;
import org.example.parsing.info.HostInfo;
import org.example.parsing.managers.ParsingManager;
import org.example.service.ScrapperService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
@Getter
@Setter
public class TrackCommandImpl implements BaseCommand {

    public static final CommandInfo commandInfo = CommandInfo.TRACK;

    private final Integer CURRENT_COUNT_ARGS = 1;

    private static final Integer LINK_POSITION = 0;

    private final ParsingManager parsingManager;

    private ScrapperService scrapperService;

    public TrackCommandImpl(ParsingManager parsManager, ScrapperService scrapperService) {
        this.parsingManager = parsManager;
        this.scrapperService = scrapperService;
    }

    @Override
    public void executeCommand(String[] commandArgs, Update update, AbsSender bot) {
        Long userId = update.getMessage().getChatId();
        System.out.println("выполняем добавление");
        if (checkArgs(commandArgs)) {
            String currentLink = commandArgs[LINK_POSITION];
            System.out.println("спарсили ссылку " + currentLink);
            HostInfo hostInfo = parsingManager.findResource(currentLink);
            if (hostInfo != null) { // проверяем поддерживается ли данный ресурс для отслеживания
                ResponseResult response = scrapperService.addLinkToChat(userId, currentLink);
                if (response.isSuccess()) { // проверка были ли ошибки при добавлении
                    sendText(userId, AdditionalInfo.ADD_LINK_MESSAGE.getMessage() + hostInfo.getResourceNameURL(), bot);
                } else {
                    System.out.println("произошла жопа");
                    sendText(userId, AdditionalInfo.LINK_ADD_ERROR.getMessage(), bot);
                }
            } else {
                sendText(userId, AdditionalInfo.LINK_ERROR.getMessage(), bot);
            }

        } else {
            sendText(userId, AdditionalInfo.INCORRECT_ARGS.getMessage()
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
