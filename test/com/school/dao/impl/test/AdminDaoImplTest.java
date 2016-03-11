package com.school.dao.impl.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.school.beans_model.Admin;
import com.school.controllers.AdminController;
import com.school.dao.interfaces.AdminDao;

public class AdminDaoImplTest {
	
	public static final Long ADMIN_ID = 1L;
	public static final String USERNAME = "username";

	@Mock
	AdminDao adminDao;

	@InjectMocks
	AdminController controller;
	
	private Admin admin;
	private Admin admin1;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		admin = new Admin();
		admin1 = new Admin();
	}
	
	@Test
	public void saveAdmin() throws Exception {
		doReturn(admin1).when(adminDao).saveAdmin(admin);
		assertEquals(admin1, adminDao.saveAdmin(admin));
		verify(adminDao).saveAdmin(admin);
	}
	
	@Test
	public void getAdminByUsername() throws Exception {
		doReturn(admin1).when(adminDao).getAdminByUsername(USERNAME);
		assertEquals(admin1, adminDao.getAdminByUsername(USERNAME));
		verify(adminDao).getAdminByUsername(USERNAME);
	}
	
	@Test
	public void getAdminById() throws Exception {
		doReturn(admin1).when(adminDao).getAdminById(ADMIN_ID);
		assertEquals(admin1, adminDao.getAdminById(ADMIN_ID));
		verify(adminDao).getAdminById(ADMIN_ID);
	}
	
}
