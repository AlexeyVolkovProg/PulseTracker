package org.example.client.stackoverflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnswersResponse {

    @JsonProperty("items")
    List<AnswerResponse> answerResponseList;

    public static class AnswerResponse{
        @JsonProperty("owner")
        private UserInfoResponse userInfoResponse;

        @JsonProperty("score")
        private String score;

        @JsonProperty("last_activity_date")
        private String last_activity_date;

        @JsonProperty("creation_date")
        private String creation_date;

        @JsonProperty("answer_id")
        private String answer_id;
    }

    @Override
    public String toString() {
        return "AnswersResponse{" +
                "answerResponseList=" + answerResponseList +
                '}';
    }
}
