package e2e.com.example.configs;

import com.example.Application;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class RoutesConfigTest {

    static Network network = Network.newNetwork();

    @Container
    static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:5.7")
            .withDatabaseName("database")
            .withUsername("admin")
            .withPassword("admin123")
            .withNetwork(network)
            .withNetworkAliases("mysql");

    @Container
    static GenericContainer<?> secondContainer = new GenericContainer<>(DockerImageName.parse("wisniewskikr/springcloud-springboot3-e2etests-gateway_second:0.0.1"))
            .withExposedPorts(8081)
            .dependsOn(mysqlContainer)
            .withNetwork(network)
            .withNetworkAliases("second")
            .withEnv("SPRING_DATASOURCE_URL", "jdbc:mysql://mysql:3306/database")
            .waitingFor(Wait.forHttp("/message/1").forStatusCode(200));

    @Container
    static GenericContainer<?> firstContainer = new GenericContainer<>(DockerImageName.parse("wisniewskikr/springcloud-springboot3-e2etests-gateway_first:0.0.1"))
            .withExposedPorts(8080)
            .dependsOn(secondContainer)
            .withNetwork(network)
            .withNetworkAliases("first")
            .withEnv("baseurl.second", "http://second:8081")
            .waitingFor(Wait.forHttp("/public").forStatusCode(200));

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @BeforeAll
    static void setUp() {
        mysqlContainer.start();
        secondContainer.start();
        firstContainer.start();
    }

    @AfterAll
    static void tearDown() {
        mysqlContainer.stop();
        secondContainer.stop();
        firstContainer.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        String firstServiceUrl = "http://" + firstContainer.getHost() + ":" + firstContainer.getMappedPort(8080);
        registry.add("first.service.url", () -> firstServiceUrl);
    }

    @Test
    void firstServiceRoute_public() {

        given()
            .when()
                .get("/public")
            .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("text", equalTo("Hello World, Public!"))
                .body("portFirst", equalTo("8080"))
                .body("portSecond", equalTo("8081"));

    }

    @Test
    void firstServiceRoute_secured() {

        given()
                .when()
                .get("/secured")
                .then()
                .statusCode(200)
                .body("id", equalTo(2))
                .body("text", equalTo("Hello World, Secured!"))
                .body("portFirst", equalTo("8080"))
                .body("portSecond", equalTo("8081"));

    }

}