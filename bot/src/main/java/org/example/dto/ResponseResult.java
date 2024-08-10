package org.example.dto;


import lombok.Getter;
import lombok.Setter;
import org.example.scrapperrestapi.dto.response.LinkResponse;

import java.util.List;

@Getter
@Setter
public class ResponseResult {

    private final boolean success;

    private final String error;

    private final String description;

    private final List<LinkResponse> linksList;


    public ResponseResult(boolean success, String error, String description, List<LinkResponse> linksList){
        this.success = success;
        this.error = error;
        this.description = description;
        this.linksList = linksList;
    }

    public ResponseResult(boolean success, String error, String description) {
        this(success, error, description, null);
    }


}
