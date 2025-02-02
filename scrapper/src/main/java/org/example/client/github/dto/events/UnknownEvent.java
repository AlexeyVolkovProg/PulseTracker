package org.example.client.github.dto.events;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.client.github.dto.RepoEventResponse;


/**
 * Класс для отлавливания не отслеживаемых событий в репозиториях
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnknownEvent extends RepoEventResponse {
    @JsonProperty("type")
    private String eventType;


    public UnknownEvent() {
        super.setType("UnknownEvent");
    }
}
