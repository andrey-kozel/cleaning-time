package dev.akozel.cleaningtime.repository.postgres.test.rules;

import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class PostgresContainerExecutorRule implements TestRule {

    @Override
    public Statement apply(Statement base, Description description) {
        return RuleChain.outerRule(PostgresContainerRule.getInstance())
                .around(new SqlExecutorRule())
                .apply(base, description);
    }
}
