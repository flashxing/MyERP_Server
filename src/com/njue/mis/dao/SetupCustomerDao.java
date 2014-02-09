package com.njue.mis.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.njue.mis.model.SetupCustomer;
import com.njue.mis.server.Server;

public class SetupCustomerDao extends CommonObjectDao{
	@SuppressWarnings("finally")
	public boolean update(List<SetupCustomer> setupCustomerList){
		Session session = null;
		Boolean result = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			for(SetupCustomer  setupCustomer: setupCustomerList){
				session.saveOrUpdate(setupCustomer);
			}
			session.getTransaction().commit();
			result = true;
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("update "+setupCustomerList+" failed");
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
	
	@SuppressWarnings({"finally" })
	public List<SetupCustomer> getAll(List<String> customerIds){
		List<SetupCustomer> list = new ArrayList<>();
		Session session = null;
		Object setupCustomer = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			for(String customerId: customerIds){
				setupCustomer = session.get(SetupCustomer.class, customerId);
				if(setupCustomer != null){
					list.add((SetupCustomer) setupCustomer);
				}else{
					list.add(new SetupCustomer(customerId, 0, 0));
				}
			}
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("get SetupCustomer"+customerIds+" failed");
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
	
	public List<SetupCustomer> getAll(){
		List<SetupCustomer> list = new ArrayList<>();
		@SuppressWarnings("unchecked")
		Iterator<SetupCustomer> iterator = super.getAll("SetupCustomer").iterator();
		while(iterator.hasNext()){
			list.add(iterator.next().clone());
		}
		return list;
	}
}
