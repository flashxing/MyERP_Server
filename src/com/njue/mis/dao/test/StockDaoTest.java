package com.njue.mis.dao.test;

import java.util.Iterator;
import java.util.List;

import com.njue.mis.dao.StockDao;
import com.njue.mis.model.Stock;

import junit.framework.TestCase;

public class StockDaoTest extends TestCase{
	private StockDao stockDao;
	public void setUp()throws Exception{
		stockDao = new StockDao();
	}
	
	public void tearDown() throws Exception{
		
	}
	
	public void testSaveStock0(){
		Stock stock = new Stock();
		stock.setGoodsId("test");
		stock.setNumber(100);
		int stock_id = stockDao.saveStock(stock);
		stock = stockDao.getStock(stock_id);
		assertEquals(stock.getNumber(), 100);
		assertEquals(stock.getGoodsId(), "test");
	}
	
	public void testDeleteStock(){
		List<Stock> list = stockDao.getAll();
		Iterator<Stock> iter = list.iterator();
		while(iter.hasNext()){
			Stock stock = (Stock) iter.next();
			stockDao.delStock(stock);
		}
		list = stockDao.getAll();
		assertEquals(list.size(),0);
	}
	
	public void testUpdateStock(){
		Stock stock = new Stock();
		stock.setGoodsId("test1");
		stock.setNumber(100);
		int stock_id = stockDao.saveStock(stock);
		stock = stockDao.getStock(stock_id);
		stock.setNumber(101);
		stockDao.updateStock(stock);
		assertEquals(stock.getNumber(),101);
	}
	
//	public static void main(String[] args){
//		StockDaoTest test = new StockDaoTest();
//		test.run();
//	}
}
