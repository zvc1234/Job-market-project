package com.proiect.lamunca.config;


import com.proiect.lamunca.entity.db1.*;
import com.proiect.lamunca.entity.db2.Notification;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
//@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = "com.proiect.lamunca.repository.db1",
        entityManagerFactoryRef = "db1EntityManagerFactory",
        transactionManagerRef = "db1TransactionManager")
public class PersistenceDB1Configuration {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "app.datasource.database1")
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    @ConfigurationProperties(prefix="app.datasource.database1.configuration")
    public DataSource dataSource() {
        return dataSourceProperties().initializeDataSourceBuilder().type(BasicDataSource.class).build();
       // return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "db1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(EntityManagerFactoryBuilder builder) {

        return builder.dataSource(dataSource()).packages(User.class, Category.class, Review.class, UserCategory.class, UserJob.class).build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager db1TransactionManager(final @Qualifier("db1EntityManagerFactory") LocalContainerEntityManagerFactoryBean db1EntityManagerFactory) {
        //transactionManager.setEntityManagerFactory(entityManager().getObject());
        return new JpaTransactionManager(db1EntityManagerFactory.getObject());
    }

}