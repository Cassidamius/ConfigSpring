package com.spring.config.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo, Integer> implements UserDetailsService,
        UserInfoService {

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

	@DataSourceType(Constant.MASTER_DATASOURCE_KEY)
	public void updateUserInfo(UserInfo userInfo) {
		String hql = "update UserInfo t set t.descn = :descn, t.nickName = :nickName, t.address = :address, t.mobile = :mobile, "
		        + " t.version = t.version + 1, t.updateTime = current_timestamp where t.id = :id and t.version = :version";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("descn", userInfo.getDescn());
		map.put("nickName", userInfo.getNickName());
		map.put("address", userInfo.getAddress());
		map.put("mobile", userInfo.getMobile());
		map.put("id", userInfo.getId());
		map.put("version", userInfo.getVersion());
		userInfoDao.update(hql, map);
	}

	@Override
	@DataSourceType(Constant.SLAVE_DATASOURCE_KEY)
	public PageResultSet<UserInfo> queryForPage(UserInfo user, int pageSize, int currentPage) {
		String hql = "SELECT count(id) FROM UserInfo ui where ui.deleteFlag = 1 ";
		Map<String, Object> map = new HashMap<String, Object>();
		int totalRow = userInfoDao.queryRowCount(createHql(hql, user, map), map);
		List<UserInfo> users = null;
		PageInfo pageInfo = new PageInfo(totalRow, pageSize, currentPage);
		if (totalRow == 0) {
			users = new ArrayList<UserInfo>();
		} else {
			hql = "FROM UserInfo ui where ui.deleteFlag = 1 ";
			map.clear();
			users = userInfoDao.queryForPage(createHql(hql, user, map), map, pageInfo.countOffset(),
			        pageInfo.getPageSize());
		}

		return new PageResultSet<UserInfo>(pageInfo, users);
	}

	private String createHql(String hql, UserInfo userInfo, Map<String, Object> map) {
		StringBuffer sb = new StringBuffer(hql);
		if (userInfo != null) {
			if (StringUtils.isNotEmpty(userInfo.getUserName())) {
				sb.append(" and ui.userName like :userName ");
				map.put("userName", "%" + userInfo.getUserName() + "%");
			}
			if (StringUtils.isNotEmpty(userInfo.getNickName())) {
				sb.append(" and ui.nickName like :nickName ");
				map.put("nickName", "%" + userInfo.getNickName() + "%");
			}
		}
		return sb.toString();
	}

	@DataSourceType(Constant.SLAVE_DATASOURCE_KEY)
	public UserInfo getUserInfo(String name, String password) {
		String hql = "from UserInfo where username = :username and password= :password";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", name);
		map.put("password", password);
		return (UserInfo) userInfoDao.getObjectByHql(hql, map);
	}

	@DataSourceType(Constant.SLAVE_DATASOURCE_KEY)
	public UserInfo getUserInfo(String name) {
		return (UserInfo) userInfoDao.getSession().createCriteria(UserInfo.class)
		        .add(Restrictions.eq("userName", name)).setFetchMode("roles", FetchMode.JOIN).uniqueResult();
	}

	public UserInfo getUserInfoById(Integer id) {
		String hql = "select new UserInfo( id, userName, nickName, namePinyin, address, telephone, "
		        + " birthday, mobile, descn, version) from UserInfo where id = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return (UserInfo) userInfoDao.getObjectByHql(hql, map);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = getUserInfo(username);
		if (user == null) {
			throw new UsernameNotFoundException("user '" + username + "' not found.");
		}
		return user;
	}

}
