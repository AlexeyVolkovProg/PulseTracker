package org.example.client.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class PullRequestDTO {

    @JsonProperty("title")
    private String title;

    @JsonProperty("user")
    private IssueReposDTO.User user;

    @Getter
    public static class User{

        @JsonProperty("login")
        private String name;
    }

    @JsonProperty("created_at")
    private String dateCreate;

    @JsonProperty("updated_at")
    private String dateUpdate;

}
