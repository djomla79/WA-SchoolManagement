package com.school.dao.impl.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.school.beans_model.Professor;
import com.school.beans_model.Student;
import com.school.beans_model.Subject;
import com.school.dao.impl.StudentDaoImpl;
import com.school.dao.impl.SubjectDaoImpl;

public class SubjectDaoImplTest {
	
	public static final Long SUBJECT_ID = 3L;
	public static final String SUBJECT_NAME = "subjectName";
	
	@Mock
	StudentDaoImpl studentDao;
	@Mock
	SubjectDaoImpl subjectDao;
	
	private Student student;
	private Subject subject;
	private Subject subject1;
	private Professor professor;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		student = new Student();
		subject = new Subject();
		subject1 = new Subject();
		professor = new Professor();
	}
	
	@Test
	public void saveSubject() throws Exception {
		doReturn(subject1).when(subjectDao).saveSubject(subject);
		assertEquals(subject1, subjectDao.saveSubject(subject));
		verify(subjectDao).saveSubject(subject);
	}
	
	@Test
	public void updateSubject() throws Exception {
		doReturn(subject1).when(subjectDao).updateSubject(subject);
		assertEquals(subject1, subjectDao.updateSubject(subject));
		verify(subjectDao).updateSubject(subject);
	}
	
	@Test
	public void getSubjectByName() throws Exception {
		doReturn(subject).when(subjectDao).getSubjectByName(SUBJECT_NAME);
		assertEquals(subject, subjectDao.getSubjectByName(SUBJECT_NAME));
		verify(subjectDao).getSubjectByName(SUBJECT_NAME);
	}
	
	@Test
	public void getSubjectWithStudents() throws Exception {
		doReturn(subject).when(subjectDao).getSubjectWithStudents(SUBJECT_NAME);
		assertEquals(subject, subjectDao.getSubjectWithStudents(SUBJECT_NAME));
		verify(subjectDao).getSubjectWithStudents(SUBJECT_NAME);
	}
	
	@Test
	public void getSubjectWithProfessor() throws Exception {
		doReturn(subject).when(subjectDao).getSubjectWithProfessor(SUBJECT_ID);
		assertEquals(subject, subjectDao.getSubjectWithProfessor(SUBJECT_ID));
		verify(subjectDao).getSubjectWithProfessor(SUBJECT_ID);
	}
	
	@Test
	public void getSubjectWithStudentsById() throws Exception {
		doReturn(subject).when(subjectDao).getSubjectWithStudentsById(SUBJECT_ID);
		assertEquals(subject, subjectDao.getSubjectWithStudentsById(SUBJECT_ID));
		verify(subjectDao).getSubjectWithStudentsById(SUBJECT_ID);
	}
	
	@Test
	public void addProfessorToSubjectById() throws Exception {
		subjectDao.addProfessorToSubjectById(professor, SUBJECT_ID);
		verify(subjectDao).addProfessorToSubjectById(professor, SUBJECT_ID);
	}
	
	@Test
	public void getSubjectById() throws Exception {
		doReturn(subject).when(subjectDao).get(SUBJECT_ID);
		assertEquals(subject, subjectDao.get(SUBJECT_ID));
	}
	
	@Test
	public void addStudentToSubject() throws Exception {
		subjectDao.addStudentToSubject(student, SUBJECT_ID);
		verify(subjectDao).addStudentToSubject(student, SUBJECT_ID);
	}
	
}
