package com.wapple.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.patterns.PerSingleton;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wapple.bo.VideoParams;
import com.wapple.mapper.ProductDao;
import com.wapple.mapper.VideoDao;
import com.wapple.pojo.Company;
import com.wapple.pojo.Country;
import com.wapple.pojo.Language;
import com.wapple.pojo.User;
import com.wapple.pojo.Video;
import com.wapple.pojo.VideoList;
import com.wapple.pojo.VideoMillType;
import com.wapple.pojo.VideoType;
import com.wapple.service.VideoService;
import com.wapple.vo.VideoListVo;

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
		int rowCount = videoDao.insertVideo(video);
		if (rowCount > 0) {
			log.info("成功新增一条记录");
		}
	}

	@Override
	public List<VideoListVo> getVideoList() {
		List<VideoList> list = videoDao.queryVideoList();
		List<VideoListVo> list2 = new ArrayList<>();
		for (VideoList videoList : list) {
			VideoListVo videoListVo = new VideoListVo();
			BeanUtils.copyProperties(videoList, videoListVo);
			String tps = this.millIdsToString(videoList.getMtypes());
			videoListVo.setMtypeStr(tps);
			videoListVo.setUrl(videoDao.queryTypeNameCnById(videoList.getType()) + "-" + videoList.getName() + "-"
					+ videoList.getSeason());
			list2.add(videoListVo);
		}
		return list2;
	}

	private String millIdsToString(String ids) {
		if (ids.indexOf(",") < -1) {
			return videoDao.queryMillNameCnById(Integer.parseInt(ids));
		}
		StringBuffer buffer = new StringBuffer();
		String[] idarr = ids.split(",");
		for (String str : idarr) {
			String tname = videoDao.queryMillNameCnById(Integer.parseInt(str));
			buffer.append(tname).append(",");
		}
		String nas = buffer.toString();

		return nas.substring(0, nas.length() - 1);

	}

	@Override
	public Video getVideo(String type, String name, int season) {

		return videoDao.queryVideoByTypeAndNameAndSeason(type, name, season);
	}

	
	
	@Override
	public Video getNextVideoToNameAndType(String videoName, int type) {
		int rowCount = videoDao.countVideoByNameAndType(videoName, type);
		VideoParams params = new VideoParams(type);
		params.setName(videoName);
        params.setSeason(rowCount);
        Video video=videoDao.queryVideoByParams(params);
        if (video!=null) {
			video.setSeason(rowCount+1);
		}
		return video;

	}
	
	
	

} 
