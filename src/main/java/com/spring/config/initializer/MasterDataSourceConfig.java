package com.spring.config.initializer;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/** 
 *Description: <类功能描述>. <br>
 *<p><使用说明></p>
 *Makedate:2014年9月3日 下午5:24:02 
 * @author Administrator  
 * @version V1.0                             
 */
@Configuration
//加载资源文件
@PropertySource({"classpath:/config/properties/mdb.properties"})
public class MasterDataSourceConfig {
	
	private static final Logger logger = Logger.getLogger(MasterDataSourceConfig.class);
	
	/**
	 * 绑定资源属性
	 */
	@Value("${master.jdbc.driver}")
	String masterDriverClass;
	
	@Value("${master.jdbc.url}")
	String masterUrl;
	
	@Value("${master.jdbc.username}")
	String masterUserName;
	
	@Value("${master.jdbc.password}")
	String masterPassWord;
	
	@Bean(name = "masterDataSource")
	public DataSource masterDataSource() {
		logger.info("MasterDataSource");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(masterDriverClass);
		dataSource.setUrl(masterUrl);
		dataSource.setUsername(masterUserName);
		dataSource.setPassword(masterPassWord);
		return dataSource;
	}
}


