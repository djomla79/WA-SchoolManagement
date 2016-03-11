package com.school.dao.impl.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.school.beans_model.Absence;
import com.school.beans_model.Grade;
import com.school.beans_model.Student;
import com.school.beans_model.Subject;
import com.school.beans_model.SubjectRequest;
import com.school.dao.impl.StudentDaoImpl;
import com.school.dao.impl.SubjectDaoImpl;
import com.school.dao.impl.SubjectRequestDaoImpl;

public class StudentDaoImplTest {
	
	public static final Long STUDENT_ID = 1L;
	public static final Long SUBJECT_ID = 3L;
	public static final Long REQUEST_ID = 5L;
	public static final Integer GRADE_VALUE = 10;
	public static final String USERNAME = "name";
	
	@Mock
	StudentDaoImpl studentDao;
	@Mock
	SubjectDaoImpl subjectDao;
	@Mock
	SubjectRequestDaoImpl requestDao;
	@Mock
	Subject subjectMock;
	@Mock
	Student studentMock;
	
	private Student student;
	private Student student1;
	private Subject subject;
	private Subject subject1;
	private Absence absence;
	private Grade grade;
	private Grade grade1;
	private SubjectRequest request;
	private SubjectRequest request1;
	private List<Subject> listOfSubjects;
	private List<Student> listOfStudents;
	private List<Grade> listOfGrades;
	private List<SubjectRequest> listOfRequests;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		student = new Student();
		student1 = new Student();
		subject = new Subject();
		subject1 = new Subject();
		absence = new Absence();
		grade = new Grade();
		grade1 = new Grade();
		request = new SubjectRequest();
		request1 = new SubjectRequest();
		listOfSubjects = Arrays.asList(subject, subject1);
		listOfStudents = Arrays.asList(student, student1);
		listOfGrades = Arrays.asList(grade, grade1);
		listOfRequests = Arrays.asList(request, request1);
	}
	
	@Test
	public void saveStudent() throws Exception {
		studentDao.saveStudent(student);
	}
	
	@Test
	public void updateStudent() throws Exception {
		doReturn(student1).when(studentDao).updateStudent(student);
		assertEquals(student1, studentDao.updateStudent(student));
	}
	
	@Test
	public void getStudentByUsername() throws Exception {
		doReturn(student1).when(studentDao).getStudentByUsername(USERNAME);
		assertEquals(student1, studentDao.getStudentByUsername(USERNAME));
	}
	
	@Test
	public void getStudentWithSubjectsByUsername() throws Exception {
		doReturn(student1).when(studentDao).getStudentByUsername(USERNAME);
		listOfSubjects = student1.getStudentSubjects();
		assertEquals(student1, studentDao.getStudentByUsername(USERNAME));
		assertEquals(listOfSubjects, studentMock.getStudentSubjects());
	}
	
	@Test
	public void getStudentWithGradesByUsername() throws Exception {
		doReturn(student1).when(studentDao).getStudentByUsername(USERNAME);
		listOfGrades = student1.getStudentGrades();
		assertEquals(student1, studentDao.getStudentByUsername(USERNAME));
		assertEquals(listOfGrades, studentMock.getStudentGrades());
	}
	
	@Test
	public void getStudentWithGradesById() throws Exception {
		doReturn(student1).when(studentDao).getStudentWithGradesById(STUDENT_ID);
		listOfGrades= student1.getStudentGrades();
		assertEquals(student1, studentDao.getStudentWithGradesById(STUDENT_ID));
		assertEquals(listOfGrades, studentMock.getStudentGrades());
	}
	
	@Test
	public void getStudentWithSubjectsAndGradesByUsername() throws Exception {
		doReturn(student1).when(studentDao).getStudentByUsername(USERNAME);
		listOfGrades = student1.getStudentGrades();
		listOfSubjects = student1.getStudentSubjects();
		assertEquals(student1, studentDao.getStudentByUsername(USERNAME));
		assertEquals(listOfGrades, studentMock.getStudentGrades());
		assertEquals(listOfSubjects, studentMock.getStudentSubjects());
	}
	
	@Test
	public void getStudentWithSubjectsAndGradesById() throws Exception {
		doReturn(student1).when(studentDao).getStudentById(STUDENT_ID);
		listOfGrades = student1.getStudentGrades();
		listOfSubjects = student1.getStudentSubjects();
		assertEquals(student1, studentDao.getStudentById(STUDENT_ID));
		assertEquals(listOfGrades, studentMock.getStudentGrades());
		assertEquals(listOfSubjects, studentMock.getStudentSubjects());
	}
	
	@Test
	public void addAbsenceToStudent() throws Exception {
		doReturn(student).when(studentDao).get(STUDENT_ID);
		studentMock.getStudentAbsences().add(absence);
		studentDao.updateStudent(student);
	}
	
	@Test
	public void addGradeToStudent() throws Exception {
		doReturn(student).when(studentDao).get(STUDENT_ID);
		studentMock.getStudentGrades().add(grade);
		studentDao.updateStudent(student);
	}
	
	@Test
	public void mergeStudent() throws Exception {
		doReturn(student).when(studentDao).mergeStudent(student);
		assertEquals(student, studentDao.mergeStudent(student));
	}
	
	@Test
	public void getSubjectWithStudentsById() throws Exception {
		doReturn(subject).when(subjectDao).getSubjectWithStudentsById(SUBJECT_ID);
		listOfStudents = subject.getStudents();
		assertEquals(subject, subjectDao.getSubjectWithStudentsById(SUBJECT_ID));
		assertEquals(listOfStudents, subjectMock.getStudents());
	}
	
	@Test
	public void getStudentWithSubjectsById() throws Exception {
		doReturn(student).when(studentDao).getStudentWithSubjectsById(STUDENT_ID);
		listOfSubjects = student.getStudentSubjects();
		assertEquals(student, studentDao.getStudentWithSubjectsById(STUDENT_ID));
		assertEquals(listOfSubjects, studentMock.getStudentSubjects());
	}
	
	@Test
	public void getStudentWithRequestsById() throws Exception {
		doReturn(student).when(studentDao).getStudentById(STUDENT_ID);
		listOfRequests = student.getSubjectRequests();
		assertEquals(student, studentDao.getStudentById(STUDENT_ID));
		assertEquals(listOfRequests, studentMock.getSubjectRequests());
	}
	
	@Test
	public void addSubjectToStudent() throws Exception {
		doReturn(student).when(studentDao).getStudentById(STUDENT_ID);
		student.getStudentSubjects().add(subject);
		studentDao.updateStudent(student);
	}
	
	@Test
	public void addSubjectToStudentByRequestId() throws Exception {
		doReturn(request).when(requestDao).get(REQUEST_ID);
		doReturn(student).when(studentDao).get(STUDENT_ID);
		doReturn(subject).when(subjectDao).get(SUBJECT_ID);
		student.getStudentSubjects().add(subject);
		student.getSubjectRequests().remove(request);
	}
	
	@Test
	public void removeSubjectRequestById() throws Exception {
		doReturn(request).when(requestDao).get(REQUEST_ID);
		doReturn(student).when(studentDao).get(STUDENT_ID);
		student.getSubjectRequests().remove(request);
	}
	
}
