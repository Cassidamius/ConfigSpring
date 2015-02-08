package com.spring.config.initializer;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
@Import({ DynamicDataSourceConfig.class })
@PropertySource({ "classpath:/config/properties/hibernate.properties" })
public class HibernateConfig {

	private static final Logger logger = Logger.getLogger(HibernateConfig.class);

	@Value("${hibernate.dialect}")
	private String hibernate_dialect;

	@Value("${hibernate.show_sql}")
	private String hibernate_show_sql;

	@Value("${hibernate.current_session_context_class}")
	private String hibernate_current_session_context_class;

	@Resource(name = "dynamicDataSource")
	private DataSource dynamicDataSource;

	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean localSessionFactoryBean() {
		logger.info("sessionFactory");
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dynamicDataSource);
		String[] packagesToScan = new String[] { "com.spring.config.model" };
		sessionFactory.setPackagesToScan(packagesToScan);

		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", hibernate_dialect);
		hibernateProperties.setProperty("hibernate.show_sql", hibernate_show_sql);
		hibernateProperties.setProperty("hibernate.current_session_context_class",
		        hibernate_current_session_context_class);
		sessionFactory.setHibernateProperties(hibernateProperties);

		return sessionFactory;
	}

	@Bean(name = "transactionManager")
	public HibernateTransactionManager hibernateTransactionManager() {
		logger.info("transactionManager");
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(localSessionFactoryBean().getObject());
		return hibernateTransactionManager;
	}

}
