package com.school.dao.interfaces;

import com.school.beans_model.User;
import com.school.dao.generic.GenericDao;

public interface UserDao extends GenericDao<User, Long> {
	
	/** abstract methods */
	User getUserByUsername(String username);

	boolean isUserAlreadyExists(String username);

}
