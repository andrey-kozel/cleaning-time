package dev.akozel.cleaningtime.repository.postgres.test.rules;

import dev.akozel.cleaningtime.repository.postgres.test.annotations.SqlData;
import dev.akozel.cleaningtime.repository.postgres.test.providers.DslContextProvider;
import dev.akozel.cleaningtime.repository.postgres.test.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.jooq.DSLContext;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.Optional;

class SqlExecutorRule implements TestRule {

    private static final String DEFAULT_CLEAN_UP_SCRIPT = "clean_up.sql";

    @Override
    public Statement apply(Statement base, Description description) {
        String initialScript = Optional.ofNullable(description.getAnnotation(SqlData.class))
                .map(SqlData::initialScript)
                .orElse(null);

        return createStatement(base, initialScript);
    }

    private Statement createStatement(Statement base, String initialScript) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                DSLContext context = DslContextProvider.getContext();
                try {
                    if (StringUtils.isNotEmpty(initialScript)) {
                        SqlUtils.execute(context, initialScript);
                    }
                    base.evaluate();
                } finally {
                    SqlUtils.execute(context, DEFAULT_CLEAN_UP_SCRIPT);
                }
            }
        };
    }
}
