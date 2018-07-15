package com.wapple.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wapple.mapper.VideoDao;
import com.wapple.pojo.Company;
import com.wapple.pojo.Country;
import com.wapple.pojo.Language;
import com.wapple.pojo.Video;
import com.wapple.pojo.VideoMillType;
import com.wapple.pojo.VideoType;
import com.wapple.service.VideoService;

import freemarker.core._RegexBuiltins.replace_reBI;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Service("videoService")
@Slf4j
public class VideoServiceImpl implements VideoService {

	@Autowired
	VideoDao videoDao;

	@Override
	public List<Country> getCountryList() {
		// TODO Auto-generated method stub
		return videoDao.queryCountryList();
	}

	@Override
	public List<Language> getLanguageList() {
		// TODO Auto-generated method stub
		return videoDao.queryLanguageList();
	}

	@Override
	public List<Company> getCompanyList() {
		// TODO Auto-generated method stub
		return videoDao.queryCompanyList();
	}

	@Override
	public List<VideoType> getVideoTypeList() {
		// TODO Auto-generated method stub
		return videoDao.queryVideoTypeList();
	}

	@Override
	public List<VideoMillType> getVideoMillTypeList() {
		// TODO Auto-generated method stub
		return videoDao.queryVideoMillTypeList();
	}

	@Override
	public void saveVideo(Video video) {
		int rowCount=videoDao.insertVideo(video);
		if (rowCount>0) {
			log.info("成功新增一条记录");
		}
	}

}
