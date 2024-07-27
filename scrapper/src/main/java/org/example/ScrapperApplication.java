package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import java.io.IOException;

@SpringBootApplication
@ConfigurationPropertiesScan("org.example.configuration")
public class ScrapperApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ScrapperApplication.class, args);
    }
}
