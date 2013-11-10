package com.njue.mis.services;

import java.util.Vector;

import com.njue.mis.dao.UserRoleDao;
import com.njue.mis.model.UserRole;

public class UserRoleService {
	private UserRoleDao userRoleDao = new UserRoleDao();
	public int addUserRole(UserRole userRole){
		return userRoleDao.add(userRole);
	}
	
	public boolean deleteUserRole(UserRole userRole){
		return userRoleDao.delete(userRole);
	}
	
	public boolean updateUserRole(UserRole userRole){
		return userRoleDao.update(userRole);
	}
	public UserRole getUserRole(int id){
		return userRoleDao.get(id);
	}
	
	public Vector<UserRole> getAllUserRole(){
		return userRoleDao.getAll();
	}
}
