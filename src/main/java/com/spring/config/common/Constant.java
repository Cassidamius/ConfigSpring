package com.spring.config.common;

public class Constant {
	
	public final static String MASTER_DATASOURCE = "masterDataSource";
	
	public final static String SLAVE_DATASOURCE_A = "slaveDataSourceA";
	
	public final static String SLAVE_DATASOURCE_B = "slaveDataSourceB";
	
	public final static String DYNAMIC_DATASOURCE = "dynamicDataSource";
	
	public final static String MASTER_DATASOURCE_KEY = "master";
	
	public final static String SLAVE_DATASOURCE_KEY = "slave";
	
	public final static String SLAVE_DATASOURCE_KEY_A = "slaveA";
	
	public final static String SLAVE_DATASOURCE_KEY_B = "slaveB";
	
	/**
	 * 读库个数
	 */
	public final static int SLAVE_DATASOURCE_COUNT = 2;
	
	/**
	 * 读库后缀
	 */
	public final static String SLAVE_DATASOURCE_SUFFIX = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

}
