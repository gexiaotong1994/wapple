package com.wapple.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.Versioned;
import org.codehaus.jackson.map.util.BeanUtil;
import org.springframework.beans.BeanUtils;

import com.google.common.collect.Lists;
import com.wapple.common.Const;
import com.wapple.common.Json;
import com.wapple.enums.CookieEnum;
import com.wapple.enums.RedisEnum;
import com.wapple.enums.UserStatusEnum;
import com.wapple.pojo.Category;
import com.wapple.pojo.Product;
import com.wapple.pojo.User;
import com.wapple.service.CategoryService;
import com.wapple.service.ProductService;
import com.wapple.service.UserService;
import com.wapple.util.CookieUtil;
import com.wapple.util.DateTimeUtil;
import com.wapple.util.HttpServletRequsetUtil;
import com.wapple.util.JsonUtil;
import com.wapple.util.RedisUtil;
import com.wapple.util.SpringUtil;
import com.wapple.vo.ProductListVo;
import com.wapple.vo.UserListVo;
import com.wapple.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminServlet extends HttpServlet {

	private static final UserService userService = SpringUtil.getBean(UserService.class);
	private static final CategoryService categroyService = SpringUtil.getBean(CategoryService.class);
	private static final ProductService productService = SpringUtil.getBean(ProductService.class);

	private static final String GET = "GET";
	private static final String POST = "POST";

	private StringBuffer url(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		String queryString = request.getQueryString();
		if (!StringUtils.isBlank(queryString)) {
			url.append("?").append(queryString);
		}
		return url;
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		String uri = request.getRequestURI();
		String actionName = uri.substring(15, uri.length());
		log.info("method:{} uri:{} action:{}", method, uri, actionName);
		if (actionName.equals("login.vhtml")) {
			if (GET.equals(method)) {
				getLogin(request, response);
			} else {
				postLogin(request, response);
			}
		} else {
			// 先要判断是否登录
			/*
			 * User adminUser = userService.adminUserLogin(request); if (adminUser == null)
			 * { response.sendRedirect("login.vhtml?return_url=" +url(request).toString());
			 * return; }
			 */
			switch (actionName) {
			case "index.vhtml":
				getIndex(request, response);
				break;
			case "user/list.vhtml":
				getUserList(request, response);
				break;
			case "changeUserStatus.ajax.vhtml":
				ajaxChangeUserDetail(request, response);
				break;
			case "user/userinit.vhtml":
				getUserNew(request, response);
				break;
			case "user/detail.vhtml":
				getUserDetail(request, response);
				break;
			case "upload.vhtml":
				getUploadImage(request, response);
				break;
			case "product/productnew.vhtml":

				if (GET.equals(method)) {
					getProductNew(request, response);
				} else {
					postProductNew(request, response);
				}
				break;

			case "product/productlist.vhtml":
				this.getProudctList(request, response);
				break;
			case "product/productdetail.vhtml":
				this.getProductdetail(request, response);
				break;

			case "product/productupdate.vhtml":
				this.proudctUpdate(request, response);
				break;
			default:
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<h4>未找到当前的链接</h4>");
				out.println("</html>");
				out.flush();
				out.close();
				break;
			}

		}

	}

	private static String VIEW(String viewName) {

		return new StringBuffer().append("/admin/").append(viewName).append(".jsp").toString();
	}

	/**
	 * 登录视图
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW("login")).forward(request, response);
	}

	/**
	 * 处理登录请求
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void postLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = HttpServletRequsetUtil.getString(request, "username");
		String password = HttpServletRequsetUtil.getString(request, "password");
		String returnUrl = HttpServletRequsetUtil.getString(request, "return_url");
		Json<User> json = userService.login(username, password);
		if (!json.isSuccess()) {
			response.sendRedirect(
					url(request).append("#").append(URLEncoder.encode(json.getMsg(), "UTF-8")).toString());
			return;
		}
		User user = json.getData();
		if (user.getRole() != Const.Role.admin) {
			String notRoleMsg = URLEncoder.encode("没有管理员权限!", "UTF-8");
			log.info(notRoleMsg);
			response.sendRedirect(request.getRequestURL().append("#").append(notRoleMsg).toString());
			return;
		}

		RedisEnum redisAdmin = RedisEnum.ADMIN_LOGIN;
		String redisKey = redisAdmin.getKey() + username + System.currentTimeMillis();
		RedisUtil.setEx(redisKey, JsonUtil.objToString(user), RedisEnum.ADMIN_LOGIN.getExTime());
		CookieUtil.write(response, CookieEnum.LOGIN_ADMIN.getKey(), redisKey, redisAdmin.getExTime());
		if (StringUtils.isBlank(returnUrl)) {
			response.sendRedirect(returnUrl);
		}
		response.sendRedirect("index.vhtml");

	}

	protected void getIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW("index")).forward(request, response);
	}

	protected void getUploadImage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW("upload")).forward(request, response);
	}

	protected void getUserNew(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW("user_new")).forward(request, response);
	}

	protected void getUserList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> userList = userService.userList();
		List<UserListVo> userVoList = Lists.newArrayList();
		for (User user : userList) {
			UserListVo userListVo = new UserListVo();
			BeanUtils.copyProperties(user, userListVo);
			userListVo.setCreateTime(DateTimeUtil.dateToStr(user.getCreateTime()));
			userListVo.setStatusMsg(UserStatusEnum.getValueByCode(user.getStatus()));
			userVoList.add(userListVo);
		}
		request.setAttribute("userList", userVoList);
		request.getRequestDispatcher(VIEW("user_list")).forward(request, response);
	}

	protected void getUserDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int uid = HttpServletRequsetUtil.getInt(request, "userid");
		if (uid > -1) {
			User user = userService.getUserByUserId(uid);
			request.setAttribute("user", user);
		}
		request.getRequestDispatcher(VIEW("user_detail")).forward(request, response);

	}

	/**
	 * 修改用户状态
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void ajaxChangeUserDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json;charset=UTF-8");
		String username = HttpServletRequsetUtil.getString(request, "username");
		int status = HttpServletRequsetUtil.getInt(request, "status");
		Json<String> json = null;
		boolean flag = userService.modifyUserStatus(username, status);
		if (flag) {
			json = Json.success("用户[" + username + "]修改状态成功");
		} else {
			json = Json.fail("用户[" + username + "]修改状态失败");
		}
		String jsonStr = JsonUtil.objToString(json);
		response.getWriter().println(jsonStr);

	}

	protected void getProductNew(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> categories = categroyService.list();
		request.setAttribute("categoryList", categories);
		request.getRequestDispatcher(VIEW("product_new")).forward(request, response);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void postProductNew(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Product product = new Product();
		product.setName(HttpServletRequsetUtil.getString(request, "name").toLowerCase());
		product.setTitle(HttpServletRequsetUtil.getString(request, "title"));
		product.setCategoryId(HttpServletRequsetUtil.getInt(request, "category_id"));
		product.setStock(HttpServletRequsetUtil.getInt(request, "stock"));
		product.setPrice(HttpServletRequsetUtil.getInt(request, "price"));
		product.setDesc(HttpServletRequsetUtil.getString(request, "desc"));
		product.setMainImage("default.jpg");
		product.setSales(0);
		boolean flag = productService.save(product);
		if (flag) {
			response.sendRedirect(request.getRequestURL().append("#add_success").toString());
		} else {
			response.sendRedirect(request.getRequestURL().append("#add_success").toString());
		}

	}

	/**
	 * 获取单个商品的详细信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getProductdetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int proudctId = HttpServletRequsetUtil.getInt(request, "productId");
		Product product = productService.getProductById(proudctId);
		request.setAttribute("product", product);
		request.getRequestDispatcher(VIEW("product_detail")).forward(request, response);

	}

	/**
	 * 获取全部商品类别列表
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getProudctList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Product> products = productService.products();
		List<ProductListVo> productListVos = Lists.newArrayList();
		for (Product product : products) {
			productListVos.add(this.proudctToProductListVo(product));
		}
		request.setAttribute("productListVos", productListVos);
		request.getRequestDispatcher(VIEW("product_list")).forward(request, response);

	}

	protected void proudctUpload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File("E://wapple//images"));
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> fileItemList = upload.parseRequest(request);
			for (FileItem fileItem : fileItemList) {
				if (!fileItem.isFormField()) {
					String mainImage = fileItem.getName();
					OutputStream os = new FileOutputStream(new File("E://wapple//images", mainImage));
					InputStream ins = fileItem.getInputStream();
					// 写入到磁盘中
					byte[] bytes = new byte[1024];
					int len = 0;
					while ((len = ins.read(bytes)) != -1) {
						os.write(bytes, 0, len);
					}
					ins.close();
					os.close();

				}
			}

		} catch (FileUploadException e) {

			e.printStackTrace();
		}

	}

	
	protected void proudctUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json;charset=UTF-8");
		int id = HttpServletRequsetUtil.getInt(request, "id");
		int price = HttpServletRequsetUtil.getInt(request, "price");
		int stock = HttpServletRequsetUtil.getInt(request, "stock");
		String name = HttpServletRequsetUtil.getString(request, "name");
		String title = HttpServletRequsetUtil.getString(request, "title");
		String desc = HttpServletRequsetUtil.getString(request, "desc");
		Product product = new Product();
		product.setId(id);
		product.setDesc(desc);
		product.setName(name.toLowerCase());
		product.setTitle(title);
		product.setStock(stock);
		product.setPrice(price);
		Json<String> json = null;
		boolean flag = productService.modifyProduct(product);
		if (flag) {
			json = Json.success("商品[" + id + "]修改成功");
		} else {
			json = Json.fail("商品[" + id + "]修改失败");
		}
		response.getWriter().println(JsonUtil.objToString(json));

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

}
