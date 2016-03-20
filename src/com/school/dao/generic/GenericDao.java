package com.school.dao.generic;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface GenericDao<E, K extends Serializable> {
	
	/** abstract methods */
	E saveEntity(E entity);
	E get(K id);
	E updateEntity(E entity);
	void mergeEntity(E entity);
	void deleteEntity(E entity);
	E saveOrUpdate(E entity);
	
	Collection<E> getAll();
	
}
