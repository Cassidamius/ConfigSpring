package com.spring.config.initializer;

import com.spring.config.common.Constant;

public final class DynamicDataSourceHolder {
	
	/**
	 * 读库个数
	 */
	private static final Integer SLAVE_COUNTS = 2;

	private static final ThreadLocal<String> holder = new ThreadLocal<String>();

	/**
	 * 随机选择读库
	 * @param name
	 */
	public static void putDataSource(String name) {
		String real_ds_name = name;
		if (name.startsWith(Constant.SLAVE_DATASOURCE_KEY)) {
			int index = (int) (Math.round(Math.random() * 10) % SLAVE_COUNTS);
			real_ds_name += index;
		}
		holder.set(real_ds_name);
	}

	public static String getDataSouce() {
		return holder.get();
	}
	
}
