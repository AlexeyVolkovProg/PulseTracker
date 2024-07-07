package org.example.client.github;

import org.example.client.github.dto.RepoEventResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

/**
 * Отвечает за отправку запросов к API Github
 */
public interface GithubReposService {

    @GetExchange("/repos/{owner}/{repos}/events")
    List<RepoEventResponse> getEvents(@PathVariable("owner") String ownerName, @PathVariable("repos") String reposName);
}
