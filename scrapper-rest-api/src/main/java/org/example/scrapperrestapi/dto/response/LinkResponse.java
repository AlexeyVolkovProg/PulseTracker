package org.example.scrapperrestapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkResponse {

    @JsonProperty("id")
    private long id;

    @JsonProperty("uri")
    private String uri;

}
