package org.example.scrapperrestapi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddLinkRequest {


    @JsonProperty("link")
    @NotNull
    private String link;



}
