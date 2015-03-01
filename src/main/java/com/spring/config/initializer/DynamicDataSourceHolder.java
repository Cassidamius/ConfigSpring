package com.spring.config.initializer;

import com.spring.config.common.Constant;

public class DynamicDataSourceHolder {

	public static final ThreadLocal<String> holder = new ThreadLocal<String>();

	/**
	 * 随机选择读库
	 * @param name
	 */
	public static void putDataSource(String name) {
		String real_ds_name = name;
		if (name.startsWith(Constant.SLAVE_DATASOURCE_KEY)) {
			int index = (int) (Math.round(Math.random() * 10) % Constant.SLAVE_DATASOURCE_COUNT);
			String suffix = String.valueOf(Constant.SLAVE_DATASOURCE_SUFFIX.charAt(index));
			real_ds_name += suffix;
		}
		holder.set(real_ds_name);
	}

	public static String getDataSouce() {
		return holder.get();
	}
	
}
