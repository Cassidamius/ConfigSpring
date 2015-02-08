package com.spring.config.dao.impl;

import org.springframework.stereotype.Repository;

import com.spring.config.dao.UserInfoDao;
import com.spring.config.model.UserInfo;

/**
 * 用户
 * @author yinsl
 *
 */
@Repository
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo, Integer> implements UserInfoDao {

    public UserInfoDaoImpl() {
        super(UserInfo.class);
    }
    
}
