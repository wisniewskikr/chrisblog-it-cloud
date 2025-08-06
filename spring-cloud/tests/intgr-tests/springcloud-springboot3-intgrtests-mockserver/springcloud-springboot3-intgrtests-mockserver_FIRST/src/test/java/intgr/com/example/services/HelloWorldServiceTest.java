package intgr.com.example.services;

import com.example.clients.SecondClient;
import com.example.dtos.HelloWorldFirstDto;
import com.example.services.HelloWorldService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.http.HttpMethod;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

public class HelloWorldServiceTest {

    private MockRestServiceServer mockServer;
    private HelloWorldService helloWorldService;

    private final String BASE_URL = "http://localhost:8082";

    @BeforeEach
    void setUp() {
        // Create RestClient.Builder first
        RestClient.Builder restClientBuilder = RestClient.builder().baseUrl(BASE_URL);

        // Bind MockRestServiceServer BEFORE building RestClient
        mockServer = MockRestServiceServer.bindTo(restClientBuilder).build();

        // Now build RestClient
        RestClient restClient = restClientBuilder.build();

        // Create the SecondClient proxy
        SecondClient secondClient = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build()
                .createClient(SecondClient.class);

        // Mock Environment to return a fake port
        Environment environment = new StandardEnvironment() {
            @Override
            public String getProperty(String key) {
                if ("local.server.port".equals(key)) {
                    return "8081";
                }
                return super.getProperty(key);
            }
        };

        helloWorldService = new HelloWorldService(secondClient, environment);
    }

    @Test
    void testGetPublicMessage() {
        // Prepare mock response
        String responseBody = """
            {
                "text": "Hello World, Public!",
                "portSecond": "8082"
            }
        """;

        mockServer.expect(requestTo(BASE_URL + "/message/1"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

        HelloWorldFirstDto result = helloWorldService.getPublicMessage();

        assertThat(result.text()).isEqualTo("Hello World, Public!");
        assertThat(result.portFirst()).isEqualTo("8081");
        assertThat(result.portSecond()).isEqualTo("8082");

        mockServer.verify();
    }

    @Test
    void testGetSecuredMessage() {
        // Prepare mock response
        String responseBody = """
            {
                "text": "Hello World, Secured!",
                "portSecond": "8082"
            }
        """;

        mockServer.expect(requestTo(BASE_URL + "/message/2"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

        HelloWorldFirstDto result = helloWorldService.getSecuredMessage();

        assertThat(result.text()).isEqualTo("Hello World, Secured!");
        assertThat(result.portFirst()).isEqualTo("8081");
        assertThat(result.portSecond()).isEqualTo("8082");

        mockServer.verify();
    }

}

