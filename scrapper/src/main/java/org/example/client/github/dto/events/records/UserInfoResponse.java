package org.example.client.github.dto.events.records;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserInfoResponse(
        @JsonProperty("login") String username
) {
}
