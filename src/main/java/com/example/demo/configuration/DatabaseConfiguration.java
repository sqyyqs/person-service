package com.example.demo.configuration;

import com.example.demo.utils.DataSourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseConfiguration {
    @Value("${datasource.driverClassName}")
    private String driver;
    @Value("${database.username}")
    private String username;
    @Value("${database.password}")
    private String password;
    @Value("${database.url}")
    private String url;

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(
                DataSourceUtils.createDataSource(driver, url, username, password)
        );
    }
}

