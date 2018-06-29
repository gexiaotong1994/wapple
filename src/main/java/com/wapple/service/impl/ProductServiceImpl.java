package com.wapple.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wapple.mapper.ProductDao;
import com.wapple.mapper.ProductMapper;
import com.wapple.pojo.Page;
import com.wapple.pojo.Product;
import com.wapple.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	ProductDao productDao;
	
	public boolean save(Product product) {
		
		return productMapper.insertProductMapper(product)>-0;
	}

	@Override
	public List<Product> products() {
		return productDao.queryListAll();
	}

	@Override
	public Product getProductById(int id) {
		return productDao.queryProductById(id);
	}

	@Override
	public Product getProductByName(String name) {
		return productDao.queryProductByName(name);
	}

	@Override
	public List<Product> productList(Page page) {
		// TODO Auto-generated method stub
		return productDao.queryList(page);
	}

	@Override
	public boolean modifyProduct(Product product) {
		
		return productDao.updateProduct(product)==1;
	}
	
	

}
