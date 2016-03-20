package com.school.dao.generic;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.school.commons.BaseEntity;

@Transactional
public abstract class GenericDaoImpl<E extends BaseEntity, K extends Serializable> implements GenericDao<E, K> {
	
	/** field */
	private Class<E> persistentClass;
	
	/** autowired classes */
	@Autowired
	public SessionFactory sessionFactory;
	@Autowired
	public PasswordEncoder encoder;
	
	/** Constructor with parameter */
	public GenericDaoImpl(Class<E> persistentClass) {
		this.persistentClass = persistentClass;
	}
	/** method that returns current session */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	/** geter */
	public Class<E> getPersistentClass() {
		return persistentClass;
	}
	/** Overridden methods */
	@Override
	public E saveEntity(E entity) {
		getSession().save(entity);
		return entity;
	}

	@Override
	public E get(K id) {
		return getSession().get(persistentClass, id);
	}

	@Override
	public E updateEntity(E entity) {
		getSession().update(entity);
		return entity;
	}

	@Override
	public void deleteEntity(E entity) {
		getSession().delete(entity);
	}
	
	@Override
	public void mergeEntity(E entity) {
		getSession().merge(entity);
	}

	@Override
	public E saveOrUpdate(E entity) {
		getSession().saveOrUpdate(entity);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<E> getAll() {
		Criteria criteria = getSession().createCriteria(persistentClass);
		return criteria.list();
	}
	
}
