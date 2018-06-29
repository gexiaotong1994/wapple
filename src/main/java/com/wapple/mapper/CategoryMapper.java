package com.wapple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.wapple.pojo.Category;

@Mapper
public interface CategoryMapper {

	@Select("select * from t_category")
  	List<Category> queryList();
	
	

}
