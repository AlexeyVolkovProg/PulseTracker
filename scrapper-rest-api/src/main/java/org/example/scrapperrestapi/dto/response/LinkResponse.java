package org.example.scrapperrestapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LinkResponse {

    @JsonProperty("id")
    private long id;

    @JsonProperty("uri")
    private String uri;

}
