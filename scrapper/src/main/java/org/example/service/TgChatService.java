package org.example.service;

public interface TgChatService {
    void register(long tgChatId);

    void unregister(long tgChatId);
    void addLinkToChat(Long tgChatId, String url);
}
