package com.school.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.beans_model.Absence;
import com.school.beans_model.Student;
import com.school.beans_model.Subject;
import com.school.dao.generic.GenericDaoImpl;
import com.school.dao.interfaces.AbsenceDao;

@Repository
@Transactional
public class AbsenceDaoImpl extends GenericDaoImpl<Absence, Long> implements AbsenceDao {
	
	/** constructor */
	public AbsenceDaoImpl() {
		super(Absence.class);
	}
	/** return count of all absenceCounter from Absence class,
	 *  where student's subject-id and student-id matches one in Absence class */
	@Override
	public Long getStudentAbsences(Student student, Subject subject) {
		String hql = "SELECT count(absenceCounter) FROM Absence WHERE student=:studentId AND subject=:subjectId";
		Query query = getSession().createQuery(hql);
		query.setLong("studentId", student.getId());
		query.setLong("subjectId", subject.getId());
		return (long) query.uniqueResult();
	}
	
}
