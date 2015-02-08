package com.spring.config.dao.impl;

import org.springframework.stereotype.Repository;

import com.spring.config.dao.ResourcesDao;
import com.spring.config.model.Resource;

@Repository
public class ResourcesDaoImpl extends BaseDaoImpl<Resource, Integer> implements ResourcesDao {

    public ResourcesDaoImpl() {
        super(Resource.class);
    }

}
