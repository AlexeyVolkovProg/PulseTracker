package org.example.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@EnableScheduling
public class ApplicationConfiguration {


}
