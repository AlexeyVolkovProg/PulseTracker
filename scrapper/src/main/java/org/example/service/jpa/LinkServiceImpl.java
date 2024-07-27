package org.example.service.jpa;

import org.example.model.Link;
import org.example.repository.ChatRepository;
import org.example.repository.LinkRepository;
import org.example.scrapperrestapi.dto.response.LinkResponse;
import org.example.scrapperrestapi.dto.response.ListLinksResponse;
import org.example.service.LinkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;

    public LinkServiceImpl(LinkRepository linkRepository, ChatRepository chatRepository) {
        this.linkRepository = linkRepository;
    }

    /**
     * Удалить отслеживание ссылки для конкретного чата
     */

    @Override
    public LinkResponse removeLinkFromChat(long tgChatId, String url) {
        return null;
    }

    /**
     * Получить список всех отслеживаемых ссылок для конкретного пользователя
     */
    @Override
    @Transactional
    public ListLinksResponse listAll(long tgChatId) {
        List<Link> links = linkRepository.findAllByChatsId(tgChatId);
        List<LinkResponse> linkResponses = links.stream()
                .map(link -> {
                    LinkResponse linkResponse = new LinkResponse();
                    linkResponse.setId(link.getId()); // todo присвой нормальный айдишник
                    linkResponse.setUri(link.getUrl());
                    return linkResponse;
                })
                .collect(Collectors.toList());
        ListLinksResponse listLinksResponse = new ListLinksResponse();
        listLinksResponse.setLinkResponsesList(linkResponses);
        listLinksResponse.setSize(linkResponses.size());
        return listLinksResponse;
    }
}
