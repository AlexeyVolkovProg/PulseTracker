package org.example.scrapperrestapi.info;


import lombok.Getter;

@Getter
public enum ScrapperApiEndpoints {
    TG_CHAT_URL("/tg-chat/{id}", "Запросы на добавление и удаление чатов по id"),
    LINK_URL("/links", "Запросы на добавления, удаление и список отслеживаемых ссылок")
    ;

    private final String url;

    private final String description;


    ScrapperApiEndpoints(String url, String description) {
        this.url = url;
        this.description = description;
    }
}
