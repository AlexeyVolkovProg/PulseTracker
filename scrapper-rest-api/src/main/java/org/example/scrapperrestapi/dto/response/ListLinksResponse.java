package org.example.scrapperrestapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.util.List;

@Setter
@Getter
public class ListLinksResponse {

    @JsonProperty("links")
    private List<LinkResponse> linkResponsesList;

    @JsonProperty("size")
    private int size;
}
