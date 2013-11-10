package com.njue.mis.services;

import java.util.Vector;

import com.njue.mis.dao.UserDao;
import com.njue.mis.model.User;

public class UserService {
	private UserDao userDao = new UserDao();
	public int addUser(User user){
		return userDao.add(user);
	}
	
	public boolean deleteUser(User user){
		return userDao.delete(user);
	}
	
	public boolean updateUser(User user){
		return userDao.update(user);
	}
	public User getUser(int id){
		return userDao.get(id);
	}
	
	public Vector<User> getAllUser(){
		return userDao.getAll();
	}
}
