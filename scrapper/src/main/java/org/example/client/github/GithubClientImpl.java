package org.example.client.github;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.client.github.dto.CommitResponseDTO;
import org.example.client.github.dto.IssueReposDTO;
import org.example.client.github.dto.PullRequestDTO;
import org.example.client.info.InfoGithubApi;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class GithubClientImpl{

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    String commitApiUrl = InfoGithubApi.GITHUB_API_COMMITS.getUrl();

    String issuesApiUrl = InfoGithubApi.GITHUB_API_ISSUES.getUrl();

    String pullRequestsApiUrl = InfoGithubApi.GITHUB_API_PULL_REQUEST.getUrl();


    public GithubClientImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }


    /**
     * Запрос на получение коммитов, сделанных в репозитории
     */
    public List<CommitResponseDTO> commitResponseList(){
        return getResponse(InfoGithubApi.GITHUB_API_COMMITS.getUrl(), new TypeReference<List<CommitResponseDTO>>() {});
    }

    /**
     * Запрос на получение проблем, рассматриваемых в репозитории
     */

    public List<IssueReposDTO> issuesResponseList(){
        return getResponse(InfoGithubApi.GITHUB_API_ISSUES.getUrl(), new TypeReference<List<IssueReposDTO>>() {});
    }

    /**
     * Запрос на получение pull requests в репозитории
     */
    public List<PullRequestDTO> pullRequestsList(){
        return getResponse(InfoGithubApi.GITHUB_API_PULL_REQUEST.getUrl(), new TypeReference<List<PullRequestDTO>>() {});
    }


    /**
     * Метод, позволяющий сделать запрос к api github
     */
    //todo подправить работу с исключением
    public  <T> List<T> getResponse(String url, TypeReference <List<T>> typeReference){
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        try {
            return objectMapper.readValue(response.getBody(), typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
