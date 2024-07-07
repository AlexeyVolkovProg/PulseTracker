package org.example.configuration;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.client.github.GithubReposService;
import org.example.client.github.impl.GithubClientImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfiguration {


    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }


    /**
     * Абстрактный метод для конфигурации клиентов, которые посылают запросы на внешние API
     */
    public <T> T buildClient(String baseURL, Class<T> clientClass, String mediaType) {

        //Конфигурация для сериализация и десериализации
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(clientDefaultCodecsConfigurer -> {
                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper()));
                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper()));
                })
                .build();

        WebClient webClient = WebClient.builder()
                .baseUrl(baseURL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, mediaType).codecs(codecs -> codecs
                .defaultCodecs()
                .maxInMemorySize(500 * 1024))
                .exchangeStrategies(strategies)
                .build();

        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build();
        return httpServiceProxyFactory.createClient(clientClass);
    }


    public GithubReposService buildGithubReposService() {
        return buildClient(GithubConfiguration.BASE_API_URL, GithubReposService.class, GithubConfiguration.MEDIA_TYPE);
    }

    @Bean
    public GithubClientImpl buildGithubClient(){
        return new GithubClientImpl(buildGithubReposService());
    }

}
