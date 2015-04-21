package com.spring.config.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.spring.config.dao.BaseDao;
import com.spring.config.model.BaseEntity;

/**
 * 基础dao
 * 
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

	public Integer update(String hql, Map<String, Object> map) {
		Query query = getHqlQuery(hql, map);
		return query.executeUpdate();
	}

	/**
	 * 查询的结果记录总数
	 */
	public int queryRowCount(String hql) {
		Query query = getSession().createQuery(hql);
		return ((Long) query.iterate().next()).intValue();
	}

	public int queryRowCount(String hql, Map<String, Object> map) {
		Query query = getHqlQuery(hql, map);
		Object obj = query.uniqueResult();
		return ((Long) obj).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<T> queryForPage(String hql, int beginIndex, int pageSize) {
		Query query = getSession().createQuery(hql);
		query.setFirstResult(beginIndex).setMaxResults(pageSize);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> queryForPage(String hql, Map<String, Object> map, int beginIndex, int pageSize) {
		Query query = getHqlQuery(hql, map);
		return query.setFirstResult(beginIndex).setMaxResults(pageSize).list();
	}

	@SuppressWarnings("rawtypes")
	public Object getObjectByHql(String hql, Map<String, Object> map) {
		List list = getListByHql(hql, map);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public List getListByHql(String hql, Map<String, Object> map) {
		Query query = getHqlQuery(hql, map);
		return query.list();
	}

	@SuppressWarnings("rawtypes")
	public List getListBySql(String sql, Map<String, Object> map) {
		Query query = getSqlQuery(sql, map);
		return query.list();
	}

	private Query getHqlQuery(String hql, Map<String, Object> map) {
		Query query = getSession().createQuery(hql);
		if (map != null) {
			for (String string : map.keySet()) {
				Object obj = map.get(string);
				// 这里考虑传入的参数是什么类型，不同类型使用的方法不同
				if (obj instanceof Collection<?>) {
					query.setParameterList(string, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					query.setParameterList(string, (Object[]) obj);
				} else {
					query.setParameter(string, obj);
				}
			}
		}
		return query;
	}

	private Query getSqlQuery(String sql, Map<String, Object> map) {
		Query query = getSession().createSQLQuery(sql);
		if (map != null) {
			for (String string : map.keySet()) {
				Object obj = map.get(string);
				// 这里考虑传入的参数是什么类型，不同类型使用的方法不同
				if (obj instanceof Collection<?>) {
					query.setParameterList(string, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					query.setParameterList(string, (Object[]) obj);
				} else {
					query.setParameter(string, obj);
				}
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
