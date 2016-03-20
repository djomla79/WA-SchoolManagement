package com.school.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.school.beans_model.SubjectRequest;
import com.school.dao.generic.GenericDaoImpl;
import com.school.dao.interfaces.SubjectRequestDao;

@Repository
public class SubjectRequestDaoImpl extends GenericDaoImpl<SubjectRequest, Long> implements SubjectRequestDao {
	
	/** constructor */
	public SubjectRequestDaoImpl() {
		super(SubjectRequest.class);
	}
	/** return subject-request that is saved
	 *  using save method from GenericDaoImpl class */
	@Override
	public SubjectRequest saveSubjectRequest(SubjectRequest request) {
		super.saveEntity(request);
		return request;
	}
	/** return subject-request that matches
	 *  the one in SubjectRequest class by id */
	@Override
	public SubjectRequest getSubjectRequestById(Long subjectId) {
		return (SubjectRequest) getSession().get(SubjectRequest.class, subjectId);
	}
	/** delete subject-request, 
	 *  using delete method from GenericDaoImpl class */
	@Override
	public void deleteSubjectRequest(SubjectRequest request) {
		super.deleteEntity(request);
	}
	/** return list of all subject-requests from SubjectRequest class */
	@SuppressWarnings("unchecked")
	@Override
	public List<SubjectRequest> getAll() {
		Criteria criteria = getSession().createCriteria(SubjectRequest.class);
		return criteria.list();
	}
	
}
