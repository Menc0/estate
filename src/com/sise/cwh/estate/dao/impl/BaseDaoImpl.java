package com.sise.cwh.estate.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;

import com.sise.cwh.estate.dao.BaseDao;


public class BaseDaoImpl<T> implements BaseDao<T>{
	private SessionFactory sessionFactory;
	protected Class<T> clazz;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	 @SuppressWarnings({ "unchecked", "rawtypes" })
		public BaseDaoImpl() {
	        ParameterizedType type = (ParameterizedType) this.getClass()
	                .getGenericSuperclass();
	        this.clazz = (Class) type.getActualTypeArguments()[0];
	    }
	@Override
	
	
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> T findByKey(Class<T> obj, Serializable key) {
		if(key==null){
			return null;
		}
		try {
			return(T)getSession().get(obj, key);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	//可解决in List
		@SuppressWarnings("rawtypes")
		public List findByHqlQuery(final String hql,final HashMap param){
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			for(Iterator i = param.keySet().iterator();i.hasNext();){
				String key = (String)i.next();
				Object value = param.get(key);
				if(value instanceof List){
					query.setParameter(key, (List)value);
				}else {
					query.setParameter(key, value);
				}
			}
			return query.list();
		}
		
 
   
 
    @Override
	public void delete(String id) {
        Object object = (Object) getSession().get(clazz, id);
        getSession().delete(object);
    }
    @Override
    public void delete(Integer id) {
    	Object object = (Object) getSession().get(clazz, id);
    	getSession().delete(object);
    }
 
    @Override
	@SuppressWarnings("unchecked")
	public T getById(String id) {
        return (T) getSession().get(clazz, id);
    }
 
    @SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
        return getSession().createQuery("from " + clazz.getSimpleName()).list();
    }
 
    @SuppressWarnings("unchecked")
	@Override
	public List<T> getByIds(String[] ids) {
        if (ids == null || ids.length == 0) {
            return Collections.EMPTY_LIST;
        }
        return getSession().createQuery(
                "FROM " + clazz.getSimpleName() + " WHERE id IN(:ids)")
                .setParameterList("ids", ids).list();
    }
 
    @Override
	public int getTotalCount() {
        int totalCount = ((Long)getSession().createQuery("select count(*) from " + clazz.getSimpleName()).list().get(0)).intValue();
        return totalCount;
    }
 
    @Override
	public String save(T entity) {
       getSession().saveOrUpdate(entity);
        return null;
    }
 
    @Override
	public void update(T entity) {
        getSession().update(entity);
    }
     
    @Override
	public int update(String hql,Object...field){
        /*System.out.println(hql);
        for(Object obj : field){
            System.out.println(obj);
        }*/
        Query query = getSession().createQuery(hql);
        if(field.length != 0){
            for(int i=0 ;i < field.length; i++){
                query.setParameter(i, field[i]);
            }
        }
        return query.executeUpdate();       
    }
 
   /* public Pagination getPagination(int pageNo, int pageSize, String sec) {
        Pagination pagination = new Pagination(pageNo, pageSize,
                getTotalCount(), sec);
        @SuppressWarnings("unchecked")
		List<T> list = getSession()
                .createQuery("from " + clazz.getSimpleName()).setFirstResult(
                        pagination.getPageSize()*pagination.getPageNo()).setMaxResults(
                        pagination.getPageSize()*(pagination.getPageNo()+1)).list();
        pagination.setList(list);
        return pagination;
    }*/
 
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
