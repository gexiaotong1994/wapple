package com.wapple.vo;

import java.util.List;

import lombok.Data;

@Data
public class VideoVo {

	/**
	 * 以上字段属性直接copy
	 */
	private long id;
	private String name;
	private String nameCn;
	private String title;
	private String director;// 导演
	private String actor;
	private int season;// 第几季
	private int episode;// 第几集 适合电视剧
	private int vipLevel;// 会员等级 0 免费 1普通会员 2
	private String mainImage;
	private String desc;
	
	
	/**
	 * 以下字段需要自行填充
	 */
	private String typeStr;// 大类
	private String mtypesStr;// 小雷
	private String companyStr;// 出品公司
	private String languageIdsStr;
	private String subtitlesStr;
	private String countryStr;// 国家
	
	private List<Integer> seasonList;
}
