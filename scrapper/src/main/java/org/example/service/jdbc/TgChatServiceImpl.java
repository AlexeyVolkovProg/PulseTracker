package org.example.service.jdbc;

import org.example.service.TgChatService;
import org.springframework.stereotype.Service;

@Service
public class TgChatServiceImpl implements TgChatService {

    //метод заглушка, далее будет добавлено взаимодействие с реальной бд
    @Override
    public void register(long tgChatId) {
        System.out.println("Был добавлен чат с id " + tgChatId);
    }


    //метод заглушка, далее будет добавлено взаимодействие с реальной бд
    @Override
    public void unregister(long tgChatId) {
        System.out.println("Был удален чат с id " + tgChatId);
    }
}
