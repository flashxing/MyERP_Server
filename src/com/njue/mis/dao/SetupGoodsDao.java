package com.njue.mis.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.njue.mis.model.SetupGoods;
import com.njue.mis.server.Server;

public class SetupGoodsDao extends CommonObjectDao {
	public int add(SetupGoods setupGoods){
		Serializable id = super.save(setupGoods);
		return id!=null?1:0;
	}
	
	public boolean update(SetupGoods setupGoods){
		return super.update(setupGoods);
	}
	
	public boolean delete(SetupGoods setupGoods){
		return super.delete(setupGoods);
	}
	
	@SuppressWarnings("finally")
	public boolean update(List<SetupGoods> setupGoodsList){
		Session session = null;
		Boolean result = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			for(SetupGoods setupGoods: setupGoodsList){
				Server.logger.debug("The number of setupGoods "+setupGoods.getGoodsId()+" "+setupGoods.getNumber());
				session.saveOrUpdate(setupGoods);
			}
			session.getTransaction().commit();
			result = true;
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("update "+setupGoodsList+" failed");
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
	public List<SetupGoods> getAll(List<String> goodsIds, int shId){
		List<SetupGoods> list = new ArrayList<>();
		Session session = null;
		Iterator<SetupGoods> iterator = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			for(String goodsId : goodsIds){
				iterator = session.createQuery("from SetupGoods where goods_id='"+goodsId+"' and shId="+shId).list().iterator();
				if(iterator.hasNext()){
					list.add(iterator.next().clone());
				}else{
					list.add(new SetupGoods(goodsId, shId, 0));
				}
			}
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("get "+goodsIds+" failed");
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
	public List<SetupGoods> getAll(){
		List<SetupGoods> list = new ArrayList<>();
		@SuppressWarnings("unchecked")
		Iterator<SetupGoods> iterator = super.getAll("SetupGoods").iterator();
		while(iterator.hasNext()){
			list.add(iterator.next().clone());
		}
		return list;
	}
}
