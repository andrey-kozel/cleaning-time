package dev.akozel.cleaningtime.rest.database.postgres;

import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * PersistenceContext. Database connection configuration
 * <p>
 * Date: 23/02/2020
 *
 * @author Andrey Kozel
 */
@Configuration
@EnableTransactionManagement
public class PersistenceContext {

    @Value("${cleaning.time.database.url}")
    private String databaseUrl;
    @Value("${cleaning.time.database.username}")
    private String databaseUsername;
    @Value("${cleaning.time.database.password}")
    private String databasePassword;
    @Value("${cleaning.time.database.dialect}")
    private String databaseDialect;

    @Bean
    public DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(databaseUrl);
        dataSource.setUser(databaseUsername);
        dataSource.setPassword(databasePassword);
        return dataSource;
    }

    @Bean
    public TransactionAwareDataSourceProxy transactionAwareDataSource(DataSource dataSource) {
        return new TransactionAwareDataSourceProxy(dataSource);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public DataSourceConnectionProvider connectionProvider(TransactionAwareDataSourceProxy transactionAwareProxy) {
        return new DataSourceConnectionProvider(transactionAwareProxy);
    }

    @Bean
    public ExceptionTranslator exceptionTransformer() {
        return new ExceptionTranslator();
    }

    @Bean
    public DefaultDSLContext dsl(DefaultConfiguration defaultConfiguration) {
        return new DefaultDSLContext(defaultConfiguration);
    }

    @Bean
    @Primary
    public DefaultConfiguration configuration(ExceptionTranslator exceptionTranslator,
                                              DataSourceConnectionProvider dataSourceConnectionProvider) {
        DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
        jooqConfiguration.set(dataSourceConnectionProvider);
        jooqConfiguration.set(new DefaultExecuteListenerProvider(exceptionTranslator));
        SQLDialect dialect = SQLDialect.valueOf(databaseDialect);
        jooqConfiguration.setSQLDialect(dialect);
        return jooqConfiguration;
    }

}
