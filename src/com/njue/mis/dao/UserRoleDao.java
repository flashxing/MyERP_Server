package com.njue.mis.dao;

import java.util.Vector;

import com.njue.mis.model.UserRole;

public class UserRoleDao extends CommonObjectDao{
	public int add(UserRole userRole){
		return (Integer) super.save(userRole);
	}
	
	public boolean delete(UserRole userRole){
		return super.delete(userRole);
	}
	
	public UserRole get(int id){
		return (UserRole) super.get(UserRole.class, id);
	}
	
	public boolean update(UserRole userRole){
		return super.update(userRole, userRole.getRoleId());
	}
	
	@SuppressWarnings("unchecked")
	public Vector<UserRole> getAll(){
		return super.getAll("UserRole");
	}
}
