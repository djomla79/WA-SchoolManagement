package com.school.dao.impl.test;

import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.school.beans_model.Professor;
import com.school.beans_model.Subject;
import com.school.dao.interfaces.ProfessorDao;
import com.school.dao.interfaces.SubjectDao;

public class ProfessorDaoImplTest {
	
	public static final Long PROFESSOR_ID = 1L;
	public static final Long SUBJECT_ID = 3L;
	public static final String PRINCIPAL_NAME = "name";
	
	@Mock
	ProfessorDao profDao;
	@Mock
	SubjectDao subjectDao;
	@Mock
	Professor profMock;
	@Mock
	Subject subjectMock;
	
	private Professor professor;
	private Subject subject;
	private List<Subject> listOfSubjects;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		subject = new Subject();
		professor = new Professor();
		listOfSubjects = new ArrayList<>();
	}
	
	@Test
	public void accountProf() throws Exception {
		
		doReturn(professor).when(profDao).getProfessorByUsernameWithSubjects(PRINCIPAL_NAME);
	}
	
	@Test
	public void getProfessorWithSubject() throws Exception {
		
		doReturn(professor).when(profDao).getProfessorWithSubjectsById(PROFESSOR_ID);
		doReturn(listOfSubjects).when(profMock).getSubjects();
	}
	
	@Test
	public void getSubject() throws Exception {
		
		doReturn(subject).when(subjectDao).getSubjectWithProfessor(SUBJECT_ID);
		doReturn(professor).when(subjectMock).getProfessor();
	}
	
}
