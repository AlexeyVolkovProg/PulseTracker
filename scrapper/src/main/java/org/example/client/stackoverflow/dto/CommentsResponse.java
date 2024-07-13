package org.example.client.stackoverflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommentsResponse {

    @JsonProperty("items")
    List<CommentResponse> commentResponseList;

    public static class CommentResponse{
        @JsonProperty("owner")
        private UserInfoResponse userInfoResponse;

        @JsonProperty("score")
        private String score;

        @JsonProperty("creation_date")
        private String creation_date;

        @JsonProperty("comment_id")
        private String comment_id;
    }
}
