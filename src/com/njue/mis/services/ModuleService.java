package com.njue.mis.services;

import java.util.Vector;

import com.njue.mis.dao.ModuleDao;
import com.njue.mis.model.Module;

public class ModuleService {
	private ModuleDao moduleDao = new ModuleDao();
	
	public Vector<Module> getAll(){
		return moduleDao.getAll();
	}
}
