package org.example.client;

import org.example.scrapperrestapi.api.ScrapperApi;
import org.example.scrapperrestapi.dto.request.AddLinkRequest;
import org.example.scrapperrestapi.dto.request.RemoveLinkRequest;
import org.example.scrapperrestapi.dto.response.LinkResponse;
import org.example.scrapperrestapi.dto.response.ListLinksResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ScrapperClient implements ScrapperApi {

    private final WebClient webClient;

    public ScrapperClient(WebClient webClient) {
        this.webClient = webClient;
    }


    /**
     * Запрос на регистрацию чата
     */
    @Override
    public ResponseEntity<?> registerChat(Long id) {
        return webClient.post()
                .uri("/tg-chat/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    /**
     * Запрос на удаление зарегистрированного чата
     */
    @Override
    public ResponseEntity<?> deleteChat(Long id) {
        return webClient.delete()
                .uri("/tg-chat/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    /**
     * Получить все отслеживаемые ссылки для конкретного чата
     */
    @Override
    public ResponseEntity<ListLinksResponse> getTrackLinks(Long tgChatId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/links")
                        .queryParam("Tg-Chat-Id", tgChatId)
                        .build()
                ).retrieve()
                .toEntity(ListLinksResponse.class)
                .block();
    }


    /**
     * Запрос на добавление новой отслеживаемой ссылки
     */
    @Override
    public ResponseEntity<LinkResponse> addTrackLink(Long tgChatId, AddLinkRequest addLink) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/links")
                        .queryParam("Tg-Chat-Id", tgChatId).build())
                .bodyValue(addLink)
                .retrieve()
                .toEntity(LinkResponse.class)
                .block();
    }


    /**
     * Запрос на удаление отслеживаемой ссылки
     */
    @Override
    public ResponseEntity<LinkResponse> deleteTrackLink(Long tgChatId, RemoveLinkRequest removeLinkRequest) {
        return webClient.method(HttpMethod.DELETE)
                .uri(uriBuilder -> uriBuilder
                        .path("/links")
                        .queryParam("Tg-Chat-Id", tgChatId).build())
                .bodyValue(removeLinkRequest)
                .retrieve()
                .toEntity(LinkResponse.class)
                .block();
    }
}
