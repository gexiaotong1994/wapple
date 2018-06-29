package com.wapple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wapple.pojo.Page;
import com.wapple.pojo.Product;

public interface ProductDao {

	List<Product> queryListAll();

	List<Product> queryList(@Param("page") Page page);

	Product queryProductById(int id);

	Product queryProductByName(String name);

	int updateProduct(Product product);

}
