package com.school.dao.interfaces;

import java.util.List;

import com.school.beans_model.Professor;
import com.school.beans_model.Student;
import com.school.beans_model.Subject;
import com.school.dao.generic.GenericDao;

public interface SubjectDao extends GenericDao<Subject, Long> {
	
	/** abstract methods */
	Subject saveSubject(Subject subject);

	Subject getSubjectByName(String subjectName);

	Subject updateSubject(Subject subject);

	Subject getSubjectWithStudents(String subjectName);

	Subject getSubjectWithStudentsById(Long id);

	Subject getSubjectById(Long id);

	void addProfessorToSubjectById(Professor professorById, Long subjectId);

	void addStudentToSubject(Student studentById, Long subjectId);

	Subject getSubjectWithProfessor(Long subjectId);

	List<Subject> getSubjectsNotOfThisStudent(Student student);
	
}
