package com.njue.mis.dao;



import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.njue.mis.model.Pair;
import com.njue.mis.model.Stock;
import com.njue.mis.server.Server;


public class StockDao extends CommonObjectDao{
	public int saveStock(Stock stock)
	{
		Serializable id = null;
		Stock existStock = getStockByGoodsAndTime(stock.getGoodsId(), stock.getTime(), stock.getShId());
		if(existStock != null){
			if(existStock.getTime() == stock.getTime()){
				joinStock(existStock, stock);
				if(updateStock(existStock)){
					id = existStock.getId();
				}else{
					id = null;
				}
			}else{
				joinStock(stock, existStock);
				id = super.save(stock);
			}
		}else {
			id = super.save(stock);
		}
		return id!=null?1:0;
	}
	
	@SuppressWarnings("finally")
	public boolean saveStock(List<Stock> stocks){
		Session session = null;
		boolean result = false;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			for(Stock stock : stocks){
				String sqlString = "from Stock where goods_id = '"+stock.getGoodsId()+"' and sh_id="+stock.getShId()+" and time <="+stock.getTime()+" order by time DESC";
				Query query = session.createQuery(sqlString);
				query.setFirstResult(0);
				query.setMaxResults(1);
				Stock existStock = query.list().size()>0?(Stock) query.list().get(0):null;
				if(existStock != null){
					if(existStock.getTime() == stock.getTime()){
						joinStock(existStock, stock);
					}else{
						joinStock(stock, existStock);
						result = session.save(stock)!=null;
					}
				}else{
					result = session.save(stock)!=null;
				}
			}
			session.getTransaction().commit();
			result = true;
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("save all "+stocks+" failed");
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
	
	public boolean delStock(Stock stock)
	{
		Serializable id = null;
		Stock existStock = getStockByGoodsAndTime(stock.getGoodsId(), stock.getTime(), stock.getShId());
		if(existStock != null){
			if(existStock.getNumber()>=stock.getNumber()){
				if(existStock.getTime() == stock.getTime()){
					existStock.setNumber(existStock.getNumber()-stock.getNumber());
					return super.update(existStock);
				}else{
					stock.setNumber(existStock.getNumber()-stock.getNumber());
					stock.setPrice(existStock.getPrice());
					Server.logger.debug(stock.getGoodsId()+" "+stock.getTime()+" "+stock.getShId());
					id = super.save(stock);
					return id!=null;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	@SuppressWarnings("finally")
	public boolean deleteStock(List<Stock> stocks){
		Session session = null;
		boolean result = false;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			for(Stock stock : stocks){
				String sqlString = "from Stock where goods_id = '"+stock.getGoodsId()+"' and sh_id="+stock.getShId()+" and time <="+stock.getTime()+" order by time DESC";
				Query query = session.createQuery(sqlString);
				query.setFirstResult(0);
				query.setMaxResults(1);
				Stock existStock = query.list().size()>0?(Stock) query.list().get(0):null;
				if(existStock != null){
					if(existStock.getNumber()>=stock.getNumber()){
						Server.logger.debug("The exist stock number is bigger than stock or equal");
						if(existStock.getTime() == stock.getTime()){
							Server.logger.debug("The exist stock number is equal with stock");
							existStock.setNumber(existStock.getNumber()-stock.getNumber());
						}else{
							Server.logger.debug("The exist stock number is bigger than stock");
							stock.setNumber(existStock.getNumber()-stock.getNumber());
							stock.setPrice(existStock.getPrice());
							Server.logger.debug(stock.getGoodsId()+" "+stock.getTime()+" "+stock.getShId());
							session.save(stock);
						}
					}else{
						Server.logger.debug("The exist stock number is less than stock");
						throw new HibernateException("stock not enough "+stock.getGoodsId());
					}
				}else{
					Server.logger.debug("The is no exist stock");
					throw new HibernateException("stock not enough "+stock.getGoodsId());
				}
			}
			session.getTransaction().commit();
			result = true;
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("save all "+stocks+" failed");
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
	
	public boolean updateStock(Stock stock)
	{
//		if(stock.getNumber()<0){
//			return false;
//		}
//		Session session = null;
//		Stock tmp_stock = null;
//		boolean result = false;
//		try{
//			session = HibernateUtil.getSession();
//			session.beginTransaction();
//			tmp_stock = (Stock) session.get(Stock.class,stock.getId());
//			tmp_stock.setGoodsId(stock.getGoodsId());
//			tmp_stock.setNumber(stock.getNumber());
//			session.getTransaction().commit();
//			result = true;
//		}catch(HibernateException ex){
//			ex.printStackTrace();
//			Server.logger.warn("update "+stock+" failed");
//			if(session != null){
//				session.getTransaction().rollback();
//			}
//		}finally{
//			if(session != null){
//				session.close();
//			}
//			return result;
//		}
		return super.update(stock);
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
    
    public Stock getStockByGoodsAndTime(String goodsId, int time, int shId){
    	String sqlString = "Stock where goods_id = '"+goodsId+"' and sh_id="+shId+" and time <="+time+" order by time DESC";
    	@SuppressWarnings("unchecked")
		List<Stock> stocks = super.getLastN(sqlString, 1);
    	if(stocks.size()>0){
    		return stocks.get(0);
    	}else {
			return null;
		}
    }
    
    public Stock getLastStockByTime(String goodsId, int time, int shId){
    	String sqlString = "Stock where goods_id = '"+goodsId+"' and sh_id="+shId+" and time <"+time+" order by time DESC";
    	@SuppressWarnings("unchecked")
		List<Stock> stocks = super.getLastN(sqlString, 1);
    	if(stocks.size()>0){
    		return stocks.get(0);
    	}else{
    		return null;
    	}
    }

	@SuppressWarnings({ "unchecked", "finally" })
	public Stock getStock(int shId, String goodsId) {
		// TODO Auto-generated method stub
		Session session = null;
		List<Stock> list = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			String sql = "from Stock where goods_id = '"+goodsId+"' and sh_id="+shId+" order by time DESC";
			Query query = session.createQuery(sql);
			list = new ArrayList<Stock>(query.list());
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("get stock goods_id = '"+goodsId+"' and sh_id ="+shId+" failed");
			list = null;
			if(session != null){
				session.getTransaction().rollback();
			}
		}finally{
			if(session != null){
				session.close();
			}
			if(list != null && list.size()>0){
				return list.get(0);
			}
			return null;
		}
	}
	
	public void joinStock(Stock stock1, Stock stock2){
		double price = 0;
		if(stock2.getPrice() == 0){
			price = stock1.getPrice();
		}else if(stock1.getPrice() == 0) {
			price = stock2.getPrice();
		}else{
			double first = stock1.getPrice()*stock1.getNumber();
			double second = stock2.getPrice()*stock2.getNumber();
			DecimalFormat dFormat = new DecimalFormat(".##");
			Double result = (first+second)/(stock1.getNumber()+stock2.getNumber());
			price = Double.parseDouble(dFormat.format(result));
		}
		stock1.setNumber(stock1.getNumber()+stock2.getNumber());
		stock1.setPrice(price);
	}

	@SuppressWarnings("finally")
	public boolean setUp(Map<Pair<String, Integer>, Integer> setupGoodsMap) {
		Session session = null;
		Boolean result = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Stock> list = session.createQuery("from Stock").list();
			if(list != null)
			for(Stock stock : list){
				int number = 0;
				Pair<String, Integer> pair = new Pair<String, Integer>(stock.getGoodsId(), stock.getShId());
				if(setupGoodsMap.containsKey(pair)){
					number = setupGoodsMap.get(pair);
					stock.setNumber(number);
					session.update(stock);
				}
			}
			session.getTransaction().commit();
			result = true;
		}catch(Exception ex){
			ex.printStackTrace();
			Server.logger.warn("setUp Stock Failed");
			if(session != null){
				session.getTransaction().rollback();
			}
			result = false;
		}finally{
			if(session != null){
				session.close();
			}
			return result;
		}
	}
}
