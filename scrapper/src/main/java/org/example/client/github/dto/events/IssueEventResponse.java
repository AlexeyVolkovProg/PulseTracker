package org.example.client.github.dto.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.client.github.dto.RepoEventResponse;
import org.example.client.github.dto.events.records.IssueResponse;

import java.time.OffsetDateTime;


/**
 * Отвечает за проблемы в репозитории
 */
@Getter
@Setter
@RequiredArgsConstructor
public class IssueEventResponse extends RepoEventResponse {

    @JsonProperty("payload")
    private IssueEventPayload issueEventPayload;


    @Getter
    @Setter
    public static class IssueEventPayload{
        @JsonProperty("action")
        private String action;
        @JsonProperty("issue")
        private IssueResponse issue;
    }
}
