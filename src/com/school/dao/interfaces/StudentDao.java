package com.school.dao.interfaces;

//import java.util.List;

import com.school.beans_model.Absence;
import com.school.beans_model.Grade;
import com.school.beans_model.Student;
import com.school.beans_model.Subject;
import com.school.dao.generic.GenericDao;

public interface StudentDao extends GenericDao<Student, Long> {
	
	Student saveStudent(Student student);
	
	Student updateStudent(Student student);
	
	Student getStudentByUsername(String username);

	Student getStudentWithSubjects(String username);

	Student getStudentWithGrades(String username);

	Student getStudentWithSubjectsAndGrades(String username);

	boolean isStudentExists(String username);

	Student getStudentWithSubjectsById(Long studentId);

	Student getStudentById(Long studentId);

	void addSubjectToStudent(Long studentId, Subject subject);

	Student mergeStudent(Student student);

	void addSubjectToStudentByRequestId(Long subjectRequestId);

	Student getStudentWithRequestsById(Long studentId);

	Student getStudentWithGradesById(Long studentId);

	Student getStudentWithSubjectsAndGradesById(Long studentId);

	void addAbsenceToStudent(Long studentId, Absence absence);

	void addGradeToStudent(Long studentId, Grade grade);

	void removeSubjectRequestByRequestId(Long subjectRequestId);

	//Student getStudentWithAbsencesBySubject(Subject subject);

	//List<Grade> getListOfGradesBySubjectAndStudent(Subject subject, Student student);
	
}
