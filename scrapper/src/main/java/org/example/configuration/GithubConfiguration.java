package org.example.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;



@Configuration
@PropertySource("classpath:application.yml")
@Getter
@Setter
public class GithubConfiguration {

    private final String baseApiUrl;
    private final String mediaType;

    public GithubConfiguration(@Value("${github.base-api-url}") String baseApiUrl,
                               @Value("${github.media-type}") String mediaType) {
        this.baseApiUrl = baseApiUrl;
        this.mediaType = mediaType;
    }
}
