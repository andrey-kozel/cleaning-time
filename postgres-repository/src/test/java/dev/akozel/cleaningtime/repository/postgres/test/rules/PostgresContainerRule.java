package dev.akozel.cleaningtime.repository.postgres.test.rules;

import org.flywaydb.core.Flyway;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * PostgresTestContainer. Test container for postgres. Overrides postgres version and applies flyway migration before run
 * <p>
 * Date: 09/05/2020
 *
 * @author Andrey Kozel
 */
public class PostgresContainerRule extends PostgreSQLContainer<PostgresContainerRule> {
    private static final String POSTGRES_iMAGE = System.getenv("DOCKER_POSTGRES_VERSION");

    private static PostgresContainerRule container;

    public PostgresContainerRule() {
        super(POSTGRES_iMAGE);
    }

    public static PostgresContainerRule getInstance() {
        if (container == null) {
            container = new PostgresContainerRule()
                    .withDatabaseName("test")
                    .withUsername("test")
                    .withPassword("test");
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        applyMigrations();
    }

    private void applyMigrations() {
        Flyway.configure()
                .dataSource(container.getJdbcUrl(), container.getUsername(), container.getPassword())
                .load()
                .migrate();
    }

    @Override
    public void stop() {

    }
}
