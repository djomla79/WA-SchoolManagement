package com.school.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.beans_model.Absence;
import com.school.beans_model.Grade;
import com.school.beans_model.Student;
import com.school.beans_model.Subject;
import com.school.beans_model.SubjectRequest;
import com.school.dao.generic.GenericDaoImpl;
import com.school.dao.interfaces.StudentDao;

@Repository
@Transactional
public class StudentDaoImpl extends GenericDaoImpl<Student, Long> implements StudentDao {

	public StudentDaoImpl() {
		super(Student.class);
	}

	@Override
	public Student saveStudent(Student student) {
		super.saveEntity(student);
		return student;
	}
	
	@Override
	public Student updateStudent(Student student) {
		super.updateEntity(student);
		return student;
	}
	
	@Override
	public Student getStudentByUsername(String username) {
		Criteria criteria = getSession().createCriteria(Student.class);
		criteria.add(Restrictions.eq("username", username));
		Student student = (Student) criteria.uniqueResult();
		return student;
	}
	
	@Override
	public Student getStudentWithSubjects(String username) {
		Student student = getStudentByUsername(username);
		Hibernate.initialize(student.getStudentSubjects());
		return student;
	}
	
	@Override
	public Student getStudentWithGrades(String username) {
		Student student = getStudentByUsername(username);
		Hibernate.initialize(student.getStudentGrades());
		return student;
	}
	
	@Override
	public Student getStudentWithSubjectsAndGrades(String username) {
		Student student = getStudentByUsername(username);
		Hibernate.initialize(student.getStudentSubjects());
		Hibernate.initialize(student.getStudentGrades());
		return student;
	}
	
	@Override
	public boolean isStudentExists(String username) {
		Criteria crit = getSession().createCriteria(Student.class);
		crit.add(Restrictions.eq("username", username));
		Student student = (Student) crit.uniqueResult();
		if (student != null) {
			return true;
		}
		return false;
	}

	@Override
	public Student getStudentWithSubjectsById(Long studentId) {
		Student student = getStudentById(studentId);
		Hibernate.initialize(student.getStudentSubjects());
		return student;
	}
	
	@Override
	public Student getStudentWithGradesById(Long studentId) {
		Student student = getStudentById(studentId);
		Hibernate.initialize(student.getStudentGrades());
		return student;
	}
	
	@Override
	public Student getStudentWithSubjectsAndGradesById(Long studentId) {
		Student student = getStudentById(studentId);
		Hibernate.initialize(student.getStudentSubjects());
		Hibernate.initialize(student.getStudentGrades());
		return student;
	}
	
	@Override
	public Student getStudentWithRequestsById(Long studentId) {
		Student student = getStudentById(studentId);
		Hibernate.initialize(student.getSubjectRequests());
		return student;
	}
	
	@Override
	public void addSubjectToStudent(Long studentId, Subject subject) {
		Student student = getStudentById(studentId);
		student.getStudentSubjects().add(subject);
		updateStudent(student);
	}
	
	@Override
	public void addSubjectToStudentByRequestId(Long subjectRequestId) {
		Session session = sessionFactory.openSession();
		Transaction txn = session.beginTransaction();
		
		SubjectRequest subjectRequest = session.get(SubjectRequest.class, subjectRequestId);
		Student student = session.get(Student.class, subjectRequest.getStudentId());
		Subject subject = session.get(Subject.class, subjectRequest.getSubjectId());
		
		student.getStudentSubjects().add(subject);
		student.getSubjectRequests().remove(subjectRequest);
		
		txn.commit();
		session.close();
	}
	
	@Override
	public void removeSubjectRequestByRequestId(Long subjectRequestId) {
		Session session = sessionFactory.openSession();
		Transaction txn = session.beginTransaction();
		
		SubjectRequest subjectRequest = session.get(SubjectRequest.class, subjectRequestId);
		Student student = session.get(Student.class, subjectRequest.getStudentId());
		
		student.getSubjectRequests().remove(subjectRequest);
		
		txn.commit();
		session.close();
	}
	
	@Override
	public void addAbsenceToStudent(Long studentId, Absence absence) {
		Student student = getSession().get(Student.class, studentId);
		student.getStudentAbsences().add(absence);
		updateStudent(student);
	}

	@Override
	public void addGradeToStudent(Long studentId, Grade grade) {
		Student student = getSession().get(Student.class, studentId);
		student.getStudentGrades().add(grade);
		updateStudent(student);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Grade> getGradesBySubjectAndStudent(Student student, Subject subject) {
		String hql = "SELECT s FROM Student s , in (s.studentGrades) g WHERE g.subject= :subjectId AND g.student =:studentId";
		Query query = getSession().createQuery(hql);
		query.setLong("subjectId", subject.getId());
		query.setLong("studentId", student.getId());
		return query.list();
	}
	
	@Override
	public Student getStudentById(Long studentId) {
		return (Student) getSession().get(Student.class, studentId);
	}

	@Override
	public Student mergeStudent(Student student) {
		super.mergeEntity(student);
		return student;
	}

}
