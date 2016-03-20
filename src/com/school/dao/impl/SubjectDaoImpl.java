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
	
	/** constructor */
	public SubjectDaoImpl() {
		super(Subject.class);
	}
	/** return subject that is saved
	 *  using save method from GenericDaoImpl class */
	@Override
	public Subject saveSubject(Subject subject) {
		super.saveEntity(subject);
		return subject;
	}
	/** return subject that is updated 
	 *  using update method from GenericDaoImpl class */
	@Override
	public Subject updateSubject(Subject subject) {
		super.updateEntity(subject);
		return subject;
	}
	/** return subject that matches
	 *  the one in Subject class by username */
	@Override
	public Subject getSubjectByName(String subjectName) {
		Criteria criteria = getSession().createCriteria(Subject.class);
		criteria.add(Restrictions.eq("subjectName", subjectName));
		return (Subject) criteria.uniqueResult();
	}
	/** return subject with subjects that matches
	 *  the one in Subject class by username */
	@Override
	public Subject getSubjectWithStudents(String subjectName) {
		Subject subject = getSubjectByName(subjectName);
		Hibernate.initialize(subject.getStudents());
		return subject;
	}
	/** return subject with professor that matches
	 *  the one in Subject class by id */
	@Override
	public Subject getSubjectWithProfessor(Long subjectId) {
		Subject subject = getSubjectById(subjectId);
		Hibernate.initialize(subject.getProfessor());
		return subject;
	}
	/** return subject with students that matches
	 *  the one in Subject class by id */
	@Override
	public Subject getSubjectWithStudentsById(Long id) {
		Subject subject = getSubjectById(id);
		Hibernate.initialize(subject.getStudents());
		return subject;
	}
	/** using param id, 
	 *  add professor to subject and save that subject */
	@Override
	public void addProfessorToSubjectById(Professor prof, Long subjectId) {
		Subject subject = getSubjectById(subjectId);
		subject.setProfessor(prof);
		saveSubject(subject);
	}
	/** return subject that matches
	 *  the one in Subject class by id */
	@Override
	public Subject getSubjectById(Long id) {
		return (Subject) getSession().get(Subject.class, id);
	}
	/** using param id, 
	 *  add student to subject and update that subject */
	@Override
	public void addStudentToSubject(Student student, Long subjectId) {
		Subject subject = getSubjectById(subjectId);
		subject.getStudents().add(student);
		updateSubject(subject);
	}
	/** return list of all subjects from Subject class
	 *  that are not in table where student-id matches with one in that table */
	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjectsNotOfThisStudent(Student student) {
		String hql = "SELECT subject.* FROM Subject subject WHERE id NOT IN (SELECT subject_id FROM student_subject WHERE student_id=:studentId)";
		Query query = getSession().createSQLQuery(hql).addEntity(Subject.class);
		query.setLong("studentId", student.getId());
		return query.list();
	}
}