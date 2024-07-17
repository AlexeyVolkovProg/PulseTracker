package org.example.service.jdbc;

import org.example.scrapperrestapi.dto.response.LinkResponse;
import org.example.scrapperrestapi.dto.response.ListLinksResponse;
import org.example.service.LinkService;
import org.springframework.stereotype.Service;

@Service
public class LinkServiceImpl implements LinkService {

    //метод заглушка, далее будет добавлено взаимодействие с реальной бд
    @Override
    public LinkResponse add(long tgChatId, String url) {
        System.out.println("Была добавлена новая ссылка c id " + tgChatId + " и url: " + url);
        return new LinkResponse(tgChatId, url);
    }

    //метод заглушка, далее будет добавлено взаимодействие с реальной бд
    @Override
    public LinkResponse remove(long tgChatId, String url) {
        System.out.println("Была удалена ссылка c id " + tgChatId + " и url: " + url);
        return new LinkResponse(tgChatId, url);
    }

    //метод заглушка, далее будет добавлено взаимодействие с реальной бд
    @Override
    public ListLinksResponse listAll(long tgChatId) {
        return null;
    }
}
