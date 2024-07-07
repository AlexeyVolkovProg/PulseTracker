package org.example;


import org.example.service.GithubRepoHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import java.io.IOException;

@EnableConfigurationProperties
@SpringBootApplication
public class ScrapperApplication {

    private static GithubRepoHandler githubRepoHandler;

    public ScrapperApplication(GithubRepoHandler githubRepoHandler) {
        ScrapperApplication.githubRepoHandler = githubRepoHandler;
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ScrapperApplication.class, args);

        githubRepoHandler.handleEventsRepoInfo().stream().forEach(System.out::println);
    }
}
