package org.example.client.github.impl;

import org.example.client.github.GithubReposService;
import org.example.client.github.dto.RepoEventResponse;
import org.example.client.github.GithubClient;

import java.util.List;

public class GithubClientImpl implements GithubClient {
    private final GithubReposService githubReposService;

    public GithubClientImpl(GithubReposService githubReposService) {
        this.githubReposService = githubReposService;
    }

    @Override
    public List<RepoEventResponse> fetchEvents(String ownerName, String repoName) {
        return githubReposService.getEvents(ownerName, repoName);
    }
}
