package org.example;


import org.example.client.github.GithubClientImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import java.io.IOException;

@EnableConfigurationProperties
@SpringBootApplication
public class ScrapperApplication {

    private static GithubClientImpl githubClientImpl;

    public ScrapperApplication(GithubClientImpl githubClientImpl) {
        this.githubClientImpl = githubClientImpl;
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ScrapperApplication.class, args);
        githubClientImpl.commitResponseList();
    }
}
