package com.school.controllers.test;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import com.school.config.RootConfig;
import com.school.controllers.ProfessorController;

@ContextConfiguration(classes= { RootConfig.class })
@RunWith(MockitoJUnitRunner.class)
public class ProfessorControllerTest {

	public static final Long PROFESSOR_ID = 7L;
	public static final Long SUBJECT_ID = 10L;

	@InjectMocks
	ProfessorController controller;

	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(controller).build();
	}
	
	@Test(expected=NestedServletException.class)
	public void professorAccountProfessorController() throws Exception {
		
		mockMvc.perform(post("/accountProf"))
			   .andExpect(view().name("profAccount"))
			   .andExpect(model().attribute("loggedProfessor", notNullValue()));
	}
	
	@Test(expected=NestedServletException.class)
	public void getProfessorWithSubjectsProfessorController() throws Exception {
		
		mockMvc.perform(post("/getProfessorWithSubjects/{profId}", PROFESSOR_ID))
			   .andExpect(view().name("professor"))
			   .andExpect(model().attribute("professor", notNullValue()))
			   .andExpect(model().attribute("subjects", notNullValue()));
	}
	
	@Test(expected=NestedServletException.class)
	public void getSubjectProfessorController() throws Exception {
		
		mockMvc.perform(post("/getSubject/{subjectId}", SUBJECT_ID))
			   .andExpect(view().name("subjectInfo"))
			   .andExpect(model().attribute("subject", notNullValue()))
			   .andExpect(model().attribute("professor", notNullValue()));
	}
	
}
