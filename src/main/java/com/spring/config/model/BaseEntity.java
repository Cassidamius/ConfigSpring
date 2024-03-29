package com.spring.config.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * 所有模型都包含的基本属性
 * @author yinsl
 *
 */
@SuppressWarnings("serial")
@MappedSuperclass
public class BaseEntity implements Serializable {
    
    @Column(name="delete_flag")
    protected Integer deleteFlag;
    
    @Column(name="create_time")
    protected Timestamp createTime;
    
    @Column(name="update_time")
    protected Timestamp updateTime;
    
    @Version
    @Column(name="version")
    protected Integer version;

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
