package com.spring.config.dao.impl;

import org.springframework.stereotype.Repository;

import com.spring.config.dao.DictDao;
import com.spring.config.model.Dict;

@Repository
public class DictDaoImpl extends BaseDaoImpl<Dict, Integer> implements DictDao {

	public DictDaoImpl() {
		super(Dict.class);
	}
	
}
