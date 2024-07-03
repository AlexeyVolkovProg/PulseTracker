package org.example.configuration;


import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(
        @NotEmpty
        String telegramToken,

        @NotEmpty
        String botName
) {

}


