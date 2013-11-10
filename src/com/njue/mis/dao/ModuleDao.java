package com.njue.mis.dao;

import java.util.Vector;

import com.njue.mis.model.Module;

public class ModuleDao extends CommonObjectDao {
	public int save(Module module){
		return (Integer) super.save(module);
	}
	
	public boolean delete(Module module){
		return super.delete(module);
	}
	
	@SuppressWarnings("unchecked")
	public Vector<Module> getAll(){
		return super.getAll("Module");
	}
}
