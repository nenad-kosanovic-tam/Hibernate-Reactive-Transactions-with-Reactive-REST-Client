/*
 * Copyright (C) Tamedia AG 2022
 */

package com.example.commons;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.util.Objects.isNull;

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractTest implements TestPropertyProvider {

    private static final String MARIA_DB_DOCKER_IMAGE = "mariadb:10.4.8";
    private static final String DATABASE_BANE = "THING";
    static MariaDBContainer MARIA_DB_CONTAINER;

    protected WireMockServer wireMockServer = new WireMockServer(wireMockConfig().dynamicPort());

    @Override
    public Map<String, String> getProperties() {
        if (isNull(MARIA_DB_CONTAINER)) {
            MARIA_DB_CONTAINER = new MariaDBContainer(DockerImageName.parse(MARIA_DB_DOCKER_IMAGE));
            MARIA_DB_CONTAINER.withDatabaseName(DATABASE_BANE);
            MARIA_DB_CONTAINER.withReuse(true);
            MARIA_DB_CONTAINER.start();
        }

        wireMockServer.start();

        return Map.of(
                "db.host", MARIA_DB_CONTAINER.getContainerIpAddress() + ":" + MARIA_DB_CONTAINER.getMappedPort(3306).toString(),
                "db.user", MARIA_DB_CONTAINER.getUsername(),
                "db.pass", MARIA_DB_CONTAINER.getPassword(),
                "db.database", DATABASE_BANE,
                "thing.host", wireMockServer.baseUrl()
        );
    }
}
