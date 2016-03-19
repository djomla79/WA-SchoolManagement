package com.school.beans_model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.school.commons.BaseEntity;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="users")
public class User extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Size(min=3, max=16)
	@Pattern(regexp = "^[A-Z][a-z]{3,16}$")
	private String name;
	@NotBlank
	@Size(min=3, max=25)
	@Pattern(regexp = "^[A-Z][a-z]{3,25}$")
	private String lastName;
	@NotBlank
	@Size(min=4, max=12)
	@Pattern(regexp = "^[a-z0-9]{4,12}$")
	private String username;
	@Transient
	@NotBlank
	@Size(min=4, max=10)
	@Pattern(regexp = "(?:[a-z].*){2}(?:[0-9].*){2}{4,10}$")
	private String transientPassword;
	@Column(name="password")
	private String encodedPassword;
	@Column(columnDefinition="TINYINT(1)")
	private boolean enabled;
	private String authority;
	
	
	public User() {
		/** Empty default constructor */
	}
	
	public User(String name, String lastName, String username, String password, boolean enabled, String authority) {
		this.name = name;
		this.lastName = lastName;
		this.username = username;
		this.transientPassword = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTransientPassword() {
		return transientPassword;
	}
	public void setTransientPassword(String password) {
		this.transientPassword = password;
	}
	public String getEncodedPassword() {
		return encodedPassword;
	}
	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
}
