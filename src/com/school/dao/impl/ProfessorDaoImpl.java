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
	
	/** constructor */
	public ProfessorDaoImpl() {
		super(Professor.class);
	}
	/** return professor that is saved
	 *  using save method from GenericDaoImpl class,
	 *  and using PasswordEncoder class to encode password */
	@Override
	public Professor saveProfessor(Professor prof) {
		prof.setEncodedPassword(encoder.encode(prof.getTransientPassword()));
		super.saveEntity(prof);
		return prof;
	}
	/** return professor that is updated 
	 *  using update method from GenericDaoImpl class */
	@Override
	public Professor updateProfessor(Professor prof) {
		super.updateEntity(prof);
		return prof;
	}
	/** return professor that matches
	 *  the one in Professor class by username */
	@Override
	public Professor getProfessorByUsername(String username) {
		Criteria criteria = getSession().createCriteria(Professor.class);
		criteria.add(Restrictions.eq("username", username));
		return (Professor) criteria.uniqueResult();
	}
	/** return professor with subjects that matches
	 *  the one in Professor class by username */
	@Override
	public Professor getProfessorByUsernameWithSubjects(String username) {
		Professor prof = getProfessorByUsername(username);
		Hibernate.initialize(prof.getSubjects());
		return prof;
	}
	/** return list of all professors with subjects */
	@Override
	public List<Professor> getAllProfessorsWithSubjects() {
		List<Professor> professors = (List<Professor>) getAll();
		for (Professor prof : professors) {
			Hibernate.initialize(prof.getSubjects());
		}
		return professors;
	}
	/** using param username, 
	 *  add subject to professor and update that professor */
	@Override
	public void addSubjectToProfessorByUsername(String username, Subject subject) {
		Professor professor = getProfessorByUsernameWithSubjects(username);
		professor.getSubjects().add(subject);	
		updateProfessor(professor);
	}
	/** using param id, 
	 *  add subject to professor and update that professor */
	@Override
	public void addSubjectToProfessorById(Long profId, Subject subject) {
		Professor professor = getProfessorWithSubjectsById(profId);
		professor.getSubjects().add(subject);	
		updateProfessor(professor);
	}
	/** return professor with subjects using param id */
	@Override
	public Professor getProfessorWithSubjectsById(Long profId) {
		Professor prof = getProfessorById(profId);
		Hibernate.initialize(prof.getSubjects());
		return prof;
	}
	/** return professor using param id */
	@Override
	public Professor getProfessorById(Long profId) {
		return (Professor) getSession().get(Professor.class, profId);
	}
	
}
