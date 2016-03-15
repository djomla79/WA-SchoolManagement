package com.school.beans_model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Professor extends User {
	
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="professor", targetEntity=Subject.class)
	private List<Subject> subjects = new ArrayList<>();
	
	
	public Professor() {
		/** Empty default constructor */
	}
	
	public Professor(User user) {
		this.setId(user.getId());
		this.setName(user.getName());
		this.setLastName(user.getLastName());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
	}
	
	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	
	public void addSubject(Subject subject) {
		subjects.add(subject);
	}
	
}
