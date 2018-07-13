package com.wapple.service;

import java.util.List;

import com.wapple.pojo.Company;
import com.wapple.pojo.Country;
import com.wapple.pojo.Language;

public interface VideoService {

	/**
	 * 查询所有国家
	 * 
	 * @return
	 */
	List<Country> getCountryList();

	/**
	 * 查询所有的语言
	 * 
	 * @return
	 */
	List<Language> getLanguageList();

	/**
	 * 查询所有的公司
	 * 
	 * @return
	 */
	List<Company> getCompanyList();
	
	
}
