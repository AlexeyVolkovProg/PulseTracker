package org.example.service.jpa;

import org.example.model.Chat;
import org.example.model.Link;
import org.example.repository.ChatRepository;
import org.example.repository.LinkRepository;
import org.example.scrapperrestapi.dto.response.LinkResponse;
import org.example.scrapperrestapi.dto.response.ListLinksResponse;
import org.example.service.LinkService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;

    private final ChatRepository chatRepository;

    public LinkServiceImpl(LinkRepository linkRepository, ChatRepository chatRepository) {
        this.linkRepository = linkRepository;
        this.chatRepository = chatRepository;
    }


    @Override
    public LinkResponse add(long tgChatId, String url) {
        Chat chat = chatRepository.findById(tgChatId)
                .orElseThrow(() -> new RuntimeException("Chat not found"));

        Link link = new Link(url);
        chat.getLinks().add(link);
        chatRepository.save(chat);
        linkRepository.save(link);
        return new LinkResponse(tgChatId, url);
    }


    /**
     * Удалить отслеживание ссылки для конкретного чата
     */
    @Override
    public LinkResponse removeLinkFromChat(long tgChatId, String url) {
        Chat chat = chatRepository.findById(tgChatId).orElseThrow(() -> new RuntimeException("Chat not found"));
        Optional<Link> linkToRemove = chat.getLinks().stream().filter(link -> link.getUrl().equals(url)).findFirst();
        if (linkToRemove.isPresent()){
            chat.getLinks().remove(linkToRemove.get());
            chatRepository.save(chat);
            return new LinkResponse(tgChatId, url);
        }else{
            throw new RuntimeException("Link not found");
        }

    }


    /**
     * Получить список всех отслеживаемых ссылок для конкретного пользователя
     */
    @Override
    public ListLinksResponse listAll(long tgChatId){
        Chat chat = chatRepository.findById(tgChatId).orElseThrow(() -> new RuntimeException("Chat not found"));
        List<LinkResponse> linkResponses = chat.getLinks().stream()
                .map(link ->{
                    LinkResponse linkResponse = new LinkResponse();
                    linkResponse.setId(link.getId());
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
