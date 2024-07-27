package org.example.service;

import org.example.scrapperrestapi.dto.response.LinkResponse;

public interface TgChatService {
    void register(long tgChatId);

    void unregister(long tgChatId);
    LinkResponse addLinkToChat(Long tgChatId, String url);
}
