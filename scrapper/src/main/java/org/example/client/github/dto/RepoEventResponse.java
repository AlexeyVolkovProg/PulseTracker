package org.example.client.github.dto;

import com.fasterxml.jackson.annotation.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.client.github.dto.events.*;

import java.io.Serializable;



@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
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
@NoArgsConstructor
public abstract class RepoEventResponse implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("created_at")
    private String createDate;

    @JsonCreator
    public RepoEventResponse(@JsonProperty("id") String id,
                             @JsonProperty("type") String type,
                             @JsonProperty("created_at") String createDate) {
        this.id = id;
        this.type = type;
        this.createDate = createDate;
    }
    @Override
    public String toString() {
        return "RepoEventResponse{" +
                "id='" + id + '\'' +
                ", type='" + this.getType() + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
