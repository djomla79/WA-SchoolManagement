package com.school.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.school.beans_model.User;
import com.school.dao.generic.GenericDaoImpl;
import com.school.dao.interfaces.UserDao;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {
	
	/** constructor */
	public UserDaoImpl() {
		super(User.class);
	}
	/** return user that matches
	 *  the one in User class by username */
	@Override
	public User getUserByUsername(String username) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		return (User) criteria.uniqueResult();
	}
	/** return true if user is found 
	 *  in User class by username, otherwise return false */
	@Override
	public boolean isUserAlreadyExists(String username) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		return criteria.uniqueResult() != null ? true : false;
	}

}
