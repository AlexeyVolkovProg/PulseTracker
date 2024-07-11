package org.example.service;

import org.example.client.github.dto.RepoEventResponse;
import org.example.client.github.dto.events.CreateEventResponse;
import org.example.client.github.impl.GithubClientImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GithubRepoHandler {

    private GithubClientImpl githubClient;

    public GithubRepoHandler(GithubClientImpl githubClient) {
        this.githubClient = githubClient;
    }


    public List<String> handleEventsRepoInfo(){
        return githubClient.fetchEvents("AlexeyVolkovProg", "PulseTracker").stream().filter(Objects::nonNull).map(this::getEventDescription)
                .toList();
    }


    private String getEventDescription(RepoEventResponse repoEventResponse){
        return String.format("Event Type: %s. Created Time: %s", repoEventResponse.getType(), repoEventResponse.getCreateDate());
    }





}
