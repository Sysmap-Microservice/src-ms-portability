package com.sysmap.srcmsportability.framework.adapters.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource getDataSource(){

        final HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setDriverClassName("org.postgresql.Driver");
        hikariDataSource.setJdbcUrl("jdbc:postgresql://ec2-54-224-64-114.compute-1.amazonaws.com:5432/ddsiu3l3tbm2b4"); ;
        hikariDataSource.setUsername("vmwnthbejoqkeq");
        hikariDataSource.setPassword("86bfe1b3e27628a052481ebf8d89983709a22fff80e2d0f9b497e2e72e7f98ed");

        hikariDataSource.setMinimumIdle(5);
        hikariDataSource.setMaximumPoolSize(20);
        hikariDataSource.setConnectionTimeout(30000);
        hikariDataSource.setIdleTimeout(600000);
        hikariDataSource.setMaxLifetime(1800000);

        return hikariDataSource;
    }
}
