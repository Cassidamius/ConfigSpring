package com.spring.config.service.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.spring.config.annotation.DataSourceType;
import com.spring.config.dao.BaseDao;
import com.spring.config.service.BaseService;

/**
 * 基础服务
 * 
 * @author yinsl
 * 
 * @param <T>
 * @param <ID>
 */
@Transactional
public class BaseServiceImpl<T extends Serializable, ID extends Serializable> implements BaseService<T, ID> {

    private Class<T> entity;

    private BaseDao<T, ID> baseDao;

    public BaseServiceImpl(Class<T> entity) {
        this.entity = entity;
    }

    @DataSourceType("master")
    public void saveOrUpdate(T t) {
        getBaseDao().saveOrUpdate(t);
    }

    public ID save(T t) {
        return (ID) getBaseDao().save(t);
    }

    @DataSourceType("master")
    public void update(T t) {
        getBaseDao().update(t);
    }

    public void delete(ID id) {
        getBaseDao().delete(id);
    }
    
    public void deleteObject(T t) {
        getBaseDao().deleteObject(t);
    }

    public T get(ID id) {
        return (T) getBaseDao().get(id);
    }

    public List<T> list() {
        return getBaseDao().list();
    }

    @SuppressWarnings("unchecked")
    public BaseDao<T, ID> getBaseDao() {
        if (baseDao == null) {
            String name = entity.getSimpleName();
            name = String.valueOf(name.charAt(0)).toLowerCase() + name.substring(1);
            Field field = null;
            try {
                field = getClass().getDeclaredField(name + "Dao");
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(this.getClass().getSimpleName() + "没有" + name + "Dao这个属性！");
            }
            field.setAccessible(true);
            try {
                baseDao = (BaseDao<T, ID>) field.get(this);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(this.getClass().getSimpleName() + "的属性：" + name + "Dao没有注入！");
            }
        }
        return baseDao;
    }
    
}
