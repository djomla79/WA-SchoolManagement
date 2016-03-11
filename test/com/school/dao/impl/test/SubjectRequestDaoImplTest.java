package com.school.dao.impl.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.school.beans_model.SubjectRequest;
import com.school.dao.impl.StudentDaoImpl;
import com.school.dao.impl.SubjectDaoImpl;
import com.school.dao.impl.SubjectRequestDaoImpl;

public class SubjectRequestDaoImplTest {

	public static final Long SUBJECT_ID = 3L;

	@Mock
	StudentDaoImpl studentDao;
	@Mock
	SubjectDaoImpl subjectDao;
	@Mock
	SubjectRequestDaoImpl requestDao;
	
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
		verify(requestDao).saveSubjectRequest(request);
	}
	
	@Test
	public void getSubjectRequestById() throws Exception {
		doReturn(request).when(requestDao).get(SUBJECT_ID);
		assertEquals(request, requestDao.get(SUBJECT_ID));
		verify(requestDao).get(SUBJECT_ID);
	}
	
	@Test
	public void deleteSubjectRequest() throws Exception {
		requestDao.deleteSubjectRequest(request);
		verify(requestDao).deleteSubjectRequest(request);
	}
	
	@Test
	public void getAllSubjectRequests() throws Exception {
		doReturn(listOfRequests).when(requestDao).getAll();
		assertEquals(listOfRequests, requestDao.getAll());
		verify(requestDao).getAll();
	}
	
}
