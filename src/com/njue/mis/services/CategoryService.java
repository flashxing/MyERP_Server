package com.njue.mis.services;


import java.util.List;
import java.util.Vector;


import com.njue.mis.dao.CategoryDao;
import com.njue.mis.model.Category;
import com.njue.mis.model.Customer;
import com.njue.mis.model.Goods;
import com.njue.mis.server.Server;

public class CategoryService{
	private CategoryDao categoryDao = new CategoryDao();
	private CustomerService customerService = new CustomerService();
	private GoodsService goodsService = new GoodsService();
	public CategoryService(){
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.njue.mis.handler.CategoryServiceHandler#addCategory(com.njue.mis.model.Category)
	 */
	public int addCategory(Category category){
		// TODO Auto-generated method stub
		Server.logger.info("add a category "+category);
		return categoryDao.saveCategory(category);
	}
	/*
	 * (non-Javadoc)
	 * @see com.njue.mis.handler.CategoryServiceHandler#delCategory(com.njue.mis.model.Category)
	 */
	public boolean delCategory(Category category){
		// TODO Auto-generated method stub
		Server.logger.info("delete a category "+category);
		return categoryDao.delCategory(category);
	}
	/*
	 * (non-Javadoc)
	 * @see com.njue.mis.handler.CategoryServiceHandler#updateCategory(com.njue.mis.model.Category)
	 */
	public boolean updateCategory(Category category){
		// TODO Auto-generated method stub
		Server.logger.info("update a category "+category);
		return categoryDao.updateCategory(category);
	}
	/*
	 * (non-Javadoc)
	 * @see com.njue.mis.handler.CategoryServiceHandler#getAllCategory()
	 */
	public List<Category> getAllGoodsCategory() {
		// TODO Auto-generated method stub
		return categoryDao.getAllGoodsCategory();
	}
	public List<Category> getAllCustomerCategory(){
		return categoryDao.getAllCustomerCategory();
	}
	/*
	 * (non-Javadoc)
	 * @see com.njue.mis.handler.CategoryServiceHandler#getCategory(int)
	 */
	public Category getGoodsCategory(int cate_id) {
		// TODO Auto-generated method stub
		return categoryDao.getGoodsCategory(cate_id);
	}
	
	public Category getCustomerCategory(int cate_id){
		return categoryDao.getCustomerCategory(cate_id);
	}

	public boolean categoryHasCustomer(int cateId) {
		// TODO Auto-generated method stub
		Vector<Customer> vec = customerService.getAllCustomerByCategory(cateId);
		return (vec!=null)&&(vec.size()>0);
	}
	public boolean categoryHasGoods(int cateId){
		Vector<Goods> vec = goodsService.getAllGoodsByCateId(cateId);
		return (vec!=null)&&(vec.size()>0);
	}
}
