package com.njue.mis.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;

import com.mysql.jdbc.IterateBlock;
import com.njue.mis.model.Role;

public class RoleDao extends CommonObjectDao{
	public int add(Role role){
		Serializable result;
		if((result =super.save(role))!=null){
			return (Integer) result;
		}else{
			return -1;
		}
	}
	
	public boolean delete(Role role){
		return super.delete(role);
	}
	
	public Role get(int id){
		return new Role((Role) super.get(Role.class, id));
	}
	
	public boolean update(Role role){
		return super.update(role, role.getRoleId());
	}
	
	@SuppressWarnings("unchecked")
	public Vector<Role> getAll(){
		Vector<Role> vector = new Vector<Role>();
		Iterator<Role> iter = super.getAll("Role").iterator();
		while(iter.hasNext()){
			vector.add(new Role(iter.next()));
		}
		return vector;
	}
}
