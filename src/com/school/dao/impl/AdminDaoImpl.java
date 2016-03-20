package com.school.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.school.beans_model.Admin;
import com.school.dao.generic.GenericDaoImpl;
import com.school.dao.interfaces.AdminDao;

@Repository
public class AdminDaoImpl extends GenericDaoImpl<Admin, Long> implements AdminDao {
	
	/** constructor */
	public AdminDaoImpl() {
		super(Admin.class);
	}
	/** return admin that is saved 
	 *  using save method from GenericDaoImpl class,
	 *  and using PasswordEncoder class to encode password */
	@Override
	public Admin saveAdmin(Admin admin) {
		admin.setEncodedPassword(encoder.encode(admin.getTransientPassword()));
		super.saveEntity(admin);
		return admin;
	}
	/** return admin that matches
	 *  the one in Admin class by username */
	@Override
	public Admin getAdminByUsername(String username) {
		Criteria criteria = getSession().createCriteria(Admin.class);
		criteria.add(Restrictions.eq("username", username));
		return (Admin) criteria.uniqueResult();
	}
	/** return admin that matches
	 *  the one in Admin class by id */
	@Override
	public Admin getAdminById(Long adminId) {
		return (Admin) getSession().get(Admin.class, adminId);
	}

}
