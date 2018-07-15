package com.wapple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wapple.pojo.Company;
import com.wapple.pojo.Country;
import com.wapple.pojo.Language;
import com.wapple.pojo.Video;
import com.wapple.pojo.VideoMillType;
import com.wapple.pojo.VideoType;

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

	/**
	 * 视频大类
	 * 
	 * @return
	 */
	List<VideoType> queryVideoTypeList();

	/**
	 * 视频小类
	 * 
	 * @return
	 */
	List<VideoMillType> queryVideoMillTypeList();

	/**
	 * 插入一条视频记录
	 * @param video
	 * @return
	 */
	int insertVideo(@Param("video") Video video);

}
