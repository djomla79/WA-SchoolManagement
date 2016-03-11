package com.school.controllers.test;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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

import com.school.beans_model.Admin;
import com.school.beans_model.Professor;
import com.school.beans_model.Subject;
import com.school.beans_model.SubjectRequest;
import com.school.config.RootConfig;
import com.school.controllers.AdminController;
import com.school.dao.interfaces.AdminDao;
import com.school.dao.interfaces.ProfessorDao;
import com.school.dao.interfaces.StudentDao;
import com.school.dao.interfaces.SubjectDao;
import com.school.dao.interfaces.SubjectRequestDao;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes={RootConfig.class})
public class AdminControllerTest {

	public static final Long PROFESSOR_ID = 1L;
	public static final Long SUBJECT_ID = 3L;
	public static final Long SUBJECT_REQUEST_ID = 5L;
	public static final String USERNAME = "username";
	
	@Mock
	AdminDao adminDao;
	@Mock
	SubjectDao subjectDao;
	@Mock
	StudentDao studentDao;
	@Mock
	ProfessorDao profDao;
	@Mock
	SubjectRequestDao requestDao;

	@InjectMocks
	AdminController controller;

	private MockMvc mockMvc;
	
	private Admin admin;
	private Subject subject;
	private Subject subject1;
	private Professor professor;
	private Professor professor1;
	private SubjectRequest subjectRequest; 
	private SubjectRequest subjectRequest1; 
	private List<Professor> listOfProfessors;
	private List<SubjectRequest> listOfRequests;
	private List<Subject> listOfSubjects;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(controller).build();
		admin = new Admin();
		subject = new Subject();
		subject1 = new Subject();
		professor = new Professor();
		professor1 = new Professor();
		subjectRequest = new SubjectRequest();
		subjectRequest1 = new SubjectRequest();
		listOfSubjects = Arrays.asList(subject, subject1);
		listOfProfessors = Arrays.asList(professor, professor1);
		listOfRequests = Arrays.asList(subjectRequest, subjectRequest1);
	}
	
	@Test(expected=NestedServletException.class)
	public void adminPage() throws Exception {
		
		doReturn(admin).when(adminDao).getAdminByUsername(USERNAME);
		doReturn(listOfProfessors).when(profDao).getAll();
		doReturn(listOfRequests).when(requestDao).getAll();
		
		mockMvc.perform(post("/admin"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("admin"))
			   .andExpect(model().attribute("professors", hasSize(2)))
			   .andExpect(model().attribute("subjectRequests", hasSize(2)));
	}
	
	@Test
	public void acceptSubjectRequest() throws Exception {
		
		doReturn(subjectRequest).when(requestDao).getSubjectRequestById(SUBJECT_REQUEST_ID);
		studentDao.addSubjectToStudentByRequestId(SUBJECT_REQUEST_ID);
		
		mockMvc.perform(post("/acceptSubjectRequest/{subjectRequestId}", SUBJECT_REQUEST_ID));
			   //.andExpect(redirectedUrl("/admin"));
	}
	
	@Test
	public void declineSubjectRequest() throws Exception {
		
		doReturn(subjectRequest).when(requestDao).getSubjectRequestById(SUBJECT_ID);
		studentDao.removeSubjectRequestByRequestId(SUBJECT_REQUEST_ID);
		requestDao.deleteSubjectRequest(subjectRequest);
		
		mockMvc.perform(post("/declineSubjectRequest/{subjectRequestId}", SUBJECT_REQUEST_ID));
			   //.andExpect(redirectedUrl("/admin"));
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
		
		doReturn(listOfProfessors).when(profDao).getAllProfessorsWithSubjects();
		doReturn(listOfSubjects).when(subjectDao).getAll();
		
		mockMvc.perform(post("/addSubjectToProf"))
			   .andExpect(view().name("addSubjectsToProfessor"))
			   .andExpect(model().attribute("listOfProfessors", nullValue()))
			   .andExpect(model().attribute("listOfSubjects", nullValue()));
	}
	
	@Test
	public void addSubjectToProfessor() throws Exception {
		
		profDao.addSubjectToProfessorById(PROFESSOR_ID, subject);
		subjectDao.addProfessorToSubjectById(professor, SUBJECT_ID);
		
		mockMvc.perform(post("/addSubjectToProfessor, params=professor && subject", PROFESSOR_ID, SUBJECT_ID));
	}
	
}
