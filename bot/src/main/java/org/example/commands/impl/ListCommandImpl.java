package org.example.commands.impl;

import lombok.Getter;
import lombok.Setter;
import org.example.commands.BaseCommand;
import org.example.commands.info.AdditionalInfo;
import org.example.commands.info.CommandInfo;
import org.example.database.TrackLinkDB;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
@Getter
@Setter
public class ListCommandImpl implements BaseCommand {
    private static final CommandInfo commandInfo = CommandInfo.LIST;
    private final Integer CURRENT_COUNT_ARGS = 0;

    private final TrackLinkDB linkDB;

    public ListCommandImpl(TrackLinkDB linkDB) {
        this.linkDB = linkDB;
    }

    @Override
    public void executeCommand(String[] commandArgs, Update update, AbsSender bot) {
        Long userId = update.getMessage().getChatId();
        if (checkArgs(commandArgs)) {
            if (linkDB.getTrackLinks().get(userId) != null && !linkDB.getTrackLinks().get(userId).isEmpty()) {
                sendText(userId, "Список отслеживаемых ссылок: \n" + buildLinksDescription(userId), bot);
            }else{
                sendText(userId, AdditionalInfo.EMPTY_LINK_LIST.getMessage(), bot);
            }

        } else {
            sendText(userId, AdditionalInfo.INCORRECT_ARGS.getMessage()
                    + CURRENT_COUNT_ARGS, bot);
        }
    }

    public String buildLinksDescription(Long userId) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String currentLink : linkDB.getTrackLinks().get(userId)) {
            stringBuilder
                    .append(currentLink)
                    .append("\n");
        }
        return stringBuilder.toString();
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
