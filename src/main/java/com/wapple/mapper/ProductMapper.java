package com.wapple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.wapple.pojo.Product;

@Mapper
public interface ProductMapper {

	String insertProductSql = "INSERT INTO t_product(category_id,`name`,main_image,stock,`desc`,price,sales,`title`) VALUE(#{categoryId},#{name},#{mainImage},#{stock},#{desc},#{price},#{sales},#{title})";

	String queryProductListSql = "select * from t_product";

	@Insert(insertProductSql)
	int insertProductMapper(Product prodcut);
	
	
	
	
	

	
	

}
