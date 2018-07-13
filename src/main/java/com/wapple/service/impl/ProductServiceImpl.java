package com.wapple.service.impl;

import java.util.List;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wapple.bo.ProductBo;
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

		return productMapper.insertProductMapper(product) > 0;
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

		return productDao.updateProduct(product) == 1;
	}

	@Override
	public boolean modifyProductByProductId(ProductBo productBo, int id) {
		Product product = new Product();
		BeanUtils.copyProperties(productBo, product);
		product.setId(id);
		return productDao.updateProduct(product) == 1;
	}

	@Override
	public Product getProductByProductNameAndCategoryName(String productName, String categoryName) {
		//TODO 
		return productDao.queryProductByCnameAndPname(productName, categoryName);
	}

}
