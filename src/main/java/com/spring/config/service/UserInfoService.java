package com.spring.config.service;

import java.util.List;

import com.spring.config.annotation.DataSourceType;
import com.spring.config.model.UserInfo;

/**
 * 用户
 * 
 * @author yinsl
 * 
 */
public interface UserInfoService extends PageService<UserInfo, Integer> {

	@DataSourceType("slave")
    UserInfo getUserInfo(String name, String password);

    UserInfo getUserInfo(String name);

    List<String> getNickNameList();
    
    void updateUserInfo(UserInfo userInfo);
    
}
