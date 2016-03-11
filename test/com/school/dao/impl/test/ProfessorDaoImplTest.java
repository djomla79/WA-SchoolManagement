package com.school.dao.impl.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.school.beans_model.Professor;
import com.school.beans_model.Subject;
import com.school.dao.interfaces.ProfessorDao;

public class ProfessorDaoImplTest {
	
	public static final Long PROFESSOR_ID = 1L;
	public static final String USERNAME = "username";
	
	@Mock
	ProfessorDao profDao;
	
	private Professor professor;
	private Professor professor1;
	private Subject subject;
	private List<Professor> listOfProfessors;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		subject = new Subject();
		professor = new Professor();
		professor1 = new Professor();
		listOfProfessors = Arrays.asList(professor, professor1);
	}
	
	@Test
	public void saveProfessor() throws Exception {
		doReturn(professor1).when(profDao).saveProfessor(professor);
		assertEquals(professor1, profDao.saveProfessor(professor));
		verify(profDao).saveProfessor(professor);
	}
	
	@Test
	public void updateProfessor() throws Exception {
		doReturn(professor1).when(profDao).updateProfessor(professor);
		assertEquals(professor1, profDao.updateProfessor(professor));
		verify(profDao).updateProfessor(professor);
	}
	
	@Test
	public void getProfessorById() throws Exception {
		doReturn(professor1).when(profDao).getProfessorById(PROFESSOR_ID);
		assertEquals(professor1, profDao.getProfessorById(PROFESSOR_ID));
		verify(profDao).getProfessorById(PROFESSOR_ID);
	}
	
	@Test
	public void getProfessorByUsername() throws Exception {
		doReturn(professor1).when(profDao).getProfessorByUsername(USERNAME);
		assertEquals(professor1, profDao.getProfessorByUsername(USERNAME));
		verify(profDao).getProfessorByUsername(USERNAME);
	}
	
	@Test
	public void getProfessorByUsernameWithSubjects() throws Exception {
		doReturn(professor1).when(profDao).getProfessorByUsernameWithSubjects(USERNAME);
		assertEquals(professor1, profDao.getProfessorByUsernameWithSubjects(USERNAME));
		verify(profDao).getProfessorByUsernameWithSubjects(USERNAME);
	}
	
	@Test
	public void getAllProfessorsWithSubjects() throws Exception {
		doReturn(listOfProfessors).when(profDao).getAllProfessorsWithSubjects();
		assertEquals(listOfProfessors, profDao.getAllProfessorsWithSubjects());
		verify(profDao).getAllProfessorsWithSubjects();
	}
	
	@Test
	public void getProfessorWithSubjectsById() throws Exception {
		doReturn(professor1).when(profDao).getProfessorWithSubjectsById(PROFESSOR_ID);
		assertEquals(professor1, profDao.getProfessorWithSubjectsById(PROFESSOR_ID));
		verify(profDao).getProfessorWithSubjectsById(PROFESSOR_ID);
	}
	
	@Test
	public void addSubjectToProfessorByUsername() throws Exception {
		profDao.addSubjectToProfessorByUsername(USERNAME, subject);
		verify(profDao).addSubjectToProfessorByUsername(USERNAME, subject);;
	}
	
	@Test
	public void addSubjectToProfessorById() throws Exception {
		profDao.addSubjectToProfessorById(PROFESSOR_ID, subject);
		verify(profDao).addSubjectToProfessorById(PROFESSOR_ID, subject);;
	}
}
