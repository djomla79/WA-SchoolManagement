package com.school.beans_model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Student extends User {
	
	private static final long serialVersionUID = 1L;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="STUDENT_SUBJECT", 
	   		   joinColumns=@JoinColumn(name="student_id"),
	   		   inverseJoinColumns=@JoinColumn(name="subject_id"))
	private List<Subject> studentSubjects = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Grade> studentGrades = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Absence> studentAbsences = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<SubjectRequest> subjectRequests = new ArrayList<>();
	
	public Student() {
		/** Empty default constructor */
	}
	
	public Student(User user) {
		this.setId(user.getId());
		this.setName(user.getName());
		this.setLastName(user.getLastName());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
	}
	
	
	public List<SubjectRequest> getSubjectRequests() {
		return subjectRequests;
	}

	public List<Subject> getStudentSubjects() {
		return studentSubjects;
	}

	public List<Grade> getStudentGrades() {
		return studentGrades;
	}

	public List<Absence> getStudentAbsences() {
		return studentAbsences;
	}
	
}
