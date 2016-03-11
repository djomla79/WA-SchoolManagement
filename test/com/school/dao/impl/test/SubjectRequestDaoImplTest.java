package com.school.dao.impl.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.school.beans_model.Student;
import com.school.beans_model.Subject;
import com.school.beans_model.SubjectRequest;
import com.school.dao.impl.StudentDaoImpl;
import com.school.dao.impl.SubjectDaoImpl;
import com.school.dao.impl.SubjectRequestDaoImpl;

public class SubjectRequestDaoImplTest {

	public static final Long STUDENT_ID = 1L;
	public static final Long SUBJECT_ID = 3L;
	public static final Long REQUEST_ID = 5L;
	public static final Integer GRADE_VALUE = 10;
	public static final String USERNAME = "name";

	@Mock
	StudentDaoImpl studentDao;
	@Mock
	SubjectDaoImpl subjectDao;
	@Mock
	SubjectRequestDaoImpl requestDao;
	@Mock
	Subject subjectMock;
	@Mock
	Student studentMock;
	
	private SubjectRequest request;
	private SubjectRequest request1;
	private List<SubjectRequest> listOfRequests;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		request = new SubjectRequest();
		request1 = new SubjectRequest();
		listOfRequests = Arrays.asList(request, request1);
	}
	
	@Test
	public void saveSubjectRequest() throws Exception {
		doReturn(request1).when(requestDao).saveSubjectRequest(request);
		assertEquals(request1, requestDao.saveSubjectRequest(request));
	}
	
	@Test
	public void getSubjectRequestById() throws Exception {
		doReturn(request).when(requestDao).get(SUBJECT_ID);
		assertEquals(request, requestDao.get(SUBJECT_ID));
	}
	
	@Test
	public void deleteSubjectRequest() throws Exception {
		requestDao.deleteSubjectRequest(request);
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected=NullPointerException.class)
	public void getAllSubjectRequests() throws Exception {
		Criteria criteria = requestDao.getSession().createCriteria(SubjectRequest.class);
		listOfRequests = criteria.list();
		assertEquals(listOfRequests, requestDao.getAll());
	}
	
}
