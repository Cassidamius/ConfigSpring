package com.spring.config.dao;

import java.util.List;
import java.util.Map;

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

    Object getObjectByHql(String hql, Map<String, Object> map);
    
    @SuppressWarnings("rawtypes")
    List getListBySql(String sql, Map<String, Object> map);

    @SuppressWarnings("rawtypes")
    List getListByHql(String hql, Map<String, Object> map);

    List<T> list();
    
    Session getSession();

    ID save(T t);

    void saveOrUpdate(T t);
    
    Integer update(String hql, Map<String, Object> map);

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
    int queryRowCount(String hql, Map<String, Object> map);

    /**
     * 翻页查询
     * 
     * @param hql
     * @param beginIndex
     * @param pageSize
     * @return
     */
    List<T> queryForPage(String hql, int beginIndex, int pageSize);
    
    List<T> queryForPage(String hql, Map<String, Object> map, int beginIndex, int pageSize);

}
