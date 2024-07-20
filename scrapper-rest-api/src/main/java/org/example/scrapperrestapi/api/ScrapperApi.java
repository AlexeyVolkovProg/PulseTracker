package org.example.scrapperrestapi.api;

import org.example.scrapperrestapi.dto.request.AddLinkRequest;
import org.example.scrapperrestapi.dto.response.LinkResponse;
import org.example.scrapperrestapi.dto.response.ListLinksResponse;
import org.example.scrapperrestapi.dto.request.RemoveLinkRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ScrapperApi {

    /**
     * Регистрация чата(пользователя) с указанным Id
     */
    ResponseEntity <?> registerChat(Long id);

    /**
     * Удаление чата(пользователя) с указанным Id
     */
    ResponseEntity <?> deleteChat(Long id);


    /**
     * Получение списка всех отслеживаемых ссылок для конкретного пользователя
     */
    ResponseEntity <ListLinksResponse> getTrackLinks(Long tgChatId);

    /**
     * Добавления новой отслеживаемой ссылки для конкретного пользователя
     */
    ResponseEntity <LinkResponse> addTrackLink(Long tgChatId, @RequestBody AddLinkRequest addLink);


    /**
     * Удаление конкретной отслеживаемой ссылки для конкретного пользователя
     */
    ResponseEntity <LinkResponse> deleteTrackLink(Long tgChatId, @RequestBody RemoveLinkRequest removeLinkRequest);


}

