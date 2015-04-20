package com.spring.config.service;

import java.io.Serializable;

import com.spring.config.model.BaseEntity;
import com.spring.config.pagination.PageResultSet;

/**
 * 翻页
 * @author yinsl
 *
 * @param <T>
 * @param <ID>
 */
public interface PageService<T extends BaseEntity, ID extends Serializable> extends BaseService<T, ID> {

    /**
     * 翻页查询
     * 
     * @param condition
     * @param pageSize
     * @param currentPage
     * @return
     */
    PageResultSet<T> queryForPage(T condition, int pageSize, int currentPage);
    
}
