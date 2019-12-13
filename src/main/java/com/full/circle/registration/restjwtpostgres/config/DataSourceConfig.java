package com.full.circle.registration.restjwtpostgres.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    //org.postgresql.Driver
    @Value("#{systemEnvironment['DRIVER_DB']}")
    public String driver;

    //jdbc:postgresql://localhost:5432/security
    @Value("#{systemEnvironment['URL']}")
    public String url;

    //postgres
    @Value("#{systemEnvironment['USERNAME']}")
    public String username;

    //masterkey
    @Value("#{systemEnvironment['PASW']}")
    public String pasw;

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(pasw);
        return dataSourceBuilder.build();
    }
}

