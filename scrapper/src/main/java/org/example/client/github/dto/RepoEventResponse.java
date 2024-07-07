package org.example.client.github.dto;

import com.fasterxml.jackson.annotation.*;

import lombok.Getter;
import lombok.Setter;
import org.example.client.github.dto.events.*;

import java.io.Serializable;



@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        defaultImpl = UnknownEvent.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateEventResponse.class, name = "CreateEvent"),
        @JsonSubTypes.Type(value = IssueEventResponse.class, name = "IssuesEvent"),
        @JsonSubTypes.Type(value = PushEventResponse.class, name = "PushEvent"),
        @JsonSubTypes.Type(value = PullEventResponse.class, name = "PullRequestEvent"),
})
@Getter
@Setter
public abstract class RepoEventResponse implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonTypeId
    @JsonProperty("type")
    private String type;

    @JsonProperty("created_at")
    private String createDate;

}
