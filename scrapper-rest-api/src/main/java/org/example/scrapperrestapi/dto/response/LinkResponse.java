package org.example.scrapperrestapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkResponse {

    @JsonProperty("id")
    private int id;

    @JsonProperty("uri")
    private String uri;



}
