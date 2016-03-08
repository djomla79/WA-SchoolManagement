package com.school.beans_model;

import javax.persistence.Entity;

import com.school.commons.BaseEntity;

@Entity
public class SubjectRequest extends BaseEntity {
	
private static final long serialVersionUID = 1L;
	
	private Long studentId;
	private Long subjectId;
	
	
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	
}
