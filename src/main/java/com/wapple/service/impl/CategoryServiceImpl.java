package com.wapple.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wapple.mapper.CategoryMapper;
import com.wapple.pojo.Category;
import com.wapple.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryMapper categoryMapper;

	@Override
	public List<Category> list() {
		
		return categoryMapper.queryList();
	}
	
	

}
