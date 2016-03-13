package com.school.beans_model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Absence {
	
	private int absenceCounter;
	
	@ManyToOne
	private Subject subject;
	

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public int getAbsence() {
		return absenceCounter;
	}

	public void setAbsence(int absence) {
		this.absenceCounter = absence;
	}
	
}
