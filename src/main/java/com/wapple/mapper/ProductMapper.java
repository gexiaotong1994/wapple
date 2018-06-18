package com.wapple.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.wapple.pojo.Product;

public interface ProductMapper {

	
	
	int insertProductMapper(Product prodcut);
	
	
	
	
}
