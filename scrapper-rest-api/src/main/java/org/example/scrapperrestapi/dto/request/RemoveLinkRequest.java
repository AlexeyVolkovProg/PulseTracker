package org.example.scrapperrestapi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveLinkRequest {

    @JsonProperty("link")
    private String link;
}
