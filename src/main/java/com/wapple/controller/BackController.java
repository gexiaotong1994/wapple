package com.wapple.controller;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.soap.Detail;

import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.druid.sql.ast.SQLStructDataType.Field;
import com.google.common.collect.Lists;
import com.mchange.v2.async.StrandedTaskReporting;
import com.wapple.bo.ProductBo;
import com.wapple.common.Json;
import com.wapple.enums.UserStatusEnum;
import com.wapple.mapper.VideoDao;
import com.wapple.pojo.Product;
import com.wapple.pojo.User;
import com.wapple.pojo.UserIndex;
import com.wapple.pojo.Video;
import com.wapple.pojo.VideoList;
import com.wapple.service.ProductService;
import com.wapple.service.UserService;
import com.wapple.service.VideoService;
import com.wapple.util.DateTimeUtil;
import com.wapple.vo.ProductListVo;
import com.wapple.vo.UserListVo;
import com.wapple.vo.VideoListVo;

import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;

@Controller
@RequestMapping("/back")
public class BackController {

	@Autowired
	public ProductService productService;

	@Autowired
	public VideoService videoService;

	@Autowired
	public UserService userService;

	public static final String view(String viewName) {
		return new StringBuffer().append("forward:").append("/admin/admin_").append(viewName).append(".jsp").toString();
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return view("login");
	}

