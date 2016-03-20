package com.school.commons;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** fields */
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="Id", nullable=false, unique=true)
	private long id;
	
	/** Geters and seters */
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
