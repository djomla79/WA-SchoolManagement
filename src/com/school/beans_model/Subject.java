package com.school.beans_model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.school.commons.BaseEntity;

@Entity
public class Subject extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	private String subjectName;
	
	@OneToOne
	private Professor professor;
	
	@ManyToMany(mappedBy="studentSubjects")
	private List<Student> students = new ArrayList<>();
	
	
	
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public List<Student> getStudents() {
		return students;
	}
	
}
