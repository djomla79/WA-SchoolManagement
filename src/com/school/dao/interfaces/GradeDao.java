package com.school.dao.interfaces;

import java.util.List;

import com.school.beans_model.Grade;
import com.school.beans_model.Student;
import com.school.beans_model.Subject;
import com.school.dao.generic.GenericDao;

public interface GradeDao extends GenericDao<Grade, Long> {
	
	/** abstract methods */
	List<Grade> getStudentGradesById(Student student);

	List<Grade> getStudentGradesByStudentAndSubjectId(Student student, Subject subject);

	Double getStudentTotalAverageGradesById(Student student);

	Double getStudentSubjectAverageGradesById(Student student, Subject subject);
	
}
