package com.school.dao.generic;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.school.commons.BaseEntity;

@Transactional
public abstract class GenericDaoImpl<T extends BaseEntity, ID extends Serializable> implements GenericDao<T, ID> {
	
	@Autowired
	public SessionFactory sessionFactory;
	
	private Class<T> persistentClass;
	
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public GenericDaoImpl(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	

	public Class<T> getPersistentClass() {
		return persistentClass;
	}
	
	@Override
	public T saveEntity(T entity) {
		getSession().save(entity);
		return entity;
	}

	@Override
	public T get(ID id) {
		return getSession().get(persistentClass, id);
	}

	@Override
	public T updateEntity(T entity) {
		getSession().update(entity);
		return entity;
	}

	@Override
	public void deleteEntity(T entity) {
		getSession().delete(entity);
	}
	
	@Override
	public void mergeEntity(T entity) {
		getSession().merge(entity);
	}

	@Override
	public T saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<T> getAll() {
		
		Criteria criteria = getSession().createCriteria(persistentClass);
		
		return criteria.list();
	}
	
}
