package com.spring.config.initializer;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.spring.config.common.Constant;

@Configuration
//加载资源文件
@PropertySource({"classpath:/config/properties/db.properties"})
public class DynamicDataSourceConfig {

	private static final Logger logger = Logger.getLogger(DynamicDataSourceConfig.class);
	
	@Bean(name = Constant.DYNAMIC_DATASOURCE)
	public AbstractRoutingDataSource dynamicDataSource() {
		logger.info("create " + Constant.DYNAMIC_DATASOURCE);
		AbstractRoutingDataSource ds = new AbstractRoutingDataSource() {
			@Override
			protected Object determineCurrentLookupKey() {
				return DynamicDataSourceHolder.getDataSouce();
			}
		};
		ds.setDefaultTargetDataSource(masterDataSource());
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		targetDataSources.put(Constant.MASTER_DATASOURCE_KEY, masterDataSource());
		targetDataSources.put(Constant.SLAVE_DATASOURCE_KEY_0, slaveDataSource0());
		targetDataSources.put(Constant.SLAVE_DATASOURCE_KEY_1, slaveDataSource1());
		ds.setTargetDataSources(targetDataSources);
		return ds;
	}
	
	/**                                                          
	* 描述 : <负责解析资源文件>. <br> 
	*<p> 
		<这个类必须有，而且必须声明为static，否则不能正常解析>  
	 </p>                                                                                                                                                                                                                                                
	* @return                                                                                                      
	*/  
	@Bean
    public static PropertySourcesPlaceholderConfigurer placehodlerConfigurer() {
		logger.info("PropertySourcesPlaceholderConfigurer");
        return new PropertySourcesPlaceholderConfigurer();
    }
	
	/**
	 * 绑定资源属性
	 */
	@Value("${jdbc.driver.master}")
	String masterDriverClass;
	
	@Value("${jdbc.url.master}")
	String masterUrl;
	
	@Value("${jdbc.username.master}")
	String masterUserName;
	
	@Value("${jdbc.password.master}")
	String masterPassWord;
	
	public DataSource masterDataSource() {
		logger.info("MasterDataSource");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(masterDriverClass);
		dataSource.setUrl(masterUrl);
		dataSource.setUsername(masterUserName);
		dataSource.setPassword(masterPassWord);
		return dataSource;
	}
	
	@Value("${jdbc.driver.slave0}")
	String driverClassSlave0;
	@Value("${jdbc.url.slave0}")
	String urlSlave0;
	@Value("${jdbc.username.slave0}")
	String userNameSlave0;
	@Value("${jdbc.password.slave0}")
	String passWordSlave0;
	
	@Bean(name = "slaveDataSource0")
	public DataSource slaveDataSource0() {
		logger.info("SlaveDataSource0");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassSlave0);
		dataSource.setUrl(urlSlave0);
		dataSource.setUsername(userNameSlave0);
		dataSource.setPassword(passWordSlave0);
		return dataSource;
	}
	
	@Value("${jdbc.driver.slave1}")
	String driverClassSlave1;
	@Value("${jdbc.url.slave1}")
	String urlSlave1;
	@Value("${jdbc.username.slave1}")
	String userNameSlave1;
	@Value("${jdbc.password.slave1}")
	String passWordSlave1;
	
	@Bean(name = "slaveDataSource1")
	public DataSource slaveDataSource1() {
		logger.info("SlaveDataSource1");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassSlave1);
		dataSource.setUrl(urlSlave1);
		dataSource.setUsername(userNameSlave1);
		dataSource.setPassword(passWordSlave1);
		return dataSource;
	}
	
}
