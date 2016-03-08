package com.school.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.school.beans_model.SubjectRequest;
import com.school.dao.generic.GenericDaoImpl;
import com.school.dao.interfaces.SubjectRequestDao;

@Repository
public class SubjectRequestDaoImpl extends GenericDaoImpl<SubjectRequest, Long> implements SubjectRequestDao {

	public SubjectRequestDaoImpl() {
		super(SubjectRequest.class);
	}
	
	@Override
	public SubjectRequest saveSubjectRequest(SubjectRequest request) {
		super.saveEntity(request);
		return request;
	}
	
	@Override
	public SubjectRequest getSubjectRequestById(Long subjectId) {
		return (SubjectRequest) getSession().get(SubjectRequest.class, subjectId);
	}
	
	@Override
	public void deleteSubjectRequest(SubjectRequest request) {
		super.deleteEntity(request);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SubjectRequest> getAll() {
		Criteria criteria = getSession().createCriteria(SubjectRequest.class);
		return criteria.list();
	}
	
}
