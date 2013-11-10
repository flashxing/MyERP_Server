package com.njue.mis.dao;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.njue.mis.model.StoreHouse;
import com.njue.mis.server.Server;


public class StoreHouseDao extends CommonObjectDao{
	
	public int saveStoreHouse(StoreHouse storeHouse){
		int id = -1;
		id = (Integer) super.save(storeHouse);
		return id;
	}
	public StoreHouse getStoreHouse(int id){
		return (StoreHouse) super.get(StoreHouse.class, id);
	}
	
	public boolean deleteStoreHouse(StoreHouse storeHouse){
		return super.delete(storeHouse);
	}
	
	@SuppressWarnings("finally")
	public boolean updateStoreHouse(StoreHouse storeHouse)
	{
		Session session = null;
		StoreHouse tmp_storeHouse = null;
		boolean result = false;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			tmp_storeHouse =(StoreHouse) session.get(StoreHouse.class, storeHouse);
			tmp_storeHouse.update(storeHouse);
			session.getTransaction().commit();
			result = true;	
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("update "+storeHouse+" failed");
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
	/*
	 * @returns all category in the db
	 */
	@SuppressWarnings({ "finally", "unchecked" })
	public List<StoreHouse> getAll()
	{
		Session session = null;
		List<StoreHouse> list = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			String sql = "from StoreHouse order by id";
			Query query = session.createQuery(sql);
			list = new ArrayList<StoreHouse>(query.list());
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("get all storeHouse "+" failed");
			if(session != null){
				session.getTransaction().rollback();
			}
		}finally{
			if(session != null){
				session.close();
			}
			return list;
		}
	}
}
