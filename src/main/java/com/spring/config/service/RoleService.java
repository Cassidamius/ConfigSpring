package com.spring.config.service;

import java.util.List;

import com.spring.config.model.Role;

public interface RoleService extends PageService<Role, Integer> {
	
	Role getRole(int id);
	
	List<Role> getRoleSetByUserId(Integer userId);
	
	List<Role> getRoleListByRoleIds(List<Integer> roleIds);
	
}
