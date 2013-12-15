/**
 * 进货单类DAO
 */
package com.njue.mis.dao;

import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.njue.mis.model.Port;
import com.njue.mis.model.PortBack;
import com.njue.mis.model.PortIn;
import com.njue.mis.server.Server;

public class PortDAO extends CommonObjectDao
{
	public PortDAO()
	{
		super();
	}
	/**
	 * 添加新的进货记录
	 * @param portIn 封装好的PortIn对象
	 * @return 执行结果
	 */
	public String addPort(Port port)
	{
		String id =(String) super.save(port);
		return id;
	}
	public boolean deletePort(Port port){
		return super.delete(port);
	}
	
//	/**
//	 * 查询数据库中满足条件的进货记录
//	 * @param field 查询的字段
//	 * @param value 满足的值
//	 * @return 查询结果
//	 */
//	public Vector<PortIn> searchPortIn(String field,String value)
//	{
//		Vector<PortIn> result=new Vector<PortIn>();
//		try
//		{
//			String sql="{call pr_searchPortIn(?,?)}";
//			Object[] params=new Object[]{field,value};
//			ResultSet rs=manager.executeQuery(sql, params, Constants.CALL_TYPE);
//			while(rs.next())
//			{
//				PortIn portIn=new PortIn(rs.getString("id"),rs.getString("providerid"),rs.getString("goodsid"),
//									  rs.getString("paytype"),rs.getInt("number"),rs.getDouble("price"),
//									  rs.getString("inporttime"),rs.getString("operateperson"),rs.getString("comment"));
//				result.add(portIn);
//			}
//			manager.closeDB();
//		}
//		catch (Exception e)
//		{
//			ErrorManager.printError("PortInDAO.searchPortIn", e);
//		}
//		return result;
//	}
	/**
	 * 查询数据库中满足条件的进货记录
	 * @param beginTime 查询的开始时间
	 * @param endTime  查询的结束时间
	 * @return 查询结果集
	 */
	@SuppressWarnings({ "unchecked"})
	public Vector<PortBack> searchPortBackByTime(String beginTime,String endTime)
	{
		Vector<PortBack> tmp = super.getAll("PortBack where outporttime>'"+beginTime+"' and outporttime<'"+endTime);
		Vector<PortBack> vector = new Vector<PortBack>();
		for(PortBack port:tmp){
			vector.add(port.clone());
		}
		return vector;
	}
	@SuppressWarnings({ "unchecked", "finally" })
	public Vector<PortIn> searchPortInByTime(String beginTime,String endTime)
	{
		Session session = null;
		Vector<PortIn> list = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			String sql = "from PortIn where inporttime>'"+beginTime+"' and inporttime<'"+endTime+"'";
			Query query = session.createQuery(sql);
			list = new Vector<PortIn>(query.list());
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("get all PortIn by time "+" failed");
			if(session != null){
				session.getTransaction().rollback();
			}
		}finally{
			if(session != null){
				session.close();
			}
			Vector<PortIn> vector = new Vector<PortIn>();
			for(PortIn port:list){
				vector.add(port.clone());
			}
			return vector;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public Vector<PortBack> getAllPortBack()
	{
		Vector<PortBack> tmp = super.getAll("PortBack order by outporttime");
		Vector<PortBack> vector = new Vector<PortBack>();
		for(PortBack port:tmp){
			vector.add(port.clone());
		}
		return vector;
	}
	/**
	 * 获取所有的进货信息
	 * @return 进货信息集合
	 */
	@SuppressWarnings({ "unchecked", "finally" })
	public Vector<PortIn> getAllPortIn()
	{
		Session session = null;
		Vector<PortIn> list = null;
		try{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			String sql = "from PortIn order by inporttime";
			Query query = session.createQuery(sql);
			list = new Vector<PortIn>(query.list());
			session.getTransaction().commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			Server.logger.warn("get all PortIn "+" failed");
			if(session != null){
				session.getTransaction().rollback();
			}
		}finally{
			if(session != null){
				session.close();
			}
			Vector<PortIn> vector = new Vector<PortIn>();
			for(PortIn port:list){
				vector.add(port.clone());
			}
			return vector;
		}
	}
//	public static void main(String[] args)
//	{
//		PortInDAO in=new PortInDAO();
//		Vector<PortIn> vector=in.searchPortInByTime("2001-08-02","2010-01-01");
//		System.err.println(vector.size());
//	}
}
