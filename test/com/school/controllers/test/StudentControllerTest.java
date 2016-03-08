package com.school.controllers.test;

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

import com.school.config.RootConfig;
import com.school.controllers.StudentController;
import com.school.dao.interfaces.StudentDao;
import com.school.dao.interfaces.SubjectDao;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration( classes = { RootConfig.class } )
public class StudentControllerTest {
	
	public static final Long STUDENT_ID = 1L;
	public static final Long SUBJECT_ID = 3L;
	public static final Integer GRADE_VALUE = 10;
	
	@Mock
	StudentDao studentDao;
	@Mock
	SubjectDao subjectDao;
	
	@InjectMocks
	StudentController controller;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(controller).build();
	}
	
	@Test(expected = NestedServletException.class)
	public void accountStudent() throws Exception {
		
		mockMvc.perform(post("/accountStudent"))
			   .andExpect(view().name("studentAccount"))
			   .andExpect(model().attribute("student", notNullValue()))
			   .andExpect(model().attribute("allSubjects", notNullValue()));
	}
	
	@Test
	public void getSubjectToStudentStudentController() throws Exception {
		
		mockMvc.perform(post("/getSubjectToStudent, params=student && subject", STUDENT_ID, SUBJECT_ID));
	}
	
	@Test
	public void addAbsenceToStudentStudentController() throws Exception {
		
		mockMvc.perform(post("/addAbsenceToStudent, params=student && subject", STUDENT_ID, SUBJECT_ID));
	}
	
	@Test
	public void addGradeToStudentStudentController() throws Exception {
			
		mockMvc.perform(post("/addGradeToStudent, params=student && subject && gradeValue", STUDENT_ID, SUBJECT_ID, GRADE_VALUE));
	}
	
	@Test
	public void sendSubjectRequestStudentController() throws Exception {
		
		mockMvc.perform(post("/sendSubjectRequest, params=student && subject"));
	}
	
	@Test
	public void getSubjectWithStudentsStudentController() throws Exception {
		
		mockMvc.perform(post("/getSubjectWithStudents/{subjectId}", SUBJECT_ID))
			   .andExpect(view().name("allStudents"));
	}
	
	@Test
	public void getStudentWithSubjectsStudentController() throws Exception {
		
		mockMvc.perform(post("/getStudentWithSubjects/{studentId}", STUDENT_ID))
			   .andExpect(view().name("studentInfo"));
	}
	
}
