package org.example.client.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class CommitResponseDTO{

    @JsonProperty("sha")
    private String sha;

    @JsonProperty("commit")
    private Commit commit;

    @Getter
    @Setter
    public static class Commit{
        @JsonProperty("author")
        private Author author;
    }

    @Getter
    @Setter
    public static class Author {
        @JsonProperty("name")
        private String name;

        @JsonProperty("date")
        private String date;
    }


}
