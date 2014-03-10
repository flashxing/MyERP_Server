/**
 * ��������DAO
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
	 * ����µĽ�����¼
	 * @param portIn ��װ�õ�PortIn����
	 * @return ִ�н��
	 */
	public String addPort(Port port)
	{
		String id =(String) super.save(port);
		return id;
	}
	public boolean deletePort(Port port){
		return super.delete(port);
	}
	
	public boolean update(Port port){
		Object object = super.get(port.getClass(), port.getId());
		if(object == null){
			return false;
		}
		super.delete(object);
		return addPort(port)!=null;
	}
	
//	/**
//	 * ��ѯ���ݿ������������Ľ�����¼
//	 * @param field ��ѯ���ֶ�
//	 * @param value �����ֵ
//	 * @return ��ѯ���
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
	 * ��ѯ���ݿ������������Ľ�����¼
	 * @param beginTime ��ѯ�Ŀ�ʼʱ��
	 * @param endTime  ��ѯ�Ľ���ʱ��
	 * @return ��ѯ�����
	 */
	@SuppressWarnings({ "unchecked"})
	public Vector<PortBack> searchPortBackByTime(String beginTime,String endTime)
	{
		Vector<PortBack> tmp = super.getAll("PortBack where outporttime>'"+beginTime+"' and outporttime<'"+endTime+"'");
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
	 * ��ȡ���еĽ�����Ϣ
	 * @return ������Ϣ����
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
//	public boolean deletePortIn(Port port) {
//		// TODO Auto-generated method stub
//		Session session = null;
//		int time = CommonUtil.convertDateToInt(CommonUtil.getMyCurrentTime());
//		if(time == 0){
//			Server.logger.warn("Failed to save the Port for time is error"+portBack.getTime());
//			return false;
//		}
//		try{
//			session = HibernateUtil.getSession();
//			session.beginTransaction();
//			session.delete(port);
//			List<GoodsItem> list = port.getGoodsItemList();
//			String sql = null;
//			for(GoodsItem item : list){
//				String goodsId = item.getGoodsId();
//				int shId = port.getStoreHouseId();
//				int number = item.getNumber();
//				Stock stock = new Stock(goodsId,shId,number,time, item.getUnitPrice());
//				sql = "from Stock where goods_id = '"+goodsId+"' and sh_id="+shId+" and time <="+time+" order by time DESC";
//				Query query = session.createQuery(sql);
//				query.setMaxResults(1);
//				query.setFirstResult(0);
//				if(null != query.list()){
//					
//				}
//				List<Stock> stocks = query.list().get(0);
//				if(!stockService.deleteStock(stock)){
//					Server.logger.warn("Failed to delete stock:"+stock);
//					portDao.deletePort(portBack);
//					return null;
//				}
//			}
//			String sql = "from PortIn order by inporttime";
//			Query query = session.createQuery(sql);
//			list = new Vector<PortIn>(query.list());
//			session.getTransaction().commit();
//		}catch(HibernateException ex){
//			ex.printStackTrace();
//			Server.logger.warn("get all PortIn "+" failed");
//			if(session != null){
//				session.getTransaction().rollback();
//			}
//		}finally{
//			if(session != null){
//				session.close();
//			}
//			Vector<PortIn> vector = new Vector<PortIn>();
//			for(PortIn port:list){
//				vector.add(port.clone());
//			}
//			return vector;
//		}
//	}
//	public boolean deletePortBack(Port port) {
//		// TODO Auto-generated method stub
//		return false;
//	}
}
