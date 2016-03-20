package com.school.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
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
	
	/** constructor */
	public StudentDaoImpl() {
		super(Student.class);
	}
	/** return student that is saved
	 *  using save method from GenericDaoImpl class,
	 *  and using PasswordEncoder class to encode password */
	@Override
	public Student saveStudent(Student student) {
		student.setEncodedPassword(encoder.encode(student.getTransientPassword()));
		super.saveEntity(student);
		return student;
	}
	/** return student that is updated 
	 *  using update method from GenericDaoImpl class */
	@Override
	public Student updateStudent(Student student) {
		super.updateEntity(student);
		return student;
	}
	/** return student that matches
	 *  the one in Student class by username */
	@Override
	public Student getStudentByUsername(String username) {
		Criteria criteria = getSession().createCriteria(Student.class);
		criteria.add(Restrictions.eq("username", username));
		return (Student) criteria.uniqueResult();
	}
	/** return student with subjects that matches
	 *  the one in Student class by username */
	@Override
	public Student getStudentWithSubjects(String username) {
		Student student = getStudentByUsername(username);
		Hibernate.initialize(student.getStudentSubjects());
		return student;
	}
	/** return student with grades that matches
	 *  the one in Student class by username */
	@Override
	public Student getStudentWithGrades(String username) {
		Student student = getStudentByUsername(username);
		Hibernate.initialize(student.getStudentGrades());
		return student;
	}
	/** return student with subjects and grades that matches
	 *  the one in Student class by username */
	@Override
	public Student getStudentWithSubjectsAndGrades(String username) {
		Student student = getStudentByUsername(username);
		Hibernate.initialize(student.getStudentSubjects());
		Hibernate.initialize(student.getStudentGrades());
		return student;
	}
	/** return student with subjects that matches
	 *  the one in Student class by id */
	@Override
	public Student getStudentWithSubjectsById(Long studentId) {
		Student student = getStudentById(studentId);
		Hibernate.initialize(student.getStudentSubjects());
		return student;
	}
	/** return student with grades that matches
	 *  the one in Student class by id */
	@Override
	public Student getStudentWithGradesById(Long studentId) {
		Student student = getStudentById(studentId);
		Hibernate.initialize(student.getStudentGrades());
		return student;
	}
	/** return student with subjects and grades that matches
	 *  the one in Student class by id */
	@Override
	public Student getStudentWithSubjectsAndGradesById(Long studentId) {
		Student student = getStudentById(studentId);
		Hibernate.initialize(student.getStudentSubjects());
		Hibernate.initialize(student.getStudentGrades());
		return student;
	}
	/** return student with requests that matches
	 *  the one in Student class by id */
	@Override
	public Student getStudentWithRequestsById(Long studentId) {
		Student student = getStudentById(studentId);
		Hibernate.initialize(student.getSubjectRequests());
		return student;
	}
	/** using param id, 
	 *  add subject to student and update that student */
	@Override
	public void addSubjectToStudent(Long studentId, Subject subject) {
		Student student = getStudentById(studentId);
		student.getStudentSubjects().add(subject);
		updateStudent(student);
	}
	/** get student and subject using param request-id,
	 *  and add subject to student, remove subject-request 
	 *  from student and update that student */
	@Override
	public void addSubjectToStudentByRequestId(Long subjectRequestId) {
		
		SubjectRequest subjectRequest = getSession().get(SubjectRequest.class, subjectRequestId);
		Student student = getSession().get(Student.class, subjectRequest.getStudentId());
		Subject subject = getSession().get(Subject.class, subjectRequest.getSubjectId());
		
		student.getStudentSubjects().add(subject);
		student.getSubjectRequests().remove(subjectRequest);
		updateStudent(student);
		
	}
	/** get subject-request by request-id 
	 *  get student by subject-request-id
	 *  remove subject-request from student and update that student */
	@Override
	public void removeSubjectRequestByRequestId(Long subjectRequestId) {
		
		SubjectRequest subjectRequest = getSession().get(SubjectRequest.class, subjectRequestId);
		Student student = getSession().get(Student.class, subjectRequest.getStudentId());
		student.getSubjectRequests().remove(subjectRequest);
		updateStudent(student);
		
	}
	/** get student by student-id, 
	 *  add absence to student and update that student */
	@Override
	public void addAbsenceToStudent(Long studentId, Absence absence) {
		Student student = getSession().get(Student.class, studentId);
		student.getStudentAbsences().add(absence);
		updateStudent(student);
	}
	/** get student by student-id. 
	 *  add grade to student and update that student */
	@Override
	public void addGradeToStudent(Long studentId, Grade grade) {
		Student student = getSession().get(Student.class, studentId);
		student.getStudentGrades().add(grade);
		updateStudent(student);
	}
	/** return student using param student-id */
	@Override
	public Student getStudentById(Long studentId) {
		return (Student) getSession().get(Student.class, studentId);
	}
	/** return student that is merged
	 *  using merge method from GenericDaoImpl class */
	@Override
	public Student mergeStudent(Student student) {
		super.mergeEntity(student);
		return student;
	}

}
