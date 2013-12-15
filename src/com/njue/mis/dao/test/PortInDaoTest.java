package com.njue.mis.dao.test;

import java.util.ArrayList;
import java.util.List;

import com.njue.mis.common.CommonUtil;
import com.njue.mis.dao.GoodsHDao;
import com.njue.mis.dao.PortDAO;
import com.njue.mis.model.Goods;
import com.njue.mis.model.GoodsItem;
import com.njue.mis.model.PortIn;

import junit.framework.TestCase;

public class PortInDaoTest  extends TestCase{
	private PortDAO dao;
	private PortIn portIn;
	public void setUp(){
		dao = new PortDAO();
		List<GoodsItem> list = new ArrayList<GoodsItem>();
		GoodsItem item = new GoodsItem(0,"portIn","goods",10,10.0,"comment");
		list.add(item);
		portIn = new PortIn("portin", "test", 1, 1, 0, "2013-11-12 22:55:08", "test",
				"test",""+123, list);
	}
	public void tearDown(){
		
	}
	
	public void testAddAndDelete(){
		dao.addPort(portIn);
	}
}