	/**
	 * 修改商品
	 * 
	 * @param productBo
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Json<String> productUpdate(ProductBo productBo, @PathVariable("id") int id) {
		boolean suc = productService.modifyProductByProductId(productBo, id);
		if (suc) {
			return Json.success("商品[" + id + "]修改成功");
		}
		// System.out.println(product);
		return Json.fail("商品[" + id + "]修改成功");
	}

	/**
	 * 后台删除一条记录
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Json<String> productDetail(@PathVariable("id") int productId) {
		System.out.println();
		return Json.fail();
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, String returnUrl) throws UnsupportedEncodingException {
		Json<UserIndex> json = userService.login(username, password);
		if (!json.isSuccess()) {
			return "redirect:login#" + URLEncoder.encode(json.getMsg(), "UTF-8").toString();

		}
		UserIndex userIndex = json.getData();

		return view("login");
	}

	@RequestMapping("index")
	public String index() {
		return view("index");
	}

	@RequestMapping(value = "/videos/new", method = RequestMethod.GET)
	public String viewNew(Model model) {
		// 所属国家
		model.addAttribute("countrys", videoService.getCountryList());
		// 影片语言 字幕语言
		model.addAttribute("languages", videoService.getLanguageList());
		// 视频初品公司
		model.addAttribute("companys", videoService.getCompanyList());
		// 视频大类
		model.addAttribute("videoTypes", videoService.getVideoTypeList());
		// 视频小类
		model.addAttribute("videoMillTypes", videoService.getVideoMillTypeList());
		return view("video_new");
	}

	/**
	 * 处理新增视频的请求
	 * 
	 * @param model
	 * @param name
	 * @param nameCn
	 * @param videoTypeId
	 * @param countryId
	 * @param companyId
	 * @param director
	 * @param actor
	 * @param season
	 * @param episode
	 * @param jf
	 * @param vipLevel
	 * @param languageArr
	 * @param subtitleArr
	 * @param videoMillTypeArr
	 * @param start
	 * @param weight
	 * @param desc
	 * @param mainImageFile
	 * @return
	 */
	@RequestMapping(value = "/videos/new", method = RequestMethod.POST)
	public String viewNew(Model model, String name, String nameCn, Integer videoTypeId, Integer countryId,
			Integer companyId, String director, String actor, Integer season, Integer episode, Integer jf,
			Integer vipLevel, String title, Integer[] languageArr, Integer[] subtitleArr, Integer[] videoMillTypeArr,
			Integer start, Integer weight, String desc,
			@RequestParam("mainImageFile") CommonsMultipartFile mainImageFile) throws Exception {

		Video video = new Video();
		video.setName(name.toLowerCase());
		video.setTitle(title);
		video.setNameCn(new StringBuffer(title).append(" ").append("第").append(season).append("季").toString());
		video.setType(videoTypeId);
		video.setCountryId(countryId);
		video.setCompanyId(companyId);
		video.setDirector(director);
		video.setActor(actor);
		video.setSeason(season);
		video.setEpisode(episode);
		video.setJf(jf);
		video.setVipLevel(vipLevel);
		video.setStart(start);
		video.setWeight(weight);
		video.setDesc(desc);
		String languString = this.intArrToString(languageArr);
		String subjetString = this.intArrToString(subtitleArr);
		String videoMillType = this.intArrToString(videoMillTypeArr);
		video.setLanguageIds(languString);
		video.setSubtitles(subjetString);
		video.setMtypes(videoMillType);
		// 开始处理图片 写入到服务器中
		String mianImageName = System.currentTimeMillis() + "-" + name + ".png";
		String filePath = "E:\\webimage\\" + mianImageName;
		String descPath = "E:\\webimage\\suo\\" + System.currentTimeMillis() + name + ".png";

		File file = new File(filePath);
		video.setMainImage(mianImageName);
		try {
			mainImageFile.transferTo(file);
			// this.zoomImage(filePath, descPath, 300, 350);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		videoService.saveVideo(video);
		System.out.println(name);

		// TODO//ubunt
		return "redirect:/back/videos/?code=202";
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String product(Model model) {
		List<Product> products = productService.products();
		List<ProductListVo> productListVos = Lists.newArrayList();
		for (Product product : products) {
			productListVos.add(this.proudctToProductListVo(product));
		}
		model.addAttribute("productListVos", productListVos);
		return view("product");
	}

	@RequestMapping("country/add")
	public Boolean countryAdd(String name, String nameCn, int utc) {

		return false;
	}

	@RequestMapping(value = "/users/", method = RequestMethod.GET)
	public String user(Model model) {
		List<User> userList = userService.userList();
		List<UserListVo> userVoList = Lists.newArrayList();
		for (User user : userList) {
			UserListVo userListVo = new UserListVo();
			BeanUtils.copyProperties(user, userListVo);
			userListVo.setCreateTime(DateTimeUtil.dateToStr(user.getCreateTime()));
			userListVo.setStatusMsg(UserStatusEnum.getValueByCode(user.getStatus()));
			userVoList.add(userListVo);
		}
		model.addAttribute("userList", userVoList);
		return view("user");
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public String productDetail(Model model, @PathVariable("id") int productId) {
		Product product = productService.getProductById(productId);
		model.addAttribute("product", product);
		return view("product_detail");
	}

	/**
	 * 获取用户详情
	 * 
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public String userDetail(Model model, @PathVariable("id") int userId) {
		// pon..dmvldkj
		User user = userService.getUserByUserId(userId);
		model.addAttribute("user", user);
		return view("user_detail");
	}

	@RequestMapping("/videos/")
	public String videoList(Model model) {
		List<VideoListVo> videoListVos = videoService.getVideoList();
		model.addAttribute("videoVos", videoListVos);
		return view("video_list");

	}

	@RequestMapping("/videos/quickAdd")
	public String videoNextAdd(Model model, int videoType, String videoName) {
		Video nextVideo = videoService.getNextVideoToNameAndType(videoName, videoType);
		model.addAttribute("video", nextVideo);
		// 所属国家
		model.addAttribute("countrys", videoService.getCountryList());
		// 影片语言 字幕语言
		model.addAttribute("languages", videoService.getLanguageList());
		// 视频初品公司
		model.addAttribute("companys", videoService.getCompanyList());
		// 视频大类
		model.addAttribute("videoTypes", videoService.getVideoTypeList());
		// 视频小类
		model.addAttribute("videoMillTypes", videoService.getVideoMillTypeList());
		return view("video_quick_add");

	}

	/**
	 * 根据主键删除视频
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/videos/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean del(@PathVariable("id") long id) {

		return videoService.delete(id);

	}

	public ProductListVo proudctToProductListVo(Product p) {
		ProductListVo productListVo = new ProductListVo();
		BeanUtils.copyProperties(p, productListVo);
		productListVo.setVname(p.getName().substring(0, 10));
		productListVo.setCreateTime(DateTimeUtil.dateToStr(p.getCreateTime()));
		productListVo.setUpdateTime(DateTimeUtil.dateToStr(p.getUpdateTime()));
		if (StringUtils.equals(p.getMainImage(), "default.jpg")) {
			productListVo.setImage(false);
		} else {
			productListVo.setImage(true);
		}
		return productListVo;

	}

	private String intArrToString(Integer[] arr) {
		StringBuffer sbf = new StringBuffer();
		for (int i : arr) {
			sbf.append(i).append(",");
		}
		return sbf.toString().substring(0, sbf.toString().length() - 1);
	}

	/**
	 * 等比例缩放图片
	 * 
	 * @param src
	 * @param dest
	 * @param w
	 * @param h
	 * @throws Exception
	 */
	public static void zoomImage(String src, String dest, int w, int h) throws Exception {

		double wr = 0, hr = 0;
		File srcFile = new File(src);
		File destFile = new File(dest);

		BufferedImage bufImg = ImageIO.read(srcFile); // 读取图片
		Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);// 设置缩放目标图片模板

		wr = w * 1.0 / bufImg.getWidth(); // 获取缩放比例
		hr = h * 1.0 / bufImg.getHeight();

		AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
		Itemp = ato.filter(bufImg, null);
		try {
			ImageIO.write((BufferedImage) Itemp, dest.substring(dest.lastIndexOf(".") + 1), destFile); // 写入缩减后的图片
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
