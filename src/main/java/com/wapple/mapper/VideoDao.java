package com.wapple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wapple.bo.VideoParams;
import com.wapple.params.VideoListParams;
import com.wapple.pojo.Company;
import com.wapple.pojo.Country;
import com.wapple.pojo.Language;
import com.wapple.pojo.Video;
import com.wapple.pojo.VideoList;
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
	 * 
	 * @param video
	 * @return
	 */
	int insertVideo(@Param("video") Video video);

	/**
	 * 根据条件查询出各种需要的参数
	 * 
	 * @return
	 */
	List<VideoList> queryVideoList();

	/**
	 * 根据id查询出主键的汉语
	 * 
	 * @param id
	 * @return
	 */
	String queryMillNameCnById(@Param("id") int id);

	/**
	 * 根据主键查询大类的
	 * 
	 * @param id
	 * @return
	 */
	String queryTypeNameCnById(@Param("id") int id);

	/**
	 * 根据 手视频类型 视频名 多少季 精确查找 视频
	 * 
	 * @param type
	 * @param name
	 * @param season
	 * @return
	 */
	Video queryVideoByTypeAndNameAndSeason(@Param("type") String type, @Param("name") String name,
			@Param("season") int season);

	/**
	 * 直接查询出系列
	 * 
	 * @param name
	 * @return
	 */
	int countVideoByNameAndType(@Param("name") String name, @Param("type") int type);

	/**
	 * 通过实体参数 进行查询
	 * 
	 * @param videoParams
	 * @return
	 */
	Video queryVideoByParams(@Param("params") VideoParams videoParams);

	/**
	 * 根据主键删除视频
	 * 
	 * @param id
	 * @return
	 */
	int del(@Param("id") long id);

	/**
	 * 根据参数实体查询
	 * 
	 * @param videoListParams
	 * @return
	 */
	List<VideoList> queryVideoListByParams(@Param("params") VideoListParams videoListParams);

	/**
	 * 根据id获取国家中文名
	 * 
	 * @param id
	 * @return
	 */
	String queryCountryNameCnById(@Param("id") int id);

	/**
	 * 根据id获取语言中文名
	 * 
	 * @param id
	 * @return
	 */
	String queryLanNameCnById(@Param("id") int id);

	/**
	 * 根据id获取出品公司名
	 * 
	 * @param id
	 * @return
	 */
	String queryCompanyNameCnById(@Param("id") int id);
	
	
	
	/**
	 * 根据视频名查出所有的技术了班的 卡拉拉裤，
	 * @param name
	 * @return
	 */
	List<Integer> querySeasonListByVideoName(@Param("name") String name);
}
