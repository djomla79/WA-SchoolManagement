package com.school.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.school.beans_model.Professor;
import com.school.beans_model.Subject;
import com.school.dao.generic.GenericDaoImpl;
import com.school.dao.interfaces.ProfessorDao;

@Repository
public class ProfessorDaoImpl extends GenericDaoImpl<Professor, Long> implements ProfessorDao {

	public ProfessorDaoImpl() {
		super(Professor.class);
	}
	
	@Override
	public Professor saveProfessor(Professor prof) {
		super.saveEntity(prof);
		return prof;
	}
	
	@Override
	public Professor updateProfessor(Professor prof) {
		super.updateEntity(prof);
		return prof;
	}
	
	@Override
	public Professor getProfessorByUsername(String username) {
		Criteria criteria = getSession().createCriteria(Professor.class);
		criteria.add(Restrictions.eq("username", username));
		return (Professor) criteria.uniqueResult();
	}

	@Override
	public Professor getProfessorByUsernameWithSubjects(String username) {
		Professor prof = getProfessorByUsername(username);
		Hibernate.initialize(prof.getSubjects());
		return prof;
	}
	
	@Override
	public List<Professor> getAllProfessorsWithSubjects() {
		List<Professor> professors = (List<Professor>) getAll();
		for (Professor prof : professors) {
			Hibernate.initialize(prof.getSubjects());
		}
		return professors;
	}
	
	@Override
	public void addSubjectToProfessorByUsername(String username, Subject subject) {
		Professor professor = getProfessorByUsernameWithSubjects(username);
		professor.getSubjects().add(subject);	
		updateProfessor(professor);
	}
	@Override
	public void addSubjectToProfessorById(Long profId, Subject subject) {
		Professor professor = getProfessorWithSubjectsById(profId);
		professor.getSubjects().add(subject);	
		updateProfessor(professor);
	}

	@Override
	public Professor getProfessorWithSubjectsById(Long profId) {
		Professor prof = getProfessorById(profId);
		Hibernate.initialize(prof.getSubjects());
		return prof;
	}
	
	@Override
	public Professor getProfessorById(Long profId) {
		return (Professor) getSession().get(Professor.class, profId);
	}
	
}
