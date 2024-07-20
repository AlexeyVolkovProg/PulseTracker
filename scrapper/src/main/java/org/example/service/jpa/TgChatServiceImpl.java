package org.example.service.jpa;

import org.example.model.Chat;
import org.example.model.Link;
import org.example.repository.ChatRepository;
import org.example.service.TgChatService;
import org.springframework.stereotype.Service;

@Service
public class TgChatServiceImpl implements TgChatService {

    private final ChatRepository chatRepository;

    public TgChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    //метод заглушка, далее будет добавлено взаимодействие с реальной бд
    @Override
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
    public void addLinkToChat(Long tgChatId, String url){
        Chat chat = chatRepository.findById(tgChatId).orElseThrow(() -> new RuntimeException("Chat not found"));
        chat.getLinks().add(new Link(tgChatId, url));
        chatRepository.save(chat);
    }
}
