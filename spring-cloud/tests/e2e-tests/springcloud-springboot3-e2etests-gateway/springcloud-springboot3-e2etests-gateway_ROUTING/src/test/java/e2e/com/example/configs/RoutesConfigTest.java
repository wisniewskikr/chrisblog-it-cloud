package e2e.com.example.configs;

import com.example.Application;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class RoutesConfigTest {

    @Container
    static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:5.7")
            .withDatabaseName("database")
            .withUsername("admin")
            .withPassword("admin123");

    @Container
    static GenericContainer<?> secondServiceContainer = new GenericContainer<>("wisniewskikr/springcloud-springboot3-e2etests-gateway_second:0.0.1")
            .withExposedPorts(8081);

    @Container
    static GenericContainer<?> firstServiceContainer = new GenericContainer<>("wisniewskikr/springcloud-springboot3-e2etests-gateway_first:0.0.1")
            .withExposedPorts(8080);

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    static {
        mysqlContainer.start();
        secondServiceContainer.start();
        firstServiceContainer.start();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);

        String baseurlSecond = "http://" + secondServiceContainer.getHost() + ":" + secondServiceContainer.getMappedPort(8081);
        registry.add("baseurl.second", () -> baseurlSecond);

        String firstServiceUrl = "http://" + firstServiceContainer.getHost() + ":" + firstServiceContainer.getMappedPort(8080);
        registry.add("first.service.url", () -> firstServiceUrl);
    }

    @Test
    void firstServiceRoute_public() {

        given()
            .when()
                .get("/public")
            .then()
                .statusCode(200)
                .body("id", equalTo(String.valueOf(1)))
                .body("text", equalTo("Hello World, Public!"))
                .body("portFirst", equalTo("8080"))
                .body("portSecond", equalTo("8081"));

    }
}