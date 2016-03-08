package com.school.dao.impl.test;

import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.school.beans_model.Absence;
import com.school.beans_model.Grade;
import com.school.beans_model.Student;
import com.school.beans_model.Subject;
import com.school.dao.impl.StudentDaoImpl;
import com.school.dao.impl.SubjectDaoImpl;

public class StudentDaoImplTest {
	
	public static final Long STUDENT_ID = 1L;
	public static final Long SUBJECT_ID = 3L;
	public static final Integer GRADE_VALUE = 10;
	public static final String PRINCIPAL_NAME = "name";
	
	@Mock
	StudentDaoImpl studentDao;
	@Mock
	SubjectDaoImpl subjectDao;
	@Mock
	Subject subjectMock;
	@Mock
	Student studentMock;
	
	private Student student;
	private Subject subject;
	private Absence absence;
	private Grade grade;
	private List<Subject> listOfSubjects;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		student = new Student();
		subject = new Subject();
		absence = new Absence();
		grade = new Grade();
		listOfSubjects = new ArrayList<>();
	}
	
	@Test
	public void getSubjectToStudent() throws Exception {
		
		studentDao.addSubjectToStudent(STUDENT_ID, subject);
		subjectDao.addStudentToSubject(student, SUBJECT_ID);
	}
	
	@Test
	public void addAbsenceToStudent() throws Exception {
		
		doReturn(subject).when(subjectDao).getSubjectById(SUBJECT_ID);
		(studentDao).addAbsenceToStudent(STUDENT_ID, absence);
	}
	
	@Test
	public void addGradeToStudent() throws Exception {
		
		doReturn(subject).when(subjectDao).getSubjectById(SUBJECT_ID);
		(studentDao).addGradeToStudent(STUDENT_ID, grade);
	}
	
	@Test
	public void sendSubjectRequest() throws Exception {
		
		doReturn(student).when(studentDao).getStudentWithRequestsById(STUDENT_ID);
		doReturn(subject).when(subjectDao).getSubjectById(SUBJECT_ID);
		
		studentDao.mergeStudent(student);
	}
	
	@Test
	public void accountStudent() throws Exception {
		
		doReturn(student).when(studentDao).getStudentWithSubjectsAndGrades(PRINCIPAL_NAME);
		doReturn(listOfSubjects).when(subjectDao).getAll();
	}
	
	@Test
	public void getSubjectWithStudents() throws Exception {
		
		doReturn(subject).when(subjectDao).getSubjectWithStudentsById(SUBJECT_ID);
		doReturn(listOfSubjects).when(subjectMock).getStudents();	
	}
	
	@Test
	public void getStudentWithSubjects() throws Exception {
		
		doReturn(student).when(studentDao).getStudentWithSubjectsById(STUDENT_ID);
		doReturn(listOfSubjects).when(studentMock).getStudentSubjects();
	}
	
}
