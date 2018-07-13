package com.wapple.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wapple.mapper.VideoDao;
import com.wapple.pojo.Company;
import com.wapple.pojo.Country;
import com.wapple.pojo.Language;
import com.wapple.service.VideoService;

@Service("videoService")
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

}
