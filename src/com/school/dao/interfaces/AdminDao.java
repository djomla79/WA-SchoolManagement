package com.school.dao.interfaces;

import com.school.beans_model.Admin;
import com.school.dao.generic.GenericDao;

public interface AdminDao extends GenericDao<Admin, Long> {

	Admin saveAdmin(Admin admin);

	Admin getAdminByUsername(String username);
	
}
