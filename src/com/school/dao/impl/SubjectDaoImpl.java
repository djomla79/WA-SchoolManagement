package com.school.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.school.beans_model.Professor;
import com.school.beans_model.Student;
import com.school.beans_model.Subject;
import com.school.dao.generic.GenericDaoImpl;
import com.school.dao.interfaces.SubjectDao;

@Repository
public class SubjectDaoImpl extends GenericDaoImpl<Subject, Long> implements SubjectDao {

	public SubjectDaoImpl() {
		super(Subject.class);
	}
	
	@Override
	public Subject saveSubject(Subject subject) {
		super.saveEntity(subject);
		return subject;
	}
	
	@Override
	public Subject updateSubject(Subject subject) {
		super.updateEntity(subject);
		return subject;
	}

	@Override
	public Subject getSubjectByName(String subjectName) {
		Criteria criteria = getSession().createCriteria(Subject.class);
		criteria.add(Restrictions.eq("subjectName", subjectName));
		Subject subject = (Subject) criteria.uniqueResult();
		return subject;
	}
	
	@Override
	public Subject getSubjectWithStudents(String subjectName) {
		Subject subject = getSubjectByName(subjectName);
		Hibernate.initialize(subject.getStudents());
		return subject;
	}
	
	@Override
	public Subject getSubjectWithProfessor(Long subjectId) {
		Subject subject = getSubjectById(subjectId);
		Hibernate.initialize(subject.getProfessor());
		return subject;
	}

	@Override
	public Subject getSubjectWithStudentsById(Long id) {
		Subject subject = getSubjectById(id);
		Hibernate.initialize(subject.getStudents());
		return subject;
	}
	
	@Override
	public void addProfessorToSubjectById(Professor prof, Long subjectId) {
		Subject subject = getSubjectById(subjectId);
		subject.setProfessor(prof);
		saveSubject(subject);
	}
	
	@Override
	public Subject getSubjectById(Long id) {
		return (Subject) getSession().get(Subject.class, id);
	}

	@Override
	public void addStudentToSubject(Student student, Long subjectId) {
		Subject subject = getSubjectById(subjectId);
		subject.getStudents().add(student);
		updateSubject(subject);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjectsNotOfThisStudent(Student student) {
		String hql = "SELECT subject.* FROM Subject subject WHERE id NOT IN (SELECT subject_id FROM student_subject WHERE student_id=:studentId)";
		Query query = getSession().createSQLQuery(hql).addEntity(Subject.class);
		query.setLong("studentId", student.getId());
		return query.list();
	}
}