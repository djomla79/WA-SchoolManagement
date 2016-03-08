package com.school.controllers.test;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

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

import com.school.beans_model.Subject;
import com.school.config.RootConfig;
import com.school.controllers.AdminController;
import com.school.dao.interfaces.SubjectDao;

@ContextConfiguration(classes = { RootConfig.class })
@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {

	public static final Long PROFESSOR_ID = 7L;
	public static final Long SUBJECT_ID = 10L;
	public static final Long SUBJECT_REQUEST_ID = 11L;

	@Mock
	SubjectDao subjectDao;

	@InjectMocks
	AdminController controller;

	private MockMvc mockMvc;
	
	private Subject subject;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(controller).build();
		subject = new Subject();
	}
	
	@Test(expected=NestedServletException.class)
	public void adminPage() throws Exception {
		
		mockMvc.perform(post("/admin"))
			   .andExpect(view().name("admin"))
			   .andExpect(model().attribute("professors", notNullValue()))
			   .andExpect(model().attribute("subjectRequests", notNullValue()));
	}
	
	@Test
	public void acceptSubjectRequest() throws Exception {
		
		mockMvc.perform(post("/acceptSubjectRequest/{subjectRequestId}", SUBJECT_REQUEST_ID));
			   //.andExpect(view().name("redirect:/admin"));
	}
	
	@Test
	public void declineSubjectRequest() throws Exception {
		
		mockMvc.perform(post("/declineSubjectRequest/{subjectRequestId}", SUBJECT_REQUEST_ID));
	}
	
	@Test
	public void addingSubject() throws Exception {
		
		mockMvc.perform(post("/addingSubject"))
			   .andExpect(view().name("addSubject"));
	}
	
	@Test
	public void addSubject() throws Exception {
		
		doReturn(subject).when(subjectDao).saveSubject((Subject) any());
		mockMvc.perform(post("/addSubject"));
	}
	
	@Test
	public void addSubjectToProf() throws Exception {
		
		mockMvc.perform(post("/addSubjectToProf"))
			   .andExpect(view().name("addSubjectsToProfessor"))
			   .andExpect(model().attribute("professors", notNullValue()))
			   .andExpect(model().attribute("subjects", notNullValue()));
	}
	
	@Test
	public void addSubjectToProfessor() throws Exception {
		
		mockMvc.perform(post("/addSubjectToProfessor, params=professor && subject", PROFESSOR_ID, SUBJECT_ID));
	}
	
}
