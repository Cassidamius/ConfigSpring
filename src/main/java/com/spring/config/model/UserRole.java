package com.spring.config.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "T_USER_ROLE")
@IdClass(UserRoleKey.class)
public class UserRole {

	@Id
	@Column(name="role_id")
	private Integer roleId;
	
	@Id
	@Column(name="user_id")
	private Integer userId;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}

@SuppressWarnings("serial")
class UserRoleKey implements Serializable {
	
	private Integer roleId;
	
	private Integer userId;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Override
	public int hashCode() {
		return userId.hashCode() + roleId.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof UserRoleKey) {
			UserRoleKey key = (UserRoleKey) obj;
			if (key.roleId == null || key.userId == null) {
				return false;
			} else if (roleId.intValue() == key.roleId.intValue() && userId.intValue() == key.userId.intValue()) {
					return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
}
