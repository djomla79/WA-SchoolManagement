package com.school.dao.interfaces;

import com.school.beans_model.SubjectRequest;
import com.school.dao.generic.GenericDao;

public interface SubjectRequestDao extends GenericDao<SubjectRequest, Long> {
	
	/** abstract methods */
	SubjectRequest saveSubjectRequest(SubjectRequest request);

	SubjectRequest getSubjectRequestById(Long subjectId);

	void deleteSubjectRequest(SubjectRequest request);
	
}
