package org.example.service;

import org.example.scrapperrestapi.dto.request.AddLinkRequest;
import org.example.scrapperrestapi.dto.response.LinkResponse;
import org.example.scrapperrestapi.dto.response.ListLinksResponse;

import java.net.URI;

public interface LinkService {
    LinkResponse removeLinkFromChat(long tgChatId, String url);
    ListLinksResponse listAll(long tgChatId);
}
