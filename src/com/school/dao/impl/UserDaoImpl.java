package com.school.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.school.beans_model.User;
import com.school.dao.generic.GenericDaoImpl;
import com.school.dao.interfaces.UserDao;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}
	
	@Override
	public User getUserByUsername(String username) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		return (User) criteria.uniqueResult();
	}

}
