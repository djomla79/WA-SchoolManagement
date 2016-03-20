package com.school.beans_model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.school.commons.BaseEntity;

@Entity
public class Grade extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	/** fields */
	@ManyToOne
	private Subject subject;
	@ManyToOne
	private Student student;
	
	private int gradeValue;
	
	
	public Grade() {
		/** Empty default constructor */
	}
	
	/** Geters and seters */
	public Subject getSubject() {
		return subject;
	}
	
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getGradeValue() {
		return gradeValue;
	}

	public void setGradeValue(int gradeValue) {
		this.gradeValue = gradeValue;
	}
	
}
