package com.njue.mis.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Vector;

import com.njue.mis.model.Sales;
import com.njue.mis.model.SalesBack;
import com.njue.mis.model.SalesIn;

public interface SalesControllerInterface extends Remote{
	public String addSalesBack(SalesBack sales) throws RemoteException;
	
	public String addSalesIn(SalesIn sales) throws Exception;
	
	public Sales getSalesIn(String id) throws Exception;
	
	public Sales getSalesBack(String id) throws Exception;
	
	public boolean updateSales(Sales sales) throws Exception;
	
	public boolean deleteSales(Sales sales) throws Exception;
	
	public Vector<Sales> getAllSalesIn() throws Exception;
	
	public Vector<Sales> getAllSalesBack() throws Exception;
	
	public Vector<Sales> getAllSalesInByTime(String begin, String end) throws Exception;
	
	public Vector<Sales> getAllSalesBackByTime(String begin, String end) throws Exception;
	
	/*
	 * ��һ�����۲ݸ�����
	 */
	public boolean setSalesToPublished(SalesIn salesIn) throws Exception;
	/*
	 * ��ȡ�������۲ݸ�
	 */
	public List<SalesIn> getAllSalesDraft() throws Exception;
	
	/*
	 * ��ȡһ��ʱ��ε��������۲ݸ�
	 */
	public List<SalesIn> getAllSalesDraft(String begin, String end) throws Exception;
}
