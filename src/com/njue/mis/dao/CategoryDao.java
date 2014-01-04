package com.njue.mis.dao;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.njue.mis.model.Category;
import com.njue.mis.model.CustomerCategory;
import com.njue.mis.model.GoodsCategory;
import com.njue.mis.server.Server;


public class CategoryDao{
	@SuppressWarnings("finally")
	public int saveCategory(Category category)
	{
		Session session = null;
		int id = -1;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			if (!this.isCategoryExist(category)){
				id = (Integer) session.save(category);
			}
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("Save "+category+" failed");
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
	public Category getGoodsCategory(int cate_id)
	{
		Session session = null;
		Category cate = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			cate = (Category) session.get(GoodsCategory.class, cate_id);
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("get "+cate_id+" failed");
			if(session != null){
				session.getTransaction().rollback();
			}
		}finally{
			if(session != null){
				session.close();
			}
			return cate;
		}
	}
	@SuppressWarnings("finally")
	public Category getCustomerCategory(int cate_id)
	{
		Session session = null;
		Category cate = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			cate = (Category) session.get(CustomerCategory.class, cate_id);
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("get "+cate_id+" failed");
			if(session != null){
				session.getTransaction().rollback();
			}
		}finally{
			if(session != null){
				session.close();
			}
			return cate;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean delCategory(Category category)
	{
		Session session = null;
		boolean result = false;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.delete(category);
			session.getTransaction().commit();
			result = true;
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("delete "+category+" failed");
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
	public boolean updateCategory(Category category)
	{
		Session session = null;
		Category cate = null;
		boolean result = false;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			cate = (Category) session.get(category.getClass(), category.getCate_id());
			cate.setCate_name(category.getCate_name());
			cate.setPrefer_id(category.getPrefer_id());
			if(!isCategoryExist(cate)){
				session.getTransaction().commit();
				result = true;	
			}
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("update "+category+" failed");
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
	public List<Category> getAllGoodsCategory()
	{
		Session session = null;
		List<Category> list = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			String sql = "from GoodsCategory as cate order by cate.cate_id";
			Query query = session.createQuery(sql);
			list = new ArrayList<Category>(query.list());
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
	@SuppressWarnings({ "finally", "unchecked" })
	public List<Category> getAllCustomerCategory()
	{
		Session session = null;
		List<Category> list = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			String sql = "from CustomerCategory as cate order by cate.cate_id";
			Query query = session.createQuery(sql);
			list = new ArrayList<Category>(query.list());
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
	
	/*
	 * check if the category is exist.
	 */
    @SuppressWarnings("finally")
	private boolean isCategoryExist(Category category)
    {
    	Session session = null;
		boolean result = false;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			String sql = null;
			if(category instanceof GoodsCategory){
				sql = "from GoodsCategory where cate_name='"+category.getCate_name()+"' and prefer_id="+category.getPrefer_id();
			}else{
				sql = "from CustomerCategory where cate_name='"+category.getCate_name()+"' and prefer_id="+category.getPrefer_id(); 
			}
	    	Query query = session.createQuery(sql);
	    	@SuppressWarnings("unchecked")
			List<Category> list = query.list();
	    	if (list.size() > 0)
	    	{
	    		result = true;
	    	}
	    	session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("To check category is existed failed");
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
	public static void main(String[] args)
	{
		CategoryDao test = new CategoryDao();
		Category cate = test.getGoodsCategory(2);
		System.out.println(cate.getCate_id());
	}
}
