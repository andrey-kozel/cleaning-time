package dev.akozel.cleaningtime.rest.common.postgres;

import org.jooq.ExecuteContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultExecuteListener;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;

/**
 * ExceptionTranslator.
 * <p>
 * Date: 23/02/2020
 *
 * @author Andrey Kozel
 */
public class ExceptionTranslator extends DefaultExecuteListener {

    public void exception(ExecuteContext context) {
        SQLDialect dialect = context.configuration().dialect();
        SQLExceptionTranslator translator
                = new SQLErrorCodeSQLExceptionTranslator(dialect.name());
        context.exception(translator
                .translate("Access database using Jooq", context.sql(), context.sqlException()));
    }

}
