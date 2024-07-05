package org.example.service;

import org.example.client.github.GithubClientImpl;
import org.example.client.github.dto.CommitResponseDTO;
import org.example.client.github.dto.IssueReposDTO;
import org.example.client.github.dto.PullRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GithubService {

    private final GithubClientImpl githubClient;


    public GithubService(GithubClientImpl githubClient) {
        this.githubClient = githubClient;
    }

    public List<CommitResponseDTO> getCommits(){
        return githubClient.commitResponseList();
    }

    public List<IssueReposDTO> getIssues(){
        return githubClient.issuesResponseList();
    }

    public List<PullRequestDTO> getPullRequests(){
        return githubClient.pullRequestsList();
    }


}
