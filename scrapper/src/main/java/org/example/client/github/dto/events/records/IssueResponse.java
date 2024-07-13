package org.example.client.github.dto.events.records;

import com.fasterxml.jackson.annotation.JsonProperty;

public record IssueResponse(
        @JsonProperty("url")
        String url,
        @JsonProperty("state")
        String state,
        @JsonProperty("title")
        String title,
        @JsonProperty("body")
        String body,
        @JsonProperty("user")
        UserInfoResponse user
) {

}
