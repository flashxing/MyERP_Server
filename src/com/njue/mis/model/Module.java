package com.njue.mis.model;

import java.io.Serializable;

public class Module implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7873739293600964558L;
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
	
	
}
