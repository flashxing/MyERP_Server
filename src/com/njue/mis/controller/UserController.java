package com.njue.mis.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import com.njue.mis.dao.UserDao;
import com.njue.mis.interfaces.UserControllerInterface;
import com.njue.mis.model.User;

public class UserController extends UnicastRemoteObject implements
		UserControllerInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1678298131796327597L;
	private UserDao userDao = new UserDao();
	public UserController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int addUser(User user) throws RemoteException {
		// TODO Auto-generated method stub
		return userDao.add(user);
	}

	@Override
	public boolean updateUser(User user) throws RemoteException {
		// TODO Auto-generated method stub
		return userDao.update(user);
	}

	@Override
	public boolean deleteUser(User user) throws RemoteException {
		// TODO Auto-generated method stub
		return userDao.delete(user);
	}

	@Override
	public User getUser(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return userDao.get(id);
	}

	@Override
	public Vector<User> getAllUser() throws RemoteException {
		// TODO Auto-generated method stub
		return userDao.getAll();
	}

	@Override
	public User validUser(String userName, String passWord)
			throws RemoteException {
		// TODO Auto-generated method stub
		return userDao.validUser(userName,passWord);
	}

}
