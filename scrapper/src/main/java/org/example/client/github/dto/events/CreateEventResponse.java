package org.example.client.github.dto.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.client.github.dto.RepoEventResponse;

import java.time.OffsetDateTime;

/**
 * События, отвечающие за создание новой ветки
 */

@Getter
@Setter
public class CreateEventResponse extends RepoEventResponse {

    @JsonProperty("payload")
    private PayloadCreateEvent payloadCreateEvent;

    public CreateEventResponse() {
        super.setType("CreateEvent");
    }


    @Getter
    @Setter
    public static class PayloadCreateEvent {

        @JsonProperty("ref")
        private String ref;

        @JsonProperty("ref_type")
        private String ref_type;
    }
}
