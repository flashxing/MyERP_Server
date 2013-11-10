package com.njue.mis.dao.test;

import com.njue.mis.dao.RoleDao;
import com.njue.mis.model.Role;

import junit.framework.TestCase;

public class RoleDaoTest extends TestCase{
	private RoleDao roleDao = new RoleDao();
	private Role role;
	public void setUp(){
		role = new Role();
		role.setRoleId(1000);
		role.setRoleName("���Թ���ʦ");
		role.getModuleSet().add("ϵͳ����");
	}
	public void tearDown(){
		
	}
	
	public void testVoid(){
		int id = roleDao.add(role);
		assertTrue(roleDao.delete(role));
	}
}
