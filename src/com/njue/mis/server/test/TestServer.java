package com.njue.mis.server.test;

import com.njue.mis.server.Configure;

public class TestServer {
	public static void main(String[] args){
		Configure.init();
		System.out.println(Configure.getValue("IP"));
		System.out.println(Configure.getValue("OUTER"));
		System.out.println(Configure.CategoryController);
	}
}
