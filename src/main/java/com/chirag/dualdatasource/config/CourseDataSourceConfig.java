package com.chirag.dualdatasource.config;


import javax.sql.DataSource;

import com.chirag.dualdatasource.course.entity.Course;
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
@EnableJpaRepositories(basePackages = "com.chirag.dualdatasource.course.repository",
        entityManagerFactoryRef = "courseEntityManagerFactory",
        transactionManagerRef = "courseTransactionManager")
public class CourseDataSourceConfig{
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.course")
    public DataSourceProperties courseDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.course.configuration")
    public DataSource courseDataSource() {
        return courseDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "courseEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean courseEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(courseDataSource())
                .packages( Course.class)
                .build();
    }

    @Primary
    @Bean(name = "courseTransactionManager")
    public PlatformTransactionManager courseTransactionManager(
            final @Qualifier("courseEntityManagerFactory") LocalContainerEntityManagerFactoryBean courseEntityManagerFactory) {
        return new JpaTransactionManager(courseEntityManagerFactory.getObject());
    }

}