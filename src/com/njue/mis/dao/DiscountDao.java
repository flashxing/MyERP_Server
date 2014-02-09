package com.njue.mis.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.njue.mis.model.Discount;
import com.njue.mis.server.Server;

public class DiscountDao extends CommonObjectDao{
	public String save(Discount discount){
		return (String) super.save(discount);
	}
	
	public Discount get(String customerId){
		Discount tmp = (Discount) super.get(Discount.class, customerId);
		if(tmp == null){
			return null;
		}
		Discount discount = new Discount((Discount) tmp);
		return discount;
	}
	public boolean delete(String customerId){
		Discount discount = get(customerId);
		if(discount == null){
			return true;
		}
		return super.delete(discount);
	}
	
	@SuppressWarnings("finally")
	public boolean update(Discount discount){
		Session session = null;
		boolean result = false;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Discount tmp_discount = (Discount) session.get(discount.getClass(), discount.getCustomerId());
			tmp_discount.update(discount);
			session.getTransaction().commit();
			result = true;	
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("update "+discount+" failed");
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
