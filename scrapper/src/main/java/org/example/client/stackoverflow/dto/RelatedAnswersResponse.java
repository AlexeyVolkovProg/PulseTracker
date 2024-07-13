package org.example.client.stackoverflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RelatedAnswersResponse {
    @JsonProperty("items")
    private List<RelatedQuestionsEventResponse> relatedQuestions;

    public static class RelatedQuestionsEventResponse {
        @JsonProperty("owner")
        UserInfoResponse owner;
        @JsonProperty("question_id")
        long questionId;
        @JsonProperty("title")
        String title;
        @JsonProperty("creation_date")
        String creationDate;
    }
}
