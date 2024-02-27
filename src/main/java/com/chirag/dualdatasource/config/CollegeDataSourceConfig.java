package com.chirag.dualdatasource.config;


import javax.sql.DataSource;

import com.chirag.dualdatasource.college.entity.College;
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
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.chirag.dualdatasource.college.repository",
        entityManagerFactoryRef = "collegeEntityManagerFactory",
        transactionManagerRef = "collegeTransactionManager")
public class CollegeDataSourceConfig{
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.college")
    public DataSourceProperties collegeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.college.configuration")
    public DataSource collegeDataSource() {
        return collegeDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "collegeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean collegeEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(collegeDataSource())
                .packages( College.class)
                .build();
    }

    @Primary
    @Bean(name = "collegeTransactionManager")
    public PlatformTransactionManager collegeTransactionManager(
            final @Qualifier("collegeEntityManagerFactory") LocalContainerEntityManagerFactoryBean collegeEntityManagerFactory) {
        return new JpaTransactionManager(collegeEntityManagerFactory.getObject());
    }

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder(){
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(),null);
    }
}