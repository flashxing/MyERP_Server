package com.njue.mis.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;

import com.njue.mis.model.User;

public class UserDao extends CommonObjectDao{
	public int add(User user){
		Serializable id;
		if((id = super.save(user))!=null){
			return (Integer) id;
		}
		return -1;
	}
	
	public boolean delete(User user){
		return super.delete(user);
	}
	
	public User get(int id){
		User user = (User) super.get(User.class, id);
		if(user !=null){
			return user.clone();
		}
		return null;
	}
	
	public boolean update(User user){
		return super.update(user, user.getUserId());
	}
	
	@SuppressWarnings("unchecked")
	public Vector<User> getAll(){
		Vector<User> vec = new Vector<User>();
		Iterator<User> iter = super.getAll("User").iterator();
		while(iter.hasNext()){
			vec.add(iter.next().clone());
		}
		return vec;
	}

	public User validUser(String userName, String passWord) {
		// TODO Auto-generated method stub
		Vector<User> userVec = getAll();
		Iterator<User> iter = userVec.iterator();
		while(iter.hasNext()){
			User user = iter.next();
			if(user.getUserName().equals(userName)&&user.getUserPW().equals(passWord)){
				return user;
			}
		}
		return null;
	}
}
