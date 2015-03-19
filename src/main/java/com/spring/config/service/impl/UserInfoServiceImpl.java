package com.spring.config.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.config.annotation.DataSourceType;
import com.spring.config.common.Constant;
import com.spring.config.dao.UserInfoDao;
import com.spring.config.model.UserInfo;
import com.spring.config.pagination.PageInfo;
import com.spring.config.pagination.PageResultSet;
import com.spring.config.service.UserInfoService;

/**
 * 用户
 * 
 * @author yinsl
 * 
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo, Integer> implements UserDetailsService, UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    public UserInfoServiceImpl() {
        super(UserInfo.class);
    }

    @SuppressWarnings("unchecked")
    @DataSourceType(Constant.SLAVE_DATASOURCE_KEY)
    public List<String> getNickNameList() {
        String hql = "select nickName from UserInfo";
        List<String> list = userInfoDao.getListByHql(hql, null);
        return list;
    }

    @Override
    @DataSourceType(Constant.SLAVE_DATASOURCE_KEY)
    public PageResultSet<UserInfo> queryForPage(UserInfo user, int pageSize, int currentPage) {
        String hql = "SELECT count(id) FROM UserInfo ui where 1 = 1 ";
        List<Object> objList = new ArrayList<Object>();
        int totalRow = userInfoDao.queryRowCount(createHql(hql, user, objList), objList);
        List<UserInfo> users = null;
        PageInfo pageInfo = new PageInfo(totalRow, pageSize, currentPage);
        if (totalRow == 0) {
            users = new ArrayList<UserInfo>();
        } else {
            hql = "FROM UserInfo ui where 1 = 1 ";
            objList.clear();
            users = userInfoDao.queryForPage(createHql(hql, user, objList), objList, pageInfo.countOffset(),
                    pageInfo.getPageSize());
        }

        return new PageResultSet<UserInfo>(pageInfo, users);
    }

    private String createHql(String hql, UserInfo userInfo, List<Object> objList) {
        StringBuffer sb = new StringBuffer(hql);
        if (userInfo != null) {
            if (StringUtils.isNotEmpty(userInfo.getUserName())) {
                sb.append(" and ui.userName like ? ");
                objList.add("%" + userInfo.getUserName() + "%");
            }
            if (StringUtils.isNotEmpty(userInfo.getNickName())) {
                sb.append(" and ui.nickName like ? ");
                objList.add("%" + userInfo.getNickName() + "%");
            }
            if (userInfo.getDeleteFlag() != null) {
                sb.append(" and ui.deleteFlag = ? ");
                objList.add(userInfo.getDeleteFlag());
            }
        }
        return sb.toString();
    }

    @DataSourceType(Constant.SLAVE_DATASOURCE_KEY)
    public UserInfo getUserInfo(String name, String password) {
        String hql = "from UserInfo where username = ? and password= ?";
        List<Object> objList = new ArrayList<Object>();
        objList.add(name);
        objList.add(password);
        return (UserInfo) userInfoDao.getObjectByHql(hql, objList);
    }

    @DataSourceType(Constant.SLAVE_DATASOURCE_KEY)
    public UserInfo getUserInfo(String name) {
    	
    	Criteria c = userInfoDao.getSession().createCriteria(UserInfo.class);  
        c.add(Restrictions.eq("userName", name)); 
        
        c.setFetchMode("roles", FetchMode.JOIN);  
        UserInfo ui = (UserInfo) c.uniqueResult(); 
        return ui;
    }

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return getUserInfo(username);
    }

}
