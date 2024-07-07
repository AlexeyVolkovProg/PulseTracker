package org.example.client.github.dto.events;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.client.github.dto.RepoEventResponse;

import java.time.OffsetDateTime;

/**
 * Отвечает за создание pull request в репозиторий
 */
@Getter
@Setter
@RequiredArgsConstructor
public class PullEventResponse extends RepoEventResponse {



    @JsonProperty("payload")
    private PullEventPayload pullEventPayload;

    @Getter
    @Setter
    public static class PullEventPayload{

        @JsonProperty("action")
        private String action;
    }
}
