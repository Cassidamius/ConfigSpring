package com.spring.config.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.config.annotation.DataSourceType;
import com.spring.config.common.Constant;
import com.spring.config.dao.RoleDao;
import com.spring.config.model.Resource;
import com.spring.config.model.Role;
import com.spring.config.pagination.PageInfo;
import com.spring.config.pagination.PageResultSet;
import com.spring.config.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	public RoleServiceImpl() {
		super(Role.class);
	}

	@Override
	@DataSourceType(Constant.SLAVE_DATASOURCE_KEY)
    public PageResultSet<Role> queryForPage(Role condition, int pageSize, int currentPage) {
		 String hql = "SELECT count(id) FROM Role r where 1 = 1 ";
	        List<Object> objList = new ArrayList<Object>();
	        int totalRow = roleDao.queryRowCount(createHql(hql, condition, objList), objList);
	        List<Role> roles = null;
	        PageInfo pageInfo = new PageInfo(totalRow, pageSize, currentPage);
	        if (totalRow == 0) {
	            roles = new ArrayList<Role>();
	        } else {
	            hql = "FROM Role r where 1 = 1 ";
	            objList.clear();
	            roles = roleDao.queryForPage(createHql(hql, condition, objList), objList, pageInfo.countOffset(),
	                    pageInfo.getPageSize());
	        }

	        return new PageResultSet<Role>(pageInfo, roles);
	    }

	    private String createHql(String hql, Role role, List<Object> objList) {
	        StringBuffer sb = new StringBuffer(hql);
	        if (role != null) {
	            if (StringUtils.isNotEmpty(role.getName())) {
	                sb.append(" and r.name like ? ");
	                objList.add("%" + role.getName() + "%");
	            }
	            if (StringUtils.isNotEmpty(role.getRoleDesc())) {
	                sb.append(" and r.roleDesc like ? ");
	                objList.add("%" + role.getRoleDesc() + "%");
	            }
	        }
	        return sb.toString();
	    }

	@Override
	@DataSourceType(Constant.SLAVE_DATASOURCE_KEY)
    public Role getRole(int id) {
		Criteria c = roleDao.getSession().createCriteria(Role.class);  
        c.add(Restrictions.eq("id", id)); 
        
        c.setFetchMode("resources", FetchMode.JOIN);  
        Role role = (Role) c.uniqueResult();
       Collections.sort(role.getResources(), new Comparator<Resource>() {

		@Override
        public int compare(Resource o1, Resource o2) {
	        String n1 = o1.getName();
	        String n2 = o2.getName();
	        return n1.compareTo(n2);
        }
    	   
       });
        return role;
    }
	
	@SuppressWarnings("unchecked")
	@DataSourceType(Constant.SLAVE_DATASOURCE_KEY)
    public List<Role> getRoleSetByUserId(Integer userId) {
		String hql = "select new Role(r.id, r.name, r.roleDesc,r.version) from Role r, UserRole ur where ur.userId = ? and ur.roleId = r.id";
        List<Object> objList = new ArrayList<Object>();
        objList.add(userId);
        List<Role> roleList = roleDao.getListByHql(hql, objList);
        return roleList;
	}
	
}
