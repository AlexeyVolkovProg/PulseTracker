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
    ResponseEntity <?> registerChat( Integer id);

    /**
     * Удаление чата(пользователя) с указанным Id
     */
    ResponseEntity <?> deleteChat(Integer id);


    /**
     * Получение списка всех отслеживаемых ссылок для конкретного пользователя
     */
    ResponseEntity <ListLinksResponse> getTrackLinks(Integer tgChatId);

    /**
     * Добавления новой отслеживаемой ссылки для конкретного пользователя
     */
    ResponseEntity <LinkResponse> addTrackLink(Integer tgChatId, @RequestBody AddLinkRequest addLink);


    /**
     * Удаление конкретной отслеживаемой ссылки для конкретного пользователя
     */
    ResponseEntity <LinkResponse> deleteTrackLink(Integer tgChatId, @RequestBody RemoveLinkRequest removeLinkRequest);


}

