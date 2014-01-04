package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import com.njue.mis.dao.UserRoleDao;
import com.njue.mis.interfaces.UserRoleControllerInterface;
import com.njue.mis.model.UserRole;

public class UserRoleController extends UnicastRemoteObject implements
		UserRoleControllerInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4910989975953508469L;
	private UserRoleDao userRoleDao = new UserRoleDao();
	public UserRoleController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int addUserRole(UserRole userRole) throws RemoteException {
		// TODO Auto-generated method stub
		return userRoleDao.add(userRole);
	}

	@Override
	public boolean updateUserRole(UserRole userRole) throws RemoteException {
		// TODO Auto-generated method stub
		return userRoleDao.update(userRole);
	}

	@Override
	public boolean deleteUserRole(UserRole userRole) throws RemoteException {
		// TODO Auto-generated method stub
		return userRoleDao.delete(userRole);
	}

	@Override
	public UserRole getUserRole(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return userRoleDao.get(id);
	}

	@Override
	public Vector<UserRole> getAllUserRoles() throws RemoteException {
		// TODO Auto-generated method stub
		return userRoleDao.getAll();
	}

}
