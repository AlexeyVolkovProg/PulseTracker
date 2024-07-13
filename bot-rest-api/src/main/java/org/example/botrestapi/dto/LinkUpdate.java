package org.example.botrestapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LinkUpdate {

    @JsonProperty("id")
    private int id;

    @JsonProperty("url")
    private String uri;

    @JsonProperty("description")
    private String description;

    @JsonProperty("tgChatIds")
    private List<Long> tgChatIds;


}
