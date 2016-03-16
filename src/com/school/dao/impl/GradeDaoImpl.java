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

	public GradeDaoImpl() {
		super(Grade.class);
	}
	
	@Override
	public Double getStudentTotalAverageGradesById(Student student) {
		String hql = "SELECT avg(grade.gradeValue) FROM Grade grade WHERE grade.student=:studentId";
		Query query = getSession().createQuery(hql);
		query.setLong("studentId", student.getId());
		/** If it's not checked throws NullPointerException */
		return query.uniqueResult() != null ? (double) query.uniqueResult() : 0.0;
	}
	
	@Override
	public Double getStudentSubjectAverageGradesById(Student student, Subject subject) {
		String hql = "SELECT avg(grade.gradeValue) FROM Grade grade WHERE grade.student=:studentId AND subject=:subjectId";
		Query query = getSession().createQuery(hql);
		query.setLong("studentId", student.getId());
		query.setLong("subjectId", subject.getId());
		/** If it's not checked throws NullPointerException */
		return query.uniqueResult() != null ? (double) query.uniqueResult() : 0.0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Grade> getStudentGradesById(Student student) {
		String hql = "FROM Grade WHERE student=:studentId";
		Query query = getSession().createQuery(hql);
		query.setLong("studentId", student.getId());
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Grade> getStudentGradesByStudentAndSubjectId(Student student, Subject subject) {
		String hql = "FROM Grade WHERE student=:studentId AND subject=:subjectId";
		Query query = getSession().createQuery(hql);
		query.setLong("studentId", student.getId());
		query.setLong("subjectId", subject.getId());
		return query.list();
	}
	
}
