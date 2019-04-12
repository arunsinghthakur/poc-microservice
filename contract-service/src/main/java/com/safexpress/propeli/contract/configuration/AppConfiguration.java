package com.safexpress.propeli.contract.configuration;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = { "com.safexpress.propeli" })
@EnableAutoConfiguration
@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard
@EnableTransactionManagement
public class AppConfiguration {

	@Autowired
	Environment env;

	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("app.datasource.driverClassName"));
		dataSource.setUrl(env.getProperty("app.datasource.url"));
		dataSource.setUsername(env.getProperty("app.datasource.username"));
		dataSource.setPassword(env.getProperty("app.datasource.password"));

		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setDataSource(dataSource);
		hikariDataSource.setIdleTimeout(Long.parseLong(env.getProperty("app.datasource.hikari.connection-timeout")));
		hikariDataSource.setPoolName(env.getProperty("app.datasource.hikari.pool-name"));
		hikariDataSource.setConnectionTimeout(Long.parseLong(env.getProperty("app.datasource.hikari.idle-timeout")));

		return hikariDataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
