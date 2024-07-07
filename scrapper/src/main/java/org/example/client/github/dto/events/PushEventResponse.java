package org.example.client.github.dto.events;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.client.github.dto.RepoEventResponse;

import java.util.List;

/**
 * События отвечающие за отслеживание слияний веток в репозитории
 */

@Getter
@Setter
@RequiredArgsConstructor
public class PushEventResponse extends RepoEventResponse {



    @JsonProperty("payload")
    private PushEventPayload pushEventPayload;



    public static class PushEventPayload{
        @JsonProperty("ref")
        private String ref;

        @JsonProperty("commits")
        private List commits;
    }
}
