package com.spring.config.service;

import java.io.Serializable;
import java.util.List;

/**
 * 基础服务
 * 
 * @author yinsl
 * 
 * @param <T>
 * @param <ID>
 */
public interface BaseService<T extends Serializable, ID extends Serializable> {

    /**
     * 保存对象
     */
    ID save(T t);

   // @DataSourceType("master")
    void update(T t);

    T get(ID id);

    List<T> list();

    void delete(ID id);
    
    void deleteObject(T t);

    void saveOrUpdate(T t);

}
