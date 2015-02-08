package com.spring.config.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.config.dao.ResourcesDao;
import com.spring.config.model.Resource;
import com.spring.config.pagination.PageResultSet;
import com.spring.config.service.ResourcesService;

@Service("resourcesService")
public class ResourcesServiceImpl extends BaseServiceImpl<Resource, Integer> implements ResourcesService {

	@Autowired
	private ResourcesDao resourcesDao;
	
	public ResourcesServiceImpl() {
	    super(Resource.class);
    }

	@Override
    public PageResultSet<Resource> queryForPage(Resource condition, int pageSize, int page) {
	    // TODO Auto-generated method stub
	    return null;
    }

}
