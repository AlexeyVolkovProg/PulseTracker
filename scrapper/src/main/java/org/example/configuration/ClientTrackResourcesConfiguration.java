package org.example.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.client.github.GithubReposService;
import org.example.client.github.impl.GithubClientImpl;
import org.example.client.stackoverflow.StackOverFlowQuestionsService;
import org.example.client.stackoverflow.impl.StackOverFlowClientImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;


/**
 * Конфигурация для клиентов взаимодействующих с внешними API информационных ресурсов,
 * благодаря которым мы будем отслеживать изменения ресурсов, на обновления которых подписан пользователь
 */
@Configuration
public class ClientTrackResourcesConfiguration {


    private final ObjectMapper objectMapper;

    private final GithubConfiguration githubConfiguration;

    private final StackOverFlowConfiguration stackOverFlowConfiguration;

    private final Integer MEMORY_SIZE;

    public ClientTrackResourcesConfiguration(ObjectMapper objectMapper, GithubConfiguration githubConfiguration, StackOverFlowConfiguration stackOverFlowConfiguration, ApplicationConfiguration applicationConfiguration1, ApplicationConfiguration applicationConfiguration) {
        this.objectMapper = objectMapper;
        this.githubConfiguration = githubConfiguration;
        this.stackOverFlowConfiguration = stackOverFlowConfiguration;
        MEMORY_SIZE = applicationConfiguration.getMemorySize();
    }

    /**
     * Абстрактный метод для конфигурации клиентов, которые посылают запросы на внешние API
     */
    public <T> T buildClient(String baseURL, Class<T> clientClass, String mediaType) {

        //Конфигурация для сериализация и десериализации
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(clientDefaultCodecsConfigurer -> {
                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper));
                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
                })
                .build();
        WebClient webClient = WebClient.builder()
                .baseUrl(baseURL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, mediaType)
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(MEMORY_SIZE))
                .exchangeStrategies(strategies)
                .build();

        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build();
        return httpServiceProxyFactory.createClient(clientClass);
    }


    public GithubReposService buildGithubReposService() {
        return buildClient(githubConfiguration.getBaseApiUrl(), GithubReposService.class, githubConfiguration.getMediaType());
    }

    public StackOverFlowQuestionsService buildStackOverFlowQuestionsService() {
        return buildClient(stackOverFlowConfiguration.getBaseApiUrl(), StackOverFlowQuestionsService.class, stackOverFlowConfiguration.getMediaType());
    }


    @Bean
    public GithubClientImpl buildGithubClient() {
        return new GithubClientImpl(buildGithubReposService());
    }

    @Bean
    public StackOverFlowClientImpl buildStackOverFlowClient() {
        return new StackOverFlowClientImpl(buildStackOverFlowQuestionsService());
    }
}
