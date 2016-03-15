package com.school.beans_model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.school.commons.BaseEntity;

@Entity
public class Absence extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	private int absenceCounter;
	
	@OneToOne
	private Subject subject;
	@OneToOne
	private Student student;
	
	
	public Absence() {
		/** Empty default constructor */
	}
	
	/** Geters and Seters */
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

	public int getAbsenceCounter() {
		return absenceCounter;
	}

	public void setAbsenceCounter(int absence) {
		this.absenceCounter = absence;
	}
	
}
