package com.spring.config.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.config.dao.RoleDao;
import com.spring.config.model.Role;
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
    public PageResultSet<Role> queryForPage(Role condition, int pageSize, int page) {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Role getRole(int id) {
		Criteria c = roleDao.getSession().createCriteria(Role.class);  
        c.add(Restrictions.eq("id", id)); 
        
        c.setFetchMode("resources", FetchMode.JOIN);  
        Role role = (Role) c.uniqueResult();
        return role;
    }
	
	@SuppressWarnings("unchecked")
    public List<Role> getRoleSetByUserId(Integer userId) {
		String hql = "select new Role(r.id, r.name, r.roleDesc,r.version) from Role r, UserRole ur where ur.userId = ? and ur.roleId = r.id";
        List<Object> objList = new ArrayList<Object>();
        objList.add(userId);
        List<Role> roleList = roleDao.getListByHql(hql, objList);
        return roleList;
	}
	
}
