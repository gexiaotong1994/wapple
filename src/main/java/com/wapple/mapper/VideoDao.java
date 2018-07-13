package com.wapple.mapper;

import java.util.List;

import com.wapple.pojo.Company;
import com.wapple.pojo.Country;
import com.wapple.pojo.Language;

public interface VideoDao {

	/**
	 * 查询所有国家
	 * 
	 * @return
	 */
	List<Country> queryCountryList();

	/**
	 * 查询所有的语言
	 * 
	 * @return
	 */
	List<Language> queryLanguageList();

	/**
	 * 查询所有的公司
	 * 
	 * @return
	 */
	List<Company> queryCompanyList();

}
