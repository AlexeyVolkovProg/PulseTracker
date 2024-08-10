package org.example.commands.impl;

import lombok.Getter;
import lombok.Setter;
import org.example.commands.BaseCommand;
import org.example.commands.info.AdditionalInfo;
import org.example.commands.info.CommandInfo;
import org.example.dto.ResponseResult;
import org.example.scrapperrestapi.dto.response.LinkResponse;
import org.example.service.ScrapperService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Getter
@Setter
public class ListCommandImpl implements BaseCommand {
    private static final CommandInfo commandInfo = CommandInfo.LIST;
    private final Integer CURRENT_COUNT_ARGS = 0;

    private ScrapperService scrapperService;


    public ListCommandImpl(ScrapperService scrapperService){
        this.scrapperService = scrapperService;
    }

    @Override
    public void executeCommand(String[] commandArgs, Update update, AbsSender bot) {
        Long userId = update.getMessage().getChatId();
        if (checkArgs(commandArgs)) {
//            if (linkDB.getTrackLinks().get(userId) != null && !linkDB.getTrackLinks().get(userId).isEmpty()) {
//                sendText(userId, "Список отслеживаемых ссылок: \n" + buildLinksDescription(userId), bot);
//            }else{
//                sendText(userId, AdditionalInfo.EMPTY_LINK_LIST.getMessage(), bot);
//            }
//            ResponseResult response = scrapperService.addLinkToChat(userId, currentLink);
//            if (response.isSuccess()) { // проверка были ли ошибки при добавлении
//                sendText(userId, AdditionalInfo.ADD_LINK_MESSAGE.getMessage() + hostInfo.getResourceNameURL(), bot);
//            } else {
//                sendText(userId, AdditionalInfo.LINK_ADD_ERROR.getMessage(), bot);
//            }
            ResponseResult responseResult = scrapperService.getLinksFromChat(userId);

            if(responseResult.isSuccess()){
                sendText(userId, "Список отслеживаемых ссылок: \n" + buildLinksDescription(responseResult.getLinksList()), bot);
            } else{
                sendText(userId, "Ошибка вывода списка", bot); // todo тоже сменить сообщение
            }

        } else {
            sendText(userId, AdditionalInfo.INCORRECT_ARGS.getMessage()
                    + CURRENT_COUNT_ARGS, bot);
        }
    }

    public String buildLinksDescription(List<LinkResponse> listLinks) {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String currentLink : linkDB.getTrackLinks().get(userId)) {
//            stringBuilder
//                    .append(currentLink)
//                    .append("\n");
//        }
//        return stringBuilder.toString();
        if (!listLinks.isEmpty()){
            return listLinks.stream()
                    .map(LinkResponse::getUri)
                    .collect(Collectors.joining("\n"));
        }else{
            return "на данный момент пуст"; // todo сменить на какую-то enum фразу
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
