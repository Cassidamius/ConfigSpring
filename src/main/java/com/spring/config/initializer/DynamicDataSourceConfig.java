package com.spring.config.initializer;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.spring.config.common.Constant;

@Configuration
@Import({ MasterDataSourceConfig.class, SlaveDataSourceConfig.class })
public class DynamicDataSourceConfig {

	private static final Logger logger = Logger.getLogger(DynamicDataSourceConfig.class);
	
	@Resource(name = Constant.MASTER_DATASOURCE)
	public DataSource masterDataSource;

	@Resource(name = Constant.SLAVE_DATASOURCE)
	public DataSource slaveDataSource;

	@Bean(name = Constant.DYNAMIC_DATASOURCE)
	public AbstractRoutingDataSource dynamicDataSource() {
		logger.info("create " + Constant.DYNAMIC_DATASOURCE);
		AbstractRoutingDataSource ds = new AbstractRoutingDataSource() {
			@Override
			protected Object determineCurrentLookupKey() {
				return DynamicDataSourceHolder.getDataSouce();
			}
		};
		ds.setDefaultTargetDataSource(masterDataSource);
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		targetDataSources.put(Constant.MASTER_DATASOURCE_KEY, masterDataSource);
		targetDataSources.put(Constant.SLAVE_DATASOURCE_KEY, slaveDataSource);
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

}
