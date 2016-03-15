package com.school.dao.interfaces;

import com.school.beans_model.Absence;
import com.school.beans_model.Student;
import com.school.beans_model.Subject;
import com.school.dao.generic.GenericDao;

public interface AbsenceDao extends GenericDao<Absence, Long> {

	Long getStudentAbsences(Student student, Subject subject);
	
}
