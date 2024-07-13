package org.example.client.github.dto.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
public class IssueEventResponse extends RepoEventResponse {

    @JsonProperty("payload")
    private IssueEventPayload issueEventPayload;

    public IssueEventResponse() {
        super.setType("IssueEvent");
    }


    @Getter
    @Setter
    public static class IssueEventPayload{
        @JsonProperty("action")
        private String action;
        @JsonProperty("issue")
        private IssueResponse issue;
    }
}
