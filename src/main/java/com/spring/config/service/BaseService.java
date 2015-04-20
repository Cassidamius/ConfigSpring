package com.spring.config.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.spring.config.model.BaseEntity;

/**
 * 基础服务
 * 
 * @author yinsl
 * 
 * @param <T>
 * @param <ID>
 */
public interface BaseService<T extends BaseEntity, ID extends Serializable> extends UserDetailsService {

    ID save(T t);

    void update(T t);

    T get(ID id);
    
    T load(ID id);

    List<T> list();

    void delete(ID id);
    
    void deleteLogic(ID id);
    
    void deleteObject(T t);

    void saveOrUpdate(T t);
    
    void merge(T t);

}
