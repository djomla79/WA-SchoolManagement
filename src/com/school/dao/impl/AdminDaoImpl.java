package com.school.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.school.beans_model.Admin;
import com.school.dao.generic.GenericDaoImpl;
import com.school.dao.interfaces.AdminDao;

@Repository
public class AdminDaoImpl extends GenericDaoImpl<Admin, Long> implements AdminDao {

	public AdminDaoImpl() {
		super(Admin.class);
	}

	@Override
	public Admin saveAdmin(Admin admin) {
		super.saveEntity(admin);
		return admin;
	}
	
	@Override
	public Admin getAdminByUsername(String username) {
		Criteria criteria = getSession().createCriteria(Admin.class);
		criteria.add(Restrictions.eq("username", username));
		return (Admin) criteria.uniqueResult();
	}
	
	@Override
	public Admin getAdminById(Long adminId) {
		return (Admin) getSession().get(Admin.class, adminId);
	}

}
