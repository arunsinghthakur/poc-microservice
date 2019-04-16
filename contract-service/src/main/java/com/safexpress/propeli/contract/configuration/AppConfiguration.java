package com.safexpress.propeli.contract.configuration;

import java.util.HashMap;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = { "com.safexpress.propeli" })
@EnableJpaRepositories(basePackages = {
		"com.safexpress.propeli" }, entityManagerFactoryRef = "entityManager", transactionManagerRef = "transactionManager")
public class AppConfiguration {

	@Value(value = "${app.datasource.driverClassName}")
	private String driverClassName;
	@Value(value = "${app.datasource.url}")
	private String databaseUrl;
	@Value(value = "${app.datasource.username}")
	private String databaseUsername;
	@Value(value = "${app.datasource.password}")
	private String databasePassword;
	@Value(value = "${app.datasource.hikari.connection-timeout}")
	private long hikariTimeout;
	@Value(value = "${app.datasource.hikari.pool-name}")
	private String hikariPoolName;
	@Value(value = "${app.datasource.hikari.idle-timeout}")
	private long hikariIdleTimeout;
	@Value(value = "${app.datasource.hibernate.hbm2ddl.auto}")
	private String hibernatehbm2ddl;
	@Value(value = "${app.datasource.hibernate.dialect}")
	private String hibernateDialect;
	@Value(value = "${app.datasource.hibernate.show_sql}")
	private String showSql;

	@Bean
	@Primary
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(databaseUrl);
		dataSource.setUsername(databaseUsername);
		dataSource.setPassword(databasePassword);

		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setDataSource(dataSource);
		hikariDataSource.setIdleTimeout(hikariIdleTimeout);
		hikariDataSource.setPoolName(hikariPoolName);
		hikariDataSource.setConnectionTimeout(hikariTimeout);

		return hikariDataSource;
	}

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.safexpress.propeli" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", hibernatehbm2ddl);
		properties.put("hibernate.dialect", hibernateDialect);
		properties.put("hibernate.show_sql", showSql);
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Primary
	@Bean
	public PlatformTransactionManager transactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManager().getObject());
		return transactionManager;
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
