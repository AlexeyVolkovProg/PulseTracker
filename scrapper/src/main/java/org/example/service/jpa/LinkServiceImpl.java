package org.example.service.jpa;

import org.example.exception.ChatNotFoundException;
import org.example.exception.LinkNotFoundException;
import org.example.model.Chat;
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

    private final ChatRepository chatRepository;

    public LinkServiceImpl(LinkRepository linkRepository, ChatRepository chatRepository, ChatRepository chatRepository1) {
        this.linkRepository = linkRepository;
        this.chatRepository = chatRepository1;
    }

    /**
     * Удалить отслеживаемую ссылку для конкретного чата
     */

    @Override
    @Transactional
    public LinkResponse removeLinkFromChat(long tgChatId, String url) {
        Chat chat = chatRepository.findById(tgChatId).orElseThrow(() -> new ChatNotFoundException("Chat not found"));
        Link link = linkRepository.findByUrl(url).orElseThrow(() -> new LinkNotFoundException("Link not Found"));
        chat.removeLink(link);
        return new LinkResponse(tgChatId, url);
    }

    /**
     * Получить список всех отслеживаемых ссылок для конкретного пользователя
     */
    @Override
    @Transactional
    public ListLinksResponse listAll(long tgChatId) {
        Chat chat = chatRepository.findById(tgChatId).orElseThrow(() -> new ChatNotFoundException("Chat not found"));
        List<Link> links = linkRepository.findAllByChatsId(chat.getId());
        links.forEach(System.out::println);
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
