package com.njue.mis.dao;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.njue.mis.model.Stock;
import com.njue.mis.server.Server;


public class StockDao{
	@SuppressWarnings("finally")
	public int saveStock(Stock stock)
	{
		Session session = null;
		int id = -1;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			if ((id=this.getStockId(stock))<0){
				id = (Integer) session.save(stock);
			}
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("Save "+stock+" failed");
			if(session != null){
				session.getTransaction().rollback();
			}
			id = -1;
		}finally{
			if(session != null){
				session.close();
			}
			return id;
		}
	}
	
	@SuppressWarnings("finally")
	public Stock getStock(int id)
	{
		Session session = null;
		Stock stock = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			stock = (Stock) session.get(Stock.class, id);
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
			return stock;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean delStock(Stock stock)
	{
		int id;
		if(stock.getId() == 0){
			id = this.getStockId(stock);
		}else{
			id = stock.getId();
		}
		Stock tmp_stock = getStock(id);
		if(tmp_stock == null){
			Server.logger.warn("No stock:"+stock.getId()+" existed");
			return false;
		}
		tmp_stock.setNumber(tmp_stock.getNumber()-stock.getNumber());
		if(tmp_stock.getNumber() > 0){
			return updateStock(tmp_stock);
		}else if(tmp_stock.getNumber() < 0){
			Server.logger.warn("No enough goods in the stock");
			return false;
		}
		Session session = null;
		boolean result = false;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.delete(stock);
			session.getTransaction().commit();
			result = true;
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("delete "+stock+" failed");
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
	public boolean updateStock(Stock stock)
	{
		if(stock.getNumber()<0){
			return false;
		}
		Session session = null;
		Stock tmp_stock = null;
		boolean result = false;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			tmp_stock = (Stock) session.get(Stock.class,stock.getId());
			tmp_stock.setGoodsId(stock.getGoodsId());
			tmp_stock.setNumber(stock.getNumber());
			session.getTransaction().commit();
			result = true;
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("update "+stock+" failed");
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
	 * @returns List<Stock> all category in the db
	 */
	@SuppressWarnings({ "unchecked", "finally" })
	public List<Stock> getAll()
	{
		Session session = null;
		List<Stock> list = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			String sql = "from Stock";
			Query query = session.createQuery(sql);
			list = new ArrayList<Stock>(query.list());
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("get all stock "+" failed");
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
    
    @SuppressWarnings("finally")
	public int getStockId(Stock stock){
    	Session session = null;
		int id =-1;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			String sql = "from Stock where goods_id='"+stock.getGoodsId()+"' and sh_id ="+stock.getShId();
	    	Query query = session.createQuery(sql);
	    	@SuppressWarnings("unchecked")
			List<Stock> list = query.list();
	    	if (list.size() > 0)
	    	{
	    		id = list.get(0).getId();
	    	}
	    	session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("To check stock is existed failed");
			if(session != null){
				session.getTransaction().rollback();
			}
		}finally{
			if(session != null){
				session.close();
			}
			return id;
		}
    }
}
