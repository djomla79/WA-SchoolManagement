package com.school.beans_model;

import javax.persistence.Entity;

@Entity
public class Admin extends User {
	
	private static final long serialVersionUID = 1L;
	
	public Admin() {
		/** Empty default constructor */
	}
	
	public Admin(User user) {
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setTransientPassword(user.getTransientPassword());
		this.setName(user.getName());
		this.setLastName(user.getLastName());
	}

}
