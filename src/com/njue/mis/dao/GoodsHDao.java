package com.njue.mis.dao;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.njue.mis.common.CommonUtil;
import com.njue.mis.model.Goods;
import com.njue.mis.server.Server;

public class GoodsHDao extends CommonObjectDao{
	@SuppressWarnings("finally")
	public String addGoods(Goods goods){
		Session session = null;
		String id = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			goods.setId(CommonUtil.md5s(goods.getCateId()+goods.getProductCode()+CommonUtil.getCurrentTime()));
			if (!this.isExisted(goods.getProductCode(), goods.getCateId())){
				id = (String) session.save(goods);
			}
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("Save "+goods+" failed");
			if(session != null){
				session.getTransaction().rollback();
			}
			id = null;
		}finally{
			if(session != null){
				session.close();
			}
			return id;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean deleteGoods(Goods goods){
		Session session = null;
		boolean result = false;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.delete(goods);
			session.getTransaction().commit();
			result = true;
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("delete "+goods+" failed");
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
	
	@SuppressWarnings({ "unchecked", "finally" })
	public Vector<Goods> getAllGoods(){
		Session session = null;
		Vector<Goods> list = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			String sql = "from Goods  order by goodsname";
			Query query = session.createQuery(sql);
			list = new Vector<Goods>(query.list());
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("get all category "+" failed");
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
	public Goods getGoodsInfo(String id){
		Session session = null;
		Goods goods = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			goods = (Goods) session.get(Goods.class, id);
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
			return goods;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean isExisted(String productCode, int cateId){
		Session session = null;
		boolean result = false;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			String sql = "from Goods where productcode='"+productCode+"' and cateid="+cateId;
	    	Query query = session.createQuery(sql);
	    	@SuppressWarnings("unchecked")
			List<Goods> list = query.list();
	    	if (list.size() > 0)
	    	{
	    		result = true;
	    	}
	    	session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("To check goods is existed failed");
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
	
	public boolean updateGoods(Goods goods){
		return super.update(goods);
	}
	
	@SuppressWarnings({ "unchecked", "finally" })
	public Vector<Goods> getAllGoodsByCategory(int cateId){
		Session session = null;
		Vector<Goods> list = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			String sql = "from Goods where cateid = '"+cateId+"' order by goodsname";
			Query query = session.createQuery(sql);
			list = new Vector<Goods>(query.list());
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("get all category "+" failed");
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

	@SuppressWarnings({ "unchecked", "finally" })
	public Vector<Goods> getAllGoodsByGoodsName(String goodsName) {
		// TODO Auto-generated method stub
		Session session = null;
		Vector<Goods> list = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			String sql = "from Goods where goodsname like '%"+goodsName+"%' order by goodsname";
			Query query = session.createQuery(sql);
			list = new Vector<Goods>(query.list());
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("get all category "+" failed");
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
	public boolean setUp(Map<String, Integer> setUpGoodsMap) {
		Session session = null;
		Boolean result = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Goods> list = session.createQuery("from Goods").list();
			if(list != null)
			for(Goods goods : list){
				int number = 0;
				if(setUpGoodsMap.containsKey(goods.getId())){
					number = setUpGoodsMap.get(goods.getId());
					goods.setGoodsNum(number);
					session.update(goods);
				}
			}
			session.getTransaction().commit();
			result = true;
		}catch(Exception ex){
			ex.printStackTrace();
			Server.logger.warn("setUp Goods Failed");
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
