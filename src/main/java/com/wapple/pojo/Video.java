package com.wapple.pojo;

import java.util.Date;
import java.util.Locale;

import org.apache.ibatis.reflection.wrapper.BaseWrapper;
import org.springframework.cglib.core.Local;

import lombok.Data;

@Data
public class Video {
	private long id;
	private String name;
	private String nameCn;
	private String title;
	private int jf;// 需要多少积分
	private int type;// 大类
	private String mtypes;// 小雷
	private int companyId;// 出品公司
	private String director;// 导演
	private String actor;
	private int season;// 第几季
	private int episode;// 第几集 适合电视剧
	private int vipLevel;// 会员等级 0 免费 1普通会员 2
	private String mainImage;
	private String subImage;
	private String languageIds;
	private String subtitles;
	private int countryId;// 国家
	private int start;// 星级 5星为顶级
	private int status;
	private int weight; // 权重 排序用
	private Date createTime;
	private Date updateTime;
	private String desc;
	//一对一
	private VideoType videoType;
	
	
	
	
	

}
