package com.spring.config.dao;

import java.util.List;

import org.hibernate.Session;

import com.spring.config.model.BaseEntity;

/**
 * 
 * @param <T>
 *            entity
 * @param <ID>
 *            entity id
 */
public interface BaseDao<T extends BaseEntity, ID> {

    T get(ID id);
    
    T load(ID id);

    Object getObjectByHql(String hql, List<Object> args);
    
    @SuppressWarnings("rawtypes")
    List getListBySql(String sql, List<Object> args);

    @SuppressWarnings("rawtypes")
    List getListByHql(String hql, List<Object> args);

    List<T> list();
    
    Session getSession();

    ID save(T t);

    void saveOrUpdate(T t);
    
    Integer update(String hql, List<Object> objList);

    void delete(ID id);
    
    void deleteLogic(ID id);

    void deleteObject(T t);

    void update(T t);
    
    void merge(T t);

    /**
     * 查询的结果记录总数
     * 
     * @param hql
     * @return
     */
    int queryRowCount(String hql);

    /**
     * 查询的结果记录总数
     * 
     * @param hql
     * @return
     */
    int queryRowCount(String hql, List<Object> objList);

    /**
     * 翻页查询
     * 
     * @param hql
     * @param beginIndex
     * @param pageSize
     * @return
     */
    List<T> queryForPage(String hql, int beginIndex, int pageSize);
    
    List<T> queryForPage(String hql, List<Object> objs, int beginIndex, int pageSize);

}
