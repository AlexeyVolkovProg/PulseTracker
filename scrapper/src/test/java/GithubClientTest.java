import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.example.ScrapperApplication;
import org.example.client.github.GithubReposService;
import org.example.client.github.dto.RepoEventResponse;
import org.example.client.github.impl.GithubClientImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ScrapperApplication.class)
public class GithubClientTest {

    private WireMockServer wireMockServer;

    private GithubClientImpl githubClient;

    @BeforeEach
    public void setUp() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
        WebClient webClient = WebClient.builder().baseUrl(wireMockServer.baseUrl()).build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build();
        githubClient = new GithubClientImpl(httpServiceProxyFactory.createClient(GithubReposService.class));
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.stop();
    }


    @Test
    public void testGitHubClient() {
        String owner = "AlexeyVolkovProg";
        String repo = "PulseTracker";
        String reposBody = "[{\"id\": \"1234\", \"type\": \"PushEvent\"}]";
        // настраиваем поведение, которое имитирует примерную работу нашего API
        stubFor(get(urlPathEqualTo("/repos/" + owner + "/" + repo + "/events"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(reposBody)));
        List<RepoEventResponse> events = githubClient.fetchEvents(owner, repo);
        assertEquals(1, events.size());
        assertEquals("1234", events.get(0).getId());
        assertEquals("PushEvent", events.get(0).getType());
    }


}
