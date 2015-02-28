package com.spring.config.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "T_RESC")
public class Resource extends BaseEntity {
	
	public Resource() {
		
	}
	
	public Resource(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "resc_type")
	private String rescType;

	@Column(name = "resc_string")
	private String rescString;

	@Column(name = "priority")
	private Integer priority;

	@Column(name = "descn")
	private String descn;
	
	@ManyToMany(mappedBy="resources")
	private Set<Role> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRescType() {
		return rescType;
	}

	public void setRescType(String rescType) {
		this.rescType = rescType;
	}

	public String getRescString() {
		return rescString;
	}

	public void setRescString(String rescString) {
		this.rescString = rescString;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
