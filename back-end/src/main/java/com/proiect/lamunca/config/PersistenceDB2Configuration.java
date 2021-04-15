package com.proiect.lamunca.config;

import com.proiect.lamunca.entity.db1.User;
import com.proiect.lamunca.entity.db2.Notification;
import com.zaxxer.hikari.HikariDataSource;
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
        basePackages = "com.proiect.lamunca.repository.db2",
        entityManagerFactoryRef = "db2EntityManagerFactory",
        transactionManagerRef = "db2TransactionManager")
public class PersistenceDB2Configuration {


    @Bean
    @ConfigurationProperties(prefix = "app.datasource.database2")
    public DataSourceProperties cdataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix="app.datasource.database2.configuration")
    public DataSource cdataSource() {
        return cdataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
        // return DataSourceBuilder.create().build();
    }

    @Bean(name = "db2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db2EntityManager(EntityManagerFactoryBuilder builder) {

        return builder.dataSource(cdataSource()).packages(Notification.class).build();
    }

    @Bean
    public PlatformTransactionManager db2TransactionManager(@Qualifier("db2EntityManagerFactory") LocalContainerEntityManagerFactoryBean db2EntityManagerFactory) {
        //transactionManager.setEntityManagerFactory(entityManager().getObject());
        return new JpaTransactionManager(db2EntityManagerFactory.getObject());
    }


}

