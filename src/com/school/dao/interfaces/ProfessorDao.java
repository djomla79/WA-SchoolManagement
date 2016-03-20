package com.school.dao.interfaces;

import java.util.List;

import com.school.beans_model.Professor;
import com.school.beans_model.Subject;
import com.school.dao.generic.GenericDao;

public interface ProfessorDao extends GenericDao<Professor, Long> {
	
	/** abstract methods */
	Professor saveProfessor(Professor prof);

	Professor getProfessorByUsername(String username);

	Professor updateProfessor(Professor prof);

	Professor getProfessorByUsernameWithSubjects(String username);

	List<Professor> getAllProfessorsWithSubjects();

	void addSubjectToProfessorByUsername(String username, Subject subject);

	void addSubjectToProfessorById(Long profId, Subject subject);
	
	Professor getProfessorWithSubjectsById(Long profId);

	Professor getProfessorById(Long profId);
	
}
