package com.school.controllers.test;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import com.school.beans_model.Professor;
import com.school.beans_model.Subject;
import com.school.config.RootConfig;
import com.school.controllers.ProfessorController;
import com.school.dao.interfaces.ProfessorDao;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes={RootConfig.class})
public class ProfessorControllerTest {

	public static final Long PROFESSOR_ID = 7L;
	public static final Long SUBJECT_ID = 10L;
	public static final String USERNAME = "username";
	
	@Mock
	ProfessorDao profDao;
	@Mock
	Professor professorMock;
	
	@InjectMocks
	ProfessorController controller;

	private MockMvc mockMvc;
	private Subject subject;
	private Subject subject1;
	private Professor professor;
	private List<Subject> listOfSubjects;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(controller).build();
		subject = new Subject();
		subject1 = new Subject();
		professor = new Professor();
		listOfSubjects = Arrays.asList(subject, subject1);
	}
	
	@Test(expected=NestedServletException.class)
	public void professorAccountProfessorController() throws Exception {
		
		doReturn(professor).when(profDao).getProfessorByUsernameWithSubjects(USERNAME);
		
		mockMvc.perform(post("/accountProf"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("profAccount"))
			   .andExpect(model().attribute("loggedProfessor", notNullValue()));
	}
	
	@Test
	public void getProfessorWithSubjectsProfessorController() throws Exception {
		
		doReturn(professor).when(profDao).getProfessorWithSubjectsById(PROFESSOR_ID);
		doReturn(listOfSubjects).when(professorMock).getSubjects();
		
		mockMvc.perform(post("/getProfessorWithSubjects/{profId}", PROFESSOR_ID))
			   .andExpect(view().name("professor"))
			   .andExpect(model().attribute("professor", notNullValue()))
			   .andExpect(model().attribute("subjects", hasSize(0)));
	}
	
	@Test(expected=NestedServletException.class)
	public void getSubjectProfessorController() throws Exception {
		
		mockMvc.perform(post("/getSubject/{subjectId}", SUBJECT_ID))
			   .andExpect(view().name("subjectInfo"))
			   .andExpect(model().attribute("subject", notNullValue()))
			   .andExpect(model().attribute("professor", notNullValue()));
	}
	
}
