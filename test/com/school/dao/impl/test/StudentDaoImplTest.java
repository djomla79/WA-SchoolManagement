package com.school.dao.impl.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
import com.school.dao.impl.SubjectRequestDaoImpl;

public class StudentDaoImplTest {
	
	public static final Long STUDENT_ID = 1L;
	public static final Long SUBJECT_ID = 3L;
	public static final Long REQUEST_ID = 5L;
	public static final String USERNAME = "username";
	
	@Mock
	StudentDaoImpl studentDao;
	@Mock
	SubjectDaoImpl subjectDao;
	@Mock
	SubjectRequestDaoImpl requestDao;
	
	private Student student;
	private Student student1;
	private Subject subject;
	private Absence absence;
	private Grade grade;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		student = new Student();
		student1 = new Student();
		subject = new Subject();
		absence = new Absence();
		grade = new Grade();
	}
	
	@Test
	public void saveStudent() throws Exception {
		studentDao.saveStudent(student);
		verify(studentDao).saveStudent(student);
	}
	
	@Test
	public void updateStudent() throws Exception {
		doReturn(student1).when(studentDao).updateStudent(student);
		assertEquals(student1, studentDao.updateStudent(student));
		verify(studentDao).updateStudent(student);
	}
	
	@Test
	public void getStudentByUsername() throws Exception {
		doReturn(student).when(studentDao).getStudentByUsername(USERNAME);
		assertEquals(student, studentDao.getStudentByUsername(USERNAME));
		verify(studentDao).getStudentByUsername(USERNAME);
	}
	
	@Test
	public void getStudentWithSubjectsByUsername() throws Exception {
		doReturn(student).when(studentDao).getStudentWithSubjects(USERNAME);
		assertEquals(student, studentDao.getStudentWithSubjects(USERNAME));
		verify(studentDao).getStudentWithSubjects(USERNAME);
	}
	
	@Test
	public void getStudentWithGradesByUsername() throws Exception {
		doReturn(student).when(studentDao).getStudentWithGrades(USERNAME);
		assertEquals(student, studentDao.getStudentWithGrades(USERNAME));
		verify(studentDao).getStudentWithGrades(USERNAME);
	}
	
	@Test
	public void getStudentWithGradesById() throws Exception {
		doReturn(student).when(studentDao).getStudentWithGradesById(STUDENT_ID);
		assertEquals(student, studentDao.getStudentWithGradesById(STUDENT_ID));
		verify(studentDao).getStudentWithGradesById(STUDENT_ID);
	}
	
	@Test
	public void getStudentWithSubjectsAndGradesByUsername() throws Exception {
		doReturn(student).when(studentDao).getStudentWithSubjectsAndGrades(USERNAME);
		assertEquals(student, studentDao.getStudentWithSubjectsAndGrades(USERNAME));
		verify(studentDao).getStudentWithSubjectsAndGrades(USERNAME);
	}
	
	@Test
	public void getStudentWithSubjectsAndGradesById() throws Exception {
		doReturn(student).when(studentDao).getStudentWithSubjectsAndGradesById(STUDENT_ID);
		assertEquals(student, studentDao.getStudentWithSubjectsAndGradesById(STUDENT_ID));
		verify(studentDao).getStudentWithSubjectsAndGradesById(STUDENT_ID);
	}
	
	@Test
	public void addAbsenceToStudent() throws Exception {
		studentDao.addAbsenceToStudent(STUDENT_ID, absence);
		verify(studentDao).addAbsenceToStudent(STUDENT_ID, absence);
	}
	
	@Test
	public void addGradeToStudent() throws Exception {
		studentDao.addGradeToStudent(STUDENT_ID, grade);
		verify(studentDao).addGradeToStudent(STUDENT_ID, grade);
	}
	
	@Test
	public void mergeStudent() throws Exception {
		doReturn(student).when(studentDao).mergeStudent(student);
		assertEquals(student, studentDao.mergeStudent(student));
		verify(studentDao).mergeStudent(student);
	}
	
	@Test
	public void getSubjectWithStudentsById() throws Exception {
		doReturn(subject).when(subjectDao).getSubjectWithStudentsById(SUBJECT_ID);
		assertEquals(subject, subjectDao.getSubjectWithStudentsById(SUBJECT_ID));
		verify(subjectDao).getSubjectWithStudentsById(SUBJECT_ID);
	}
	
	@Test
	public void getStudentWithSubjectsById() throws Exception {
		doReturn(student).when(studentDao).getStudentWithSubjectsById(STUDENT_ID);
		assertEquals(student, studentDao.getStudentWithSubjectsById(STUDENT_ID));
		verify(studentDao).getStudentWithSubjectsById(STUDENT_ID);
	}
	
	@Test
	public void getStudentWithRequestsById() throws Exception {
		doReturn(student).when(studentDao).getStudentWithRequestsById(STUDENT_ID);
		assertEquals(student, studentDao.getStudentWithRequestsById(STUDENT_ID));
		verify(studentDao).getStudentWithRequestsById(STUDENT_ID);
	}
	
	@Test
	public void addSubjectToStudent() throws Exception {
		studentDao.addSubjectToStudent(STUDENT_ID, subject);
		verify(studentDao).addSubjectToStudent(STUDENT_ID, subject);
	}
	
	@Test
	public void addSubjectToStudentByRequestId() throws Exception {
		studentDao.addSubjectToStudentByRequestId(REQUEST_ID);
		verify(studentDao).addSubjectToStudentByRequestId(REQUEST_ID);
	}
	
	@Test
	public void removeSubjectRequestById() throws Exception {
		studentDao.removeSubjectRequestByRequestId(REQUEST_ID);
		verify(studentDao).removeSubjectRequestByRequestId(REQUEST_ID);
	}
	
}
