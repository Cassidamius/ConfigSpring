package com.spring.config.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.spring.config.dao.BaseDao;
import com.spring.config.model.BaseEntity;


/**
 * 基础dao
 * @author yinsl
 *
 * @param <T>
 * @param <ID>
 */
public class BaseDaoImpl<T extends BaseEntity, ID extends Serializable> implements BaseDao<T, ID> {

    protected Class<T> entity;

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
        
    public BaseDaoImpl(Class<T> entity) {
        this.entity = entity;
    }

    @SuppressWarnings("unchecked")
    public ID save(T t) {
        return (ID) getSession().save(t);
    }
    
    public void merge(T t) {
        getSession().merge(t);
    }
    
    public void saveOrUpdate(T t) {
        getSession().saveOrUpdate(t);
    }
    
    public void delete(ID id) {
        getSession().delete(get(id));
    }
    
    public void deleteLogic(ID id) {
    	BaseEntity baseEntity = get(id);
    	baseEntity.setDeleteFlag(0);
    	getSession().update(baseEntity);
    }
    
    public void deleteObject(T t) {
        getSession().delete(t);
    }

    public void update(T t) {
        getSession().update(t);
    }

    @SuppressWarnings("unchecked")
    public T get(ID id) {
        return (T) getSession().get(entity, id);
    }
    
    @SuppressWarnings("unchecked")
    public T load(ID id) {
    	return (T) getSession().load(entity, id);
    }
    
    public Integer update(String hql, List<Object> objList) {
    	Query query = getHqlQuery(hql, objList);
    	return query.executeUpdate();
    }

    /**
     * 查询的结果记录总数
     */
    public int queryRowCount(String hql) {
        Query query = getSession().createQuery(hql);
        return ((Long) query.iterate().next()).intValue();
    }
    
    public int queryRowCount(String hql, List<Object> objList) {
        Query query = getHqlQuery(hql, objList);
        Object obj = query.uniqueResult();
        return ((Long)obj).intValue();
    }

    @SuppressWarnings("unchecked")
    public List<T> queryForPage(String hql, int beginIndex, int pageSize) {
        Query query = getSession().createQuery(hql);
        query.setFirstResult(beginIndex).setMaxResults(pageSize);
        return query.list();
    }
    
    @SuppressWarnings("unchecked")
    public List<T> queryForPage(String hql, List<Object> objs, int beginIndex, int pageSize) {
        Query query = getHqlQuery(hql, objs);
        return query.setFirstResult(beginIndex).setMaxResults(pageSize).list();
    }

    @SuppressWarnings("rawtypes")
    public Object getObjectByHql(String hql, List<Object> args) {
        List list = getListByHql(hql, args);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public List getListByHql(String hql, List<Object> args) {
        Query query = getHqlQuery(hql, args);
        return query.list();
    }
    
    @SuppressWarnings("rawtypes")
    public List getListBySql(String sql, List<Object> args) {
    	Query query = getSqlQuery(sql, args);
    	return query.list();
    }

    private Query getHqlQuery(String hql, List<Object> objList) {
        Query query = getSession().createQuery(hql);
        if (null != objList && objList.size() > 0) {
            for (int i = 0; i < objList.size(); i++) {
                query.setParameter(i, objList.get(i));
            }
        }
        return query;
    }
    
    private Query getSqlQuery(String sql, List<Object> objList) {
        Query query = getSession().createSQLQuery(sql);
        if (null != objList && objList.size() > 0) {
            for (int i = 0; i < objList.size(); i++) {
                query.setParameter(i, objList.get(i));
            }
        }
        return query;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public List<T> list() {
        return getSession().createCriteria(entity).list();
    }

}
