package com.spring.config.dao.impl;

import org.springframework.stereotype.Repository;

import com.spring.config.dao.ResourceDao;
import com.spring.config.model.Resource;

@Repository
public class ResourceDaoImpl extends BaseDaoImpl<Resource, Integer> implements ResourceDao {

    public ResourceDaoImpl() {
        super(Resource.class);
    }

}
