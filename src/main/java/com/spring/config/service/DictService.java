package com.spring.config.service;

import java.util.List;

import com.spring.config.model.Dict;

public interface DictService extends PageService<Dict, Integer> {
	
	List<Dict> getDictListByKey(String key);

}
