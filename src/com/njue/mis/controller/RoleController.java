package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import com.njue.mis.interfaces.RoleControllerInterface;
import com.njue.mis.model.Module;
import com.njue.mis.services.RoleService;

public class RoleController extends UnicastRemoteObject implements RoleControllerInterface {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3420622516707554823L;
	public RoleController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private RoleService roleService = new RoleService();
	@Override
	public int addRole(com.njue.mis.model.Role role) throws RemoteException {
		// TODO Auto-generated method stub
		return roleService.addRole(role);
	}

	@Override
	public boolean updateRole(com.njue.mis.model.Role role)
			throws RemoteException {
		// TODO Auto-generated method stub
		return roleService.updateRole(role);
	}

	@Override
	public boolean deleteRole(com.njue.mis.model.Role role)
			throws RemoteException {
		// TODO Auto-generated method stub
		return roleService.deleteRole(role);
	}

	@Override
	public com.njue.mis.model.Role getRole(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return roleService.getRole(id);
	}

	@Override
	public Vector<com.njue.mis.model.Role> getAllRole() throws RemoteException {
		// TODO Auto-generated method stub
		return roleService.getAllRole();
	}

	@Override
	public Vector<Module> getAllModule() throws RemoteException {
		// TODO Auto-generated method stub
		return roleService.getAllModule();
	}

}
