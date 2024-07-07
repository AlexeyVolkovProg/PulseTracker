package org.example.client.github;

import org.example.client.github.dto.RepoEventResponse;

import java.util.List;

public interface GithubClient {

    List<RepoEventResponse> fetchEvents(String ownerName, String repoName);

}
