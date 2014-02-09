package com.njue.mis.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.njue.mis.common.CommonUtil;
import com.njue.mis.server.Server;

public class CommonObjectDao {
	@SuppressWarnings("finally")
	public Serializable save(Object object){
		Session session = null;
		Serializable result = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			result = session.save(object);
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("Save "+object+" failed");
			if(session != null){
				session.getTransaction().rollback();
			}
			result = null;
		}finally{
			if(session != null){
				session.close();
			}
			return result;
		}
	}
	
	@SuppressWarnings("finally")
	protected Object get(@SuppressWarnings("rawtypes") Class myclass, Serializable id)
	{
		Session session = null;
		Object obj = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			obj = session.get(myclass, id);
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("get "+id+" failed");
			if(session != null){
				session.getTransaction().rollback();
			}
		}finally{
			if(session != null){
				session.close();
			}
			return obj;
		}
	}
	
	@SuppressWarnings("finally")
	protected boolean delete(Object object)
	{
		Session session = null;
		boolean result = false;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.delete(object);
			session.getTransaction().commit();
			result = true;
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("delete "+object+" failed");
			if(session != null){
				session.getTransaction().rollback();
			}
		}finally{
			if(session != null){
				session.close();
			}
			return result;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean update(Object object){
		Session session = null;
		boolean result = false;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.update(object);
			session.getTransaction().commit();
			result = true;
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("update "+object+" failed");
			if(session != null){
				session.getTransaction().rollback();
			}
		}finally{
			if(session != null){
				session.close();
			}
			return result;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean update(Object object,Serializable id){
		Session session = null;
		boolean result = false;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Object tmp_discount = session.get(object.getClass(), id);
			CommonUtil.updateObject(tmp_discount, object);
			session.getTransaction().commit();
			result = true;	
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("update "+object+" failed");
			if(session != null){
				session.getTransaction().rollback();
			}
		}finally{
			if(session != null){
				session.close();
			}
			return result;
		}
	}
	@SuppressWarnings({ "finally", "unchecked", "rawtypes" })	
	public Vector getAll(String table){
		Session session = null;
		Vector result = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			String sql = "from "+table;
			Query query = session.createQuery(sql);
			result = new Vector(query.list());
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("get all "+table+" failed");
			if(session != null){
				session.getTransaction().rollback();
			}
		}finally{
			if(session != null){
				session.close();
			}
			return result;
		}
	}
	
	@SuppressWarnings({ "finally", "unchecked", "rawtypes" })
	public List getLastN(String table, int n){
		Session session = null;
		List result = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			String sql = "from "+table;
			Query query = session.createQuery(sql);
			query.setFirstResult(0);
			query.setMaxResults(n);
			result = new ArrayList<>(query.list());
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("get all "+table+" failed");
			if(session != null){
				session.getTransaction().rollback();
			}
		}finally{
			if(session != null){
				session.close();
			}
			return result;
		}
	}
}
