package org.example.botrestapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {

    @JsonProperty ("description")
    private String description;

    @JsonProperty ("code")
    private String code;

    @JsonProperty ("exceptionName")
    private String exceptionName;

    @JsonProperty ("exceptionMessage")
    private String exceptionMessage;

    @JsonProperty ("stacktrace")
    private List<String> stacktrace;


}
