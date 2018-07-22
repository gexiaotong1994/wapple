package com.wapple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wapple.params.VideoListParams;
import com.wapple.pojo.Video;
import com.wapple.pojo.VideoList;
import com.wapple.service.ProductService;
import com.wapple.service.VideoService;
import com.wapple.vo.VideoListVo;
import com.wapple.vo.VideoVo;

@Controller
public class VideoController {
	@Autowired
	ProductService productService;

	@Autowired
	VideoService videoService;

	@RequestMapping("/top/")
	public String top(Model model) {
		List<VideoList> videoListVos = videoService.getVideoListByParams(new VideoListParams(0, 4));
		model.addAttribute("videoList", videoListVos);
		return "index";

	}

	@RequestMapping("/{tname}/{name}/{season}/")
	public String detail(@PathVariable("tname") String typeName, @PathVariable("name") String name,
			@PathVariable("season") int season, Model model) {
		VideoVo videoVo = videoService.getVideoDetail(typeName, name, season);
		model.addAttribute("v", videoVo);
		return "detail";
	}
}
