package org.example.commands.impl;

import lombok.Getter;
import lombok.Setter;
import org.example.commands.BaseCommand;
import org.example.commands.info.AdditionalInfo;
import org.example.commands.info.CommandInfo;
import org.example.database.TrackLinkDB;
import org.example.parsing.info.HostInfo;
import org.example.parsing.managers.ParsingManager;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
@Getter
@Setter
public class TrackCommandImpl implements BaseCommand {

    public static final CommandInfo commandInfo = CommandInfo.TRACK;

    private final ParsingManager parsingManager;

    private final TrackLinkDB linkDB; // заглушка для временного хранения ссылок, пока не напишу модуль scrapper

    private final Integer CURRENT_COUNT_ARGS = 1;

    private static final Integer LINK_POSITION = 0;

    public TrackCommandImpl(ParsingManager parsManager, TrackLinkDB linkDB) {
        this.parsingManager = parsManager;
        this.linkDB = linkDB;
    }

    @Override
    public void executeCommand(String[] commandArgs, Update update, AbsSender bot) {
        Long userId = update.getMessage().getChatId();
        if(checkArgs(commandArgs)){
            String currentLink = commandArgs[LINK_POSITION];
            HostInfo hostInfo = parsingManager.findResource(currentLink);
            if(hostInfo != null){
                linkDB.addLink(userId, currentLink);
                sendText(userId, AdditionalInfo.ADD_LINK_MESSAGE.getMessage()+ hostInfo.getResourceNameURL() + " распознана и добавлена в БД", bot);
            }else{
                sendText(userId, AdditionalInfo.LINK_ADD_ERROR.getMessage(), bot);
            }

        }else{
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
