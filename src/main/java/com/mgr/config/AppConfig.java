package com.mgr.config;

import oracle.jdbc.pool.OracleDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Konfiguracja Spring - czyli calej aplikacji, zaczytywany jest tutaj plik
 * app.properties
 *
 * @author michal
 */
@Configuration
@PropertySource({"file:${cfg.path:config/}app.properties"})
public class AppConfig {

    private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);
    @Value("${db.serverName}")
    private String serverName;
    @Value("${db.portNumber}")
    private int portNumber;
    @Value("${db.name}")
    private String name;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String password;
    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${hibernate.show_sql}")
    private String showSql;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddl;
    @Value("${esper.workers}")
    Integer workers;

    /**
     * Klasa odpowiadajaca za wstrzykiwanie propertisow
     *
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * Fabryka sesji Hibernate
     *
     * @return
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory
                .setPackagesToScan(new String[]{"com.mgr.common.data"});
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    /**
     * Polaczenie do bazy danych
     *
     * @return
     */
    @Bean
    public DataSource dataSource() {

        OracleDataSource ods;
        try {
            ods = new OracleDataSource();
            ods.setDriverType("thin");
            ods.setServerName(serverName);
            ods.setPortNumber(portNumber);
            ods.setDatabaseName(name);
            ods.setUser(user);
            ods.setPassword(password);
            return ods;
        } catch (SQLException e) {
            LOG.error("Blad przy tworzeniu polaczenia z baza");
            LOG.error(e.toString());
            return null;
        }
    }

    /**
     * Manager transakcji
     *
     * @param sessionFactory
     * @return
     */
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(
            SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", hbm2ddl);
                setProperty("hibernate.dialect", dialect);
                setProperty("hibernate.globally_quoted_identifiers", "true");
                setProperty("hibernate.show_sql", showSql);
                setProperty("hibernate.current_session_context_class", "thread");

				/*
                 * setProperty("hibernate.c3p0.min_size", workers.toString());
				 * setProperty("hibernate.c3p0.max_size", "20");
				 * setProperty("hibernate.c3p0.timeout", "3000");
				 * setProperty("hibernate.c3p0.max_statements", "50");
				 * setProperty("hibernate.c3p0.idle_test_period", "300");
				 * setProperty("hibernate.connection.provider_class",
				 * "org.hibernate.connection.C3P0ConnectionProvider");
				 */
            }
        };
    }

}
