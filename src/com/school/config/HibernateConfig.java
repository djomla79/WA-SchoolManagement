package com.school.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:com/school/properties/dataSource.properties")
public class HibernateConfig {
	
	@Resource
	private Environment env;
	
	@Bean
	public DataSource dataSource() {
		
		DriverManagerDataSource data = new DriverManagerDataSource();
		
		data.setDriverClassName(env.getRequiredProperty("hibernate.driverClass"));
		data.setUrl(env.getRequiredProperty("hibernate.url"));
		data.setUsername(env.getRequiredProperty("hibernate.username"));
		data.setPassword(env.getRequiredProperty("hibernate.password"));
		
		return data;
		
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[]{ "com.school.beans_model" });
		
		sessionFactory.setHibernateProperties(getHibernateProperties());
		
		return sessionFactory;
		
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		
		DataSourceTransactionManager data = new DataSourceTransactionManager();
		
		data.setDataSource(dataSource());
		
		return data;
		
	}
	
	private Properties getHibernateProperties() {
		
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		props.setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl"));
		props.setProperty("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		
		return props;
		
	}
}
