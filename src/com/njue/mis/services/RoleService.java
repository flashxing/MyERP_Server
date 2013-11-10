package com.njue.mis.services;

import java.util.Vector;

import com.njue.mis.dao.RoleDao;
import com.njue.mis.model.Module;
import com.njue.mis.model.Role;

public class RoleService {
	private RoleDao roleDao = new RoleDao();
	private ModuleService moduleService = new ModuleService();
	public int addRole(Role role){
		return roleDao.add(role);
	}
	
	public boolean deleteRole(Role role){
		return roleDao.delete(role);
	}
	
	public boolean updateRole(Role role){
		return roleDao.update(role);
	}
	public Role getRole(int id){
		return roleDao.get(id);
	}
	
	public Vector<Role> getAllRole(){
		return roleDao.getAll();
	}

	public Vector<Module> getAllModule() {
		// TODO Auto-generated method stub
		return moduleService.getAll();
	}
}
