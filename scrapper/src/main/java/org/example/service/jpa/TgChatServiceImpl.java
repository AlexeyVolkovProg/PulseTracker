package org.example.service.jpa;

import org.example.model.Chat;
import org.example.model.Link;
import org.example.repository.ChatRepository;
import org.example.repository.LinkRepository;
import org.example.scrapperrestapi.dto.response.LinkResponse;
import org.example.service.TgChatService;
import org.hibernate.annotations.Cache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TgChatServiceImpl implements TgChatService {

    private final ChatRepository chatRepository;

    private final LinkRepository linkRepository;

    public TgChatServiceImpl(ChatRepository chatRepository, LinkRepository linkRepository) {
        this.chatRepository = chatRepository;
        this.linkRepository = linkRepository;
    }

    //метод заглушка, далее будет добавлено взаимодействие с реальной бд
    @Override
    @Transactional
    public void register(long tgChatId) {
        System.out.println("Был добавлен чат с id " + tgChatId);
        chatRepository.save(new Chat(tgChatId));
    }


    //метод заглушка, далее будет добавлено взаимодействие с реальной бд
    @Override
    public void unregister(long tgChatId) {
        System.out.println("Был удален чат с id " + tgChatId);
        chatRepository.deleteById(tgChatId);
    }

    @Override
    @Transactional
    public LinkResponse addLinkToChat(Long tgChatId, String url){
        Chat chat = chatRepository.findById(tgChatId).orElseThrow(() -> new RuntimeException("Chat not found"));
        Link link = linkRepository.findByUrl(url).orElseGet(() -> {
            Link newLink = new Link();
            newLink.setUrl(url);
            return linkRepository.save(newLink);
        });
        chat.addLink(link);
        chatRepository.save(chat);
        linkRepository.save(link);
        return new LinkResponse(tgChatId, url);
    }


}
