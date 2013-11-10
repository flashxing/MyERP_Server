package com.njue.mis.controller;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.njue.mis.interfaces.CategoryControllerInterface;
import com.njue.mis.model.Category;
import com.njue.mis.server.Server;
import com.njue.mis.services.CategoryService;

public class CategoryController extends UnicastRemoteObject implements CategoryControllerInterface{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8685315352769388296L;
	/**
	 * 
	 */
	private CategoryService categoryService = new CategoryService();
	public CategoryController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.njue.mis.handler.CategoryServiceHandler#addCategory(com.njue.mis.model.Category)
	 */
	@Override
	public int addCategory(Category category) throws RemoteException {
		// TODO Auto-generated method stub
		Server.logger.info("add a category "+category);
		return categoryService.addCategory(category);
	}
	/*
	 * (non-Javadoc)
	 * @see com.njue.mis.handler.CategoryServiceHandler#delCategory(com.njue.mis.model.Category)
	 */
	@Override
	public boolean delCategory(Category category) throws RemoteException {
		// TODO Auto-generated method stub
		Server.logger.info("delete a category "+category);
		return categoryService.delCategory(category);
	}
	/*
	 * (non-Javadoc)
	 * @see com.njue.mis.handler.CategoryServiceHandler#updateCategory(com.njue.mis.model.Category)
	 */
	@Override
	public boolean updateCategory(Category category) throws RemoteException {
		// TODO Auto-generated method stub
		Server.logger.info("update a category "+category);
		return categoryService.updateCategory(category);
	}
	/*
	 * (non-Javadoc)
	 * @see com.njue.mis.handler.CategoryServiceHandler#getAllCategory()
	 */
	@Override
	public List<Category> getAllGoodsCategory() throws RemoteException {
		// TODO Auto-generated method stub
		return categoryService.getAllGoodsCategory();
	}
	
	@Override
	public List<Category> getAllCustomerCategory() throws RemoteException {
		// TODO Auto-generated method stub
		return categoryService.getAllCustomerCategory();
	}
	/*
	 * (non-Javadoc)
	 * @see com.njue.mis.handler.CategoryServiceHandler#getCategory(int)
	 */
	@Override
	public Category getGoodsCategory(int cate_id) throws RemoteException {
		// TODO Auto-generated method stub
		return categoryService.getGoodsCategory(cate_id);
	}
	@Override
	public Category getCustomerCategory(int cate_id) throws RemoteException {
		// TODO Auto-generated method stub
		return categoryService.getCustomerCategory(cate_id);
	}

	@Override
	public boolean categoryHasCustomer(int cate_id) throws RemoteException {
		// TODO Auto-generated method stub
		return categoryService.categoryHasCustomer(cate_id);
	}

	@Override
	public boolean categoryHasGoods(int cateId) throws RemoteException {
		// TODO Auto-generated method stub
		return categoryService.categoryHasGoods(cateId);
	}
}
