package com.school.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.beans_model.Grade;
import com.school.beans_model.Student;
import com.school.beans_model.Subject;
import com.school.dao.generic.GenericDaoImpl;
import com.school.dao.interfaces.GradeDao;

@Repository
@Transactional
public class GradeDaoImpl extends GenericDaoImpl<Grade, Long> implements GradeDao {
	
	private static final String STUDENT_ID = "studentId";
	private static final String SUBJECT_ID = "subjectId";
	
	/** constructor */
	public GradeDaoImpl() {
		super(Grade.class);
	}
	/** if it's not null return total average of gradeValue from Grade class,
	 *  otherwise returns value double 0.0, 
	 *  where student's student-id matches one in Grade class */
	@Override
	public Double getStudentTotalAverageGradesById(Student student) {
		String hql = "SELECT avg(grade.gradeValue) FROM Grade grade WHERE grade.student=:studentId";
		Query query = getSession().createQuery(hql);
		query.setLong(STUDENT_ID, student.getId());
		/** If it's not checked throws NullPointerException */
		return query.uniqueResult() != null ? (double) query.uniqueResult() : 0.0;
	}
	/** if it's not null return average gradeValue for a subject from Grade class,
	 *  otherwise returns value double 0.0, 
	 *  where student's student-id and subject-id match ones in Grade class */
	@Override
	public Double getStudentSubjectAverageGradesById(Student student, Subject subject) {
		String hql = "SELECT avg(grade.gradeValue) FROM Grade grade WHERE grade.student=:studentId AND subject=:subjectId";
		Query query = getSession().createQuery(hql);
		query.setLong(STUDENT_ID, student.getId());
		query.setLong(SUBJECT_ID, subject.getId());
		/** If it's not checked throws NullPointerException */
		return query.uniqueResult() != null ? (double) query.uniqueResult() : 0.0;
	}
	/** return list of grades from Grade class,
	 *  where student's student-id matches one in Grade class */
	@SuppressWarnings("unchecked")
	@Override
	public List<Grade> getStudentGradesById(Student student) {
		String hql = "FROM Grade WHERE student=:studentId";
		Query query = getSession().createQuery(hql);
		query.setLong(STUDENT_ID, student.getId());
		return query.list();
	}
	/** return list of grades from Grade class,
	 *  where student's student-id and subject-id match ones in Grade class */
	@SuppressWarnings("unchecked")
	@Override
	public List<Grade> getStudentGradesByStudentAndSubjectId(Student student, Subject subject) {
		String hql = "FROM Grade WHERE student=:studentId AND subject=:subjectId";
		Query query = getSession().createQuery(hql);
		query.setLong(STUDENT_ID, student.getId());
		query.setLong(SUBJECT_ID, subject.getId());
		return query.list();
	}
	
}
