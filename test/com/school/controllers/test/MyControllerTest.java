package com.school.controllers.test;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
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

import com.school.beans_model.Admin;
import com.school.beans_model.Professor;
import com.school.beans_model.Student;
import com.school.config.RootConfig;
import com.school.controllers.MyController;
import com.school.dao.interfaces.AdminDao;
import com.school.dao.interfaces.ProfessorDao;
import com.school.dao.interfaces.StudentDao;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes={RootConfig.class})
public class MyControllerTest {
	
	@Mock
	ProfessorDao profDao;
	@Mock
	AdminDao adminDao;
	@Mock
	StudentDao studentDao;
	
	@InjectMocks
	MyController controller;
	
	private MockMvc mockMvc;
	private Admin admin;
	private Professor professor;
	private Student student;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(controller).build();
		admin = new Admin();
		professor = new Professor();
		student = new Student();
	}
	
	@Test
	public void homePage() throws Exception {
		mockMvc.perform(get("/"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("home"));
	}
	
	@Test
	public void registrationPage() throws Exception {
		mockMvc.perform(get("/registerUser"))
			   .andExpect(view().name("registrationPage"))
			   .andExpect(model().attribute("user", notNullValue()));
	}
	
	@Test
	public void registerAdmin() throws Exception {
		doReturn(admin).when(adminDao).saveAdmin((Admin) any());
		mockMvc.perform(post("/registerAdmin"))
			   .andExpect(view().name("redirect:/admin"));
	}
	
	@Test
	public void registerProfessor() throws Exception {
		doReturn(professor).when(profDao).saveProfessor((Professor) any());
		mockMvc.perform(post("/registerProfessor"))
			   .andExpect(view().name("redirect:/admin"));
	}
	
	@Test
	public void registerStudent() throws Exception {
		doReturn(student).when(studentDao).saveStudent((Student) any());
		mockMvc.perform(post("/registerStudent"))
			   .andExpect(view().name("redirect:/admin"));
	}
	
}
