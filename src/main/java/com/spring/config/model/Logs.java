package com.spring.config.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 操作日志
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_logs")
public class Logs extends BaseEntity {

//	@Id
//	@TableGenerator(name = "logsGen", table = "hibernate_sequences", pkColumnName = "sequence_name", valueColumnName = "sequence_next_hi_value", pkColumnValue = "T_LOGS", allocationSize=1)
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "logsGen")
//	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name="user_id")
	private UserInfo userInfo;

	@Column(name = "class_name")
	private String className;

	@Column(name = "method_name")
	private String methodName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
}
