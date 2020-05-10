package dev.akozel.cleaningtime.repository.postgres.test.providers;

import dev.akozel.cleaningtime.repository.postgres.test.rules.PostgresContainerRule;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

public final class DslContextProvider {

    public static DSLContext getContext() {
        PostgresContainerRule container = PostgresContainerRule.getInstance();
        return DSL.using(container.getJdbcUrl(), container.getUsername(), container.getPassword());
    }

}
