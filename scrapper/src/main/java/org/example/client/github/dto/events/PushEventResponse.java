package org.example.client.github.dto.events;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.client.github.dto.RepoEventResponse;

import java.util.List;

/**
 * События отвечающие за отслеживание слияний веток в репозитории
 */

@Getter
@Setter
@NoArgsConstructor
public class PushEventResponse extends RepoEventResponse {



    @JsonProperty("payload")
    private PushEventPayload pushEventPayload;

    public PushEventResponse(String id, String type, String createDate) {
        super(id, type, createDate);
    }


    public static class PushEventPayload{
        @JsonProperty("ref")
        private String ref;

        @JsonProperty("commits")
        private List commits;
    }
}
