package com.school.dao.generic;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface GenericDao<T, ID extends Serializable> {
	
	T saveEntity(T entity);
	T get(ID id);
	T updateEntity(T entity);
	void mergeEntity(T entity);
	void deleteEntity(T entity);
	T saveOrUpdate(T entity);
	
	Collection<T> getAll();
	
}
