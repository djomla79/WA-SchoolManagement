package com.school.beans_model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Grade {
	
	@ManyToOne
	private Subject subject;
	
	@ManyToOne
	private Student student;
	
	private int gradeValue;
	

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
