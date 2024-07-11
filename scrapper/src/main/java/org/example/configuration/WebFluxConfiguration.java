package org.example.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebFluxConfiguration implements WebFluxConfigurer {
    private final int MEMORY_SIZE = 500 * 1024;

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.defaultCodecs().maxInMemorySize(MEMORY_SIZE);
    }
}

