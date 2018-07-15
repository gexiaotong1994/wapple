package com.wapple.service;

import java.util.List;

import com.wapple.pojo.Company;
import com.wapple.pojo.Country;
import com.wapple.pojo.Language;
import com.wapple.pojo.Video;
import com.wapple.pojo.VideoMillType;
import com.wapple.pojo.VideoType;

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
	
	/**
	 * 视频大类(影片类型)
	 * @return
	 */
	List<VideoType> getVideoTypeList();
	
	
	
	
	/**
	 * 视频小类(硬盘具体类型)
	 * @return
	 */
	List<VideoMillType> getVideoMillTypeList();
	
	
	
	/**
	 * 保存一条记录
	 * @param video
	 * @return
	 */
	void saveVideo(Video video);
	
}
