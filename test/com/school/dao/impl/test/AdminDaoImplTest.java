package com.school.dao.impl.test;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import com.school.beans_model.Admin;
import com.school.beans_model.Professor;
import com.school.beans_model.Subject;
import com.school.beans_model.SubjectRequest;
import com.school.controllers.AdminController;
import com.school.dao.interfaces.AdminDao;
import com.school.dao.interfaces.ProfessorDao;
import com.school.dao.interfaces.StudentDao;
import com.school.dao.interfaces.SubjectDao;
import com.school.dao.interfaces.SubjectRequestDao;

public class AdminDaoImplTest {
	
	public static final Long STUDENT_ID = 5L;
	public static final Long PROFESSOR_ID = 7L;
	public static final Long SUBJECT_ID = 10L;
	public static final Long SUBJECT_REQUEST_ID = 11L;
	public static final String PRINCIPAL_USERNAME = "name";

	@Mock
	SubjectDao subjectDao;
	
	@Mock
	AdminDao adminDao;
	
	@Mock
	ProfessorDao profDao;
	
	@Mock
	StudentDao studentDao;
	
	@Mock
	SubjectRequestDao subjectRequestDao;

	@InjectMocks
	AdminController controller;

	private MockMvc mockMvc;
	
	private Admin admin;
	private Subject subject;
	private Professor professor;
	private SubjectRequest subjectRequest;
	private List<Subject> listOfSubjects;
	private List<Professor> listOfProfessors;
	private List<SubjectRequest> listOfRequests;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(controller).build();
		admin = new Admin();
		subject = new Subject();
		professor = new Professor();
		subjectRequest = new SubjectRequest();
		listOfSubjects = new ArrayList<>();
		listOfRequests = new ArrayList<>();
		listOfProfessors = new ArrayList<>();
	}
	
	@Test
	public void returnAdminPage() throws Exception {
		
		doReturn(admin).when(adminDao).getAdminByUsername(PRINCIPAL_USERNAME);
		doReturn(listOfProfessors).when(profDao).getAll();
		doReturn(listOfRequests).when(subjectRequestDao).getAll();
		
		mockMvc.perform(post("/admin, Principal, Model"));
			   //.andExpect(view().name("admin"));
		
	}
	
	@Test
	public void acceptSubjectRequest() throws Exception {
		
		doReturn(subjectRequest).when(subjectRequestDao).getSubjectRequestById(SUBJECT_REQUEST_ID);
		
		studentDao.addSubjectToStudentByRequestId(SUBJECT_REQUEST_ID);
		subjectRequestDao.deleteSubjectRequest(subjectRequest);
		
		mockMvc.perform(post("/acceptSubjectRequest/{subjectRequestId}", SUBJECT_REQUEST_ID));
			   //.andExpect(view().name("admin"));
	}
	
	@Test
	public void declineSubjectRequest() throws Exception {
		
		doReturn(subjectRequest).when(subjectRequestDao).getSubjectRequestById(SUBJECT_ID);
		studentDao.removeSubjectRequestByRequestId(SUBJECT_REQUEST_ID);
		subjectRequestDao.deleteSubjectRequest(subjectRequest);
		
		mockMvc.perform(post("/declineSubjectRequest/{subjectRequestId}", SUBJECT_REQUEST_ID));
		
	}
	
	@Test
	public void addingSubject() throws Exception {
		
		mockMvc.perform(post("/addingSubject"))
			   .andExpect(view().name("addSubject"));
	}
	
	@Test
	public void addSubject() throws Exception {
		
		subjectDao.saveSubject(subject);
		mockMvc.perform(post("/addSubject, Subject", subject));
	}
	
	@Test
	public void addSubjectToProf() throws Exception {
		
		doReturn(listOfProfessors).when(profDao).getAllProfessorsWithSubjects();
		doReturn(listOfSubjects).when(subjectDao).getAll();
		
		mockMvc.perform(post("/addSubjectToProf"))
			   .andExpect(view().name("addSubjectsToProfessor"));
		
	}
	
	@Test
	public void addSubjectToProfessor() throws Exception {
		
		profDao.addSubjectToProfessorById(PROFESSOR_ID, subject);
		subjectDao.addProfessorToSubjectById(professor, SUBJECT_ID);
		
		mockMvc.perform(post("/addSubjectToProfessor, params=professor && subject", PROFESSOR_ID, SUBJECT_ID));
			   //.andExpect(view().name("admin"));
	}
	
}
