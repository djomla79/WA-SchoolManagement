package com.school.dao.impl.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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
	@Mock
	Subject subjectMock;
	
	private Student student;
	private Student student1;
	private Subject subject;
	private Subject subject1;
	private Professor professor;
	private List<Student> listOfStudents;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		student = new Student();
		student1 = new Student();
		subject = new Subject();
		subject1 = new Subject();
		professor = new Professor();
		listOfStudents = Arrays.asList(student, student1);
	}
	
	@Test
	public void saveSubject() throws Exception {
		doReturn(subject1).when(subjectDao).saveSubject(subject);
		assertEquals(subject1, subjectDao.saveSubject(subject));
	}
	
	@Test
	public void updateSubject() throws Exception {
		doReturn(subject1).when(subjectDao).updateSubject(subject);
		assertEquals(subject1, subjectDao.updateSubject(subject));
	}
	
	@Test(expected=NullPointerException.class)
	public void getSubjectByName() throws Exception {
		Criteria criteria = subjectDao.getSession().createCriteria(Subject.class).add(Restrictions.eq("subjectName", SUBJECT_NAME));
		doReturn(subject1).when((Subject)criteria.uniqueResult());
		assertEquals(subject1, (Subject)criteria.uniqueResult());
	}
	
	@Test
	public void getSubjectsWithStudents() throws Exception {
		doReturn(subject).when(subjectDao).getSubjectByName(SUBJECT_NAME);
		listOfStudents = subject.getStudents();
		assertEquals(subject, subjectDao.getSubjectByName(SUBJECT_NAME));
		assertEquals(listOfStudents, subjectMock.getStudents());
	}
	
	@Test
	public void getSubjectWithProfessor() throws Exception {
		doReturn(subject).when(subjectDao).getSubjectById(SUBJECT_ID);
		professor = subject.getProfessor();
		assertEquals(subject, subjectDao.getSubjectById(SUBJECT_ID));
		assertEquals(professor, subjectMock.getProfessor());
	}
	
	@Test
	public void getSubjectWithStudentsById() throws Exception {
		doReturn(subject).when(subjectDao).getSubjectById(SUBJECT_ID);
		listOfStudents = subject.getStudents();
		assertEquals(subject, subjectDao.getSubjectById(SUBJECT_ID));
		assertEquals(listOfStudents, subjectMock.getStudents());
	}
	
	@Test
	public void addProfessorToSubjectById() throws Exception {
		doReturn(subject).when(subjectDao).getSubjectById(SUBJECT_ID);
		subject.setProfessor(professor);
		subjectDao.saveSubject(subject);
	}
	
	@Test
	public void getSubjectById() throws Exception {
		doReturn(subject).when(subjectDao).get(SUBJECT_ID);
		assertEquals(subject, subjectDao.get(SUBJECT_ID));
	}
	
	@Test
	public void addStudentToSubject() throws Exception {
		doReturn(subject).when(subjectDao).getSubjectById(SUBJECT_ID);
		subject.getStudents().add(student);
		subjectDao.updateSubject(subject);
	}
	
}
