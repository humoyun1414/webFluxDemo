package uz.humoyun.webfluxdemo.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.*;
import org.testcontainers.utility.DockerImageName;


@SpringBootTest
@ActiveProfiles("junit")
@ExtendWith(SpringExtension.class)
public abstract class AbstractSpringBootTest {
    protected static final MockServerClient MOCK_SERVER_CLIENT;
    private static final MockServerContainer MOCK_SERVER_CONTAINER;
    private static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER;

    static {
        POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:14.5")
                .withInitScript("create-book-schema.sql");
        POSTGRE_SQL_CONTAINER.start();

        MOCK_SERVER_CONTAINER = new MockServerContainer(
                DockerImageName.parse("jamesdbloom/mockserver:mockserver-5.13.2")
        );
        MOCK_SERVER_CONTAINER.start();


        MOCK_SERVER_CLIENT = new MockServerClient(
                MOCK_SERVER_CONTAINER.getHost(), MOCK_SERVER_CONTAINER.getServerPort()
        );

    }

    @DynamicPropertySource
    static void initialize(final DynamicPropertyRegistry registry) {
        registry.add("spring.r2dbc.url", AbstractSpringBootTest::postgresR2dbcUrl);
        registry.add("spring.r2dbc.username", POSTGRE_SQL_CONTAINER::getUsername);
        registry.add("spring.r2dbc.password", POSTGRE_SQL_CONTAINER::getPassword);

        registry.add("spring.liquibase.url", AbstractSpringBootTest::postgresUrl);
        registry.add("spring.liquibase.user", POSTGRE_SQL_CONTAINER::getUsername);
        registry.add("spring.liquibase.password", POSTGRE_SQL_CONTAINER::getPassword);


        registry.add("eureka.client.service-url.defaultZone", MOCK_SERVER_CONTAINER::getEndpoint);
    }

    private static String postgresUrl() {
        return String.format("%s", POSTGRE_SQL_CONTAINER.getJdbcUrl());
    }

    private static String postgresR2dbcUrl() {
        return String.format(
                "r2dbc:postgresql://%s:%s/%s",
                POSTGRE_SQL_CONTAINER.getHost(),
                POSTGRE_SQL_CONTAINER.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT),
                POSTGRE_SQL_CONTAINER.getDatabaseName());
    }


}
