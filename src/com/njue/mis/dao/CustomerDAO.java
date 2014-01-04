/**
 * 客户DAO类，主要对客户信息进行管理
 */
package com.njue.mis.dao;

import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.njue.mis.model.Customer;
import com.njue.mis.model.Goods;
import com.njue.mis.server.Server;

public class CustomerDAO extends CommonObjectDao
{

	public CustomerDAO()
	{
		super();
	}
	/**
	 * 向数据库中添加新客户
	 * @param customer 封装好的客户
	 * @return 执行结果
	 */
	public String addCustomer(Customer customer)
	{
		String id = (String) super.save(customer);
		return id;
	}
	/**
	 * 从数据库中删除指定客户的信息，由于存在外键关系，此处仅设置表中 available=0
	 * @param id 被删除客户的编号
	 * @return 执行结果
	 */
	public boolean deleteCustomer(String id)
	{
		Customer customer = getCustomer(id);
		if (customer != null){
			return super.delete(customer);
		}else{
			return false;
		}
	}
	/**
	 * 查询数据库中满足条件的客户
	 * @param field 查询的字段
	 * @param value 满足的值
	 * @return 查询结果
	 */
//	public Vector<Customer> searchCustomer(String field,String value)
//	{
//		Vector<Customer> result=new Vector<Customer>();
//		try
//		{
//			String sql="{call pr_searchCustomer(?,?)}";
//			Object[] params=new Object[]{field,value};
//			ResultSet rs=manage.executeQuery(sql, params, Constants.CALL_TYPE);
//			while(rs.next())
//			{
//				Customer customer=new Customer(rs.getString("id"),rs.getString("customerName"),rs.getString("zip"),
//									  rs.getString("address"),rs.getString("telephone"),rs.getString("fax"),
//									  rs.getString("connectionPerson"),rs.getString("phone"),rs.getString("email"),
//									  rs.getString("bank"),rs.getString("account"));
//				result.add(customer);
//			}
//			manage.closeDB();
//		}
//		catch (Exception e)
//		{
//			ErrorManager.printError("CustomerDAO.searchCustomer", e);
//		}
//		return result;
//	}
	/**
	 * 修改客户信息
	 * @param customer 封装好的客户新信息
	 * @return 执行结果
	 */
	public boolean updateCustomer(Customer customer){
		return super.update(customer);
	}
	/**
     * 显示所有的客户
     * @return 客户集合
     */
    @SuppressWarnings({ "unchecked", "finally" })
	public Vector<Customer> getAllCustomer()
    {
    	Session session = null;
		Vector<Customer> vec = null ;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			String sql = "from Customer order by id";
			Query query = session.createQuery(sql);
			vec = new Vector<Customer>(query.list());
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("get all customer "+" failed");
			if(session != null){
				session.getTransaction().rollback();
			}
		}finally{
			if(session != null){
				session.close();
			}
			return vec;
		}
    }
    /**
     * 获取特定客户的信息
     * @param id 客户编号
     * @return 查询结果
     */
    public Customer getCustomer(String id)
    {
    	return (Customer) super.get(Customer.class, id);    
    }
    
    public boolean isExisted(String id){
    	return getCustomer(id) != null;
    }
    
	@SuppressWarnings({ "unchecked", "finally" })
	public Vector<Customer> getAllCustomerByCategory(int cateId){
		Session session = null;
		Vector<Customer> list = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			String sql = "from Customer where cate_id = '"+cateId+"'";
			Query query = session.createQuery(sql);
			list = new Vector<Customer>(query.list());
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("get all Customer by category id: "+cateId+" failed");
			if(session != null){
				session.getTransaction().rollback();
			}
			list = null;
		}finally{
			if(session != null){
				session.close();
			}
			return list;
		}
	}
	@SuppressWarnings({ "unchecked", "finally" })
	public Vector<Customer> searchCustomerByName(String name) {
		// TODO Auto-generated method stub
		Session session = null;
		Vector<Customer> list = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			String sql = "from Customer where customername like '%"+name+"%'";
			Query query = session.createQuery(sql);
			list = new Vector<Customer>(query.list());
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("search all Customer by customer name: "+name+" failed");
			if(session != null){
				session.getTransaction().rollback();
			}
			list = null;
		}finally{
			if(session != null){
				session.close();
			}
			return list;
		}
	}
}
