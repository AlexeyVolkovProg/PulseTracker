package org.example.client.stackoverflow.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResponse {
    @JsonProperty("user_id")
    private String user_id;

    @JsonProperty("accept_rate")
    private String accept_rate;

    @JsonProperty("display_name")
    private String name;

}
