package org.example.client.github.dto.events;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.client.github.dto.RepoEventResponse;


/**
 * Класс для отлавливания не отслеживаемых событий в репозиториях
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnknownEvent extends RepoEventResponse {
    @JsonProperty("type")
    private String eventType;  // Поля или методы, если необходимо


}
