package com.spring.config.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "T_ROLE")
public class Role extends BaseEntity {
	
	public Role() {
		
	}
	
	public Role(Integer id, String name, String roleDesc, Integer version) {
		this.id = id;
		this.name = name;
		this.roleDesc = roleDesc;
		this.version = version;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="descn")
	private String roleDesc;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_resc_role", joinColumns = { @JoinColumn(name = "role_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "resc_id") })
	private List<Resource> resources;

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

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	
}
