package com.cc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by xn032607 on 2017/1/4.
 */
@Configuration
public class DataSourceConfig {

    @Profile("development")
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        hikariDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("123321");
        hikariDataSource.setMaximumPoolSize(100);
        hikariDataSource.setMinimumIdle(10);
        hikariDataSource.setConnectionTestQuery("select 1");
        Properties properties = new Properties();
        properties.setProperty("cachePrepStmts","true");
        properties.setProperty("prepStmtCacheSize","250");
        properties.setProperty("prepStmtCacheSqlLimit","2048");
        properties.setProperty("useServerPrepStmts", "true");
        hikariDataSource.setDataSourceProperties(properties);

        return hikariDataSource;
    }

    @Profile("test")
    @Bean
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123321");
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test?zeroDateTimeBehavior=convertToNull&autoReconnect=true&useUnicode=true&characterEncoding=utf-8");
        druidDataSource.setMaxActive(20);
        druidDataSource.setInitialSize(1);
        druidDataSource.setMinIdle(1);
        druidDataSource.setMaxWait(60000);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        druidDataSource.setValidationQuery("select 1");
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(50);

        return druidDataSource;
    }
}
