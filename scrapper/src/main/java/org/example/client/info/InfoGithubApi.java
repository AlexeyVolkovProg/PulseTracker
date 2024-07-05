package org.example.client.info;

import lombok.Getter;


// todo пока что заглушки, сделаю тестирование при помощи wiremock
@Getter
public enum InfoGithubApi {
    GITHUB_API_COMMITS("GITHUB_REPOS_COMMITS", "https://api.github.com/repos/AlexeyVolkovProg/PulseTracker/commits"),
    GITHUB_API_ISSUES("GITHUB_REPOS_ISSUES", "https://api.github.com/repos/JetBrains/kotlin/issues"),
    GITHUB_API_PULL_REQUEST("GITHUB_REPOS_PULL_REQUESTS", "https://api.github.com/repos/JetBrains/kotlin/pulls");

    private final String name;

    private final String url;

    InfoGithubApi(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
