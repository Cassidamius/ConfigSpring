package com.spring.config.dao.impl;

import org.springframework.stereotype.Repository;

import com.spring.config.dao.RoleDao;
import com.spring.config.model.Role;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role, Integer> implements RoleDao {

    public RoleDaoImpl() {
        super(Role.class);
    }

}
