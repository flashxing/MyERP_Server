package com.njue.mis.dao.test;

import com.njue.mis.dao.UserDao;
import com.njue.mis.model.User;

import junit.framework.TestCase;

public class UserDaoTest extends TestCase{
	private UserDao userDao = new UserDao();
	private User user1;
	private User user2;
	public void setUp(){
		user1 = new User();
		user1.setUserName("a");
		user1.setUserPW("abc");
		user2 = new User();
		user2.setUserName("b");
		user2.setUserPW("bbc");
	}
	
	public void tearDown(){
		
	}
	
	public void testVoid(){
		int size = userDao.getAll().size();
		int id1 = userDao.add(user1);
		int id2 = userDao.add(user2);
		assertTrue(id1>0);
		assertTrue(id2>0);
		
		User tmp = userDao.get(id1);
		assertEquals(tmp.getUserName(),"a");
		
		assertTrue(userDao.delete(tmp));
		tmp = userDao.get(id1);
		assertNull(tmp);
		
		assertEquals(userDao.getAll().size()-size,1);
		
		assertTrue(userDao.delete(user2));
		assertEquals(userDao.getAll().size()-size,0);
	}
}
