package org.example.configuration;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@PropertySource("classpath:application.yml")
@EnableScheduling
@Getter
public class ApplicationConfiguration {

    private final Integer memorySize;

    public ApplicationConfiguration(@Value("${app.memory-size}") Integer memorySize) {
        this.memorySize = memorySize;
    }
}
