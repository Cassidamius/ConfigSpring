package com.spring.config.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.config.dao.DictDao;
import com.spring.config.model.Dict;
import com.spring.config.pagination.PageResultSet;
import com.spring.config.service.DictService;

@Service("dictService")
@Transactional
public class DictServiceImpl extends BaseServiceImpl<Dict, Integer> implements DictService {

	@Autowired
	private DictDao dictDao;

	public DictServiceImpl() {
		super(Dict.class);
	}
	
	@SuppressWarnings("unchecked")
    public List<Dict> getDictListByKey(String key) {
		String hql = " from Dict t where t.dictKey = :dictKey ";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dictKey", key);
		return dictDao.getListByHql(hql, map);
	}

	@Override
    public PageResultSet<Dict> queryForPage(Dict condition, int pageSize, int currentPage) {
	    // TODO Auto-generated method stub
	    return null;
    }

}
