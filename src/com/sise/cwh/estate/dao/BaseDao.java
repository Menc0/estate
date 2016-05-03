package com.sise.cwh.estate.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;


public interface BaseDao<T> {

	@SuppressWarnings("hiding")
	public abstract <T> T findByKey(Class<T> obj, Serializable key);
	
	@SuppressWarnings("rawtypes")
	public List findByHqlQuery(final String hql,final HashMap param);

	public abstract void delete(String id);
	
	public abstract void delete(Integer id);

	public abstract T getById(String id);

	public abstract List<T> getAll();

	public abstract List<T> getByIds(String[] ids);

	public abstract int getTotalCount();

	public abstract String save(T entity);

	public abstract void update(T entity);

	public abstract int update(String hql, Object... field);
	
	//public Pagination getPagination(int pageNo, int pageSize, String sec);

}