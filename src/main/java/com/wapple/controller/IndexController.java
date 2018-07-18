package com.wapple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wapple.pojo.Page;
import com.wapple.pojo.Product;
import com.wapple.service.ProductService;
import com.wapple.service.VideoService;
import com.wapple.vo.VideoListVo;

@Controller
public class IndexController {

	@Autowired
	ProductService productService;

	@Autowired
	VideoService videoService;

	@RequestMapping("/web")
	public String index(Model model) {
		List<VideoListVo> videoListVos = videoService.getVideoList();
		model.addAttribute("videoListVos", videoListVos);
		return "index";

	}

	@RequestMapping("/{type}-{name}-{season}/")
	public String detail(Model model, @PathVariable("type") String type, @PathVariable("name") String name,
			@PathVariable("season") int season) {

		return "video-detail";
	}

	@RequestMapping("/lin/{par:\\-{3,5}}/")
	public String index1(Model model) {

		return "index";

	}

	@RequestMapping("/exception")
	public String error() {

		return "exception";
	}
}
