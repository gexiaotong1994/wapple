package com.wapple.service;

import java.util.List;

import com.wapple.pojo.Page;
import com.wapple.pojo.Product;

public interface ProductService {
	
	/**
	 * 保存一个新的商品
	 * @param product
	 * @return
	 */
	boolean save(Product product);
	
	
	
	/**
	 * 获取全部的商品
	 * @return
	 */
	List<Product> products();
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Product getProductById(int id);
	
	

	/**
	 * 根据商品名 获取商品
	 * @param name
	 * @return
	 */ 
	Product getProductByName(String name);
	
	
	/**
	 * 
	 * @param page
	 * @return
	 */
	List<Product> productList(Page page);
	
	
	
	/**
	 * 修改商品
	 * 
	 * @param product
	 * @return
	 */
	boolean modifyProduct(Product product);
	
	
	

}
