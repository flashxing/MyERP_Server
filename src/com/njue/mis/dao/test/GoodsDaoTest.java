package com.njue.mis.dao.test;

import java.util.Iterator;
import java.util.Vector;

import com.njue.mis.common.CommonUtil;
import com.njue.mis.dao.GoodsHDao;
import com.njue.mis.model.Goods;

import junit.framework.TestCase;

public class GoodsDaoTest extends TestCase{
	private GoodsHDao goodsDao;
	private String goods1Id;
	private Goods goods1;
	private Goods goods2;
	public void setUp(){
		goodsDao = new GoodsHDao();
		goods1 = new Goods(CommonUtil.md5s("1tSP1"),"t商品1","上海","长1.1m","package","tSP1","promitCode","description", 12.5, 12.5*3,"providerID",2);
		goods2 = new Goods(CommonUtil.md5s("3tSP3"),"t商品3","上海","长1.1m","package","tSP3","promitCode","description", 12.5, 12.5*3,"providerID",2);
		
	}
	public void tearDown(){
		
	}
	
	public void testAddAndDelete(){
		int size = goodsDao.getAllGoods().size();
		goods1Id = goodsDao.addGoods(goods1);
		assertNotNull(goods1Id);
		Goods tmp_goods = goodsDao.getGoodsInfo(goods1Id);
		assertEquals(tmp_goods.getPrice(),12.5);
		tmp_goods.setPrice(13.5);
		goodsDao.deleteGoods(tmp_goods);
		Vector<Goods> vector = goodsDao.getAllGoods();
		assertEquals(vector.size(),size);
	}
	
	public void testGetGoodsByName(){
		Vector<Goods> vector = goodsDao.getAllGoodsByGoodsName("商品");
		assertTrue(vector.size()>0);
		Iterator<Goods> iter = vector.iterator();
		while(iter.hasNext()){
			Goods goods = iter.next();
			System.out.print(goods.getGoodsName()+" ");
		}
	}

}
