package com.wapple.security.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.alibaba.druid.pool.DruidDataSource;

public class TestShiroAuth {
	/**
	 * 最简单的认证 入门程序
	 */
	public static void test1() {
		System.out.println("开始");
		SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
		simpleAccountRealm.addAccount("admin1", "123456");
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		defaultSecurityManager.setRealm(simpleAccountRealm);
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin1", "123456");
		subject.login(usernamePasswordToken);
		System.out.println(subject.isAuthenticated());

	}

	/**
	 * shiro授权
	 */
	public static void test2() {
		System.out.println("开始");
		//定义一个简单用户范围
		SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
		// 用户受理管理员角色 root
		simpleAccountRealm.addAccount("admin1", "123456", "root");
		//创建一个默认的环境
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		//将认证realm放入环境中
		defaultSecurityManager.setRealm(simpleAccountRealm);
		//将环境放入工具类中
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		//获得一个subject
		Subject subject = SecurityUtils.getSubject();
		//实例化一个认证
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin1", "123456");
		//开始认证
		subject.login(usernamePasswordToken);
		//输入结果
		System.out.println(subject.isAuthenticated());
		//增加认证
		subject.checkRole("root");

	}
	
	/**
	 * inirealm
	 */
	public static void test3() {
		System.out.println("开始");
		//本地根路径下的ini文件
		IniRealm iniRealm=new IniRealm("classpath:user_shiro.ini");
		//创建一个默认的环境
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		//将认证realm放入环境中
		defaultSecurityManager.setRealm(iniRealm);
		//将环境放入工具类中
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		//获得一个subject
		Subject subject = SecurityUtils.getSubject();
		//实例化一个认证
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhangsan", "1234567");
		//开始认证
		subject.login(usernamePasswordToken);
		//输入结果
		System.out.println(subject.isAuthenticated());
		//增加认证
		subject.checkRole("admin");
		
		subject.checkPermission("user:detele");

	}
	
	
	public static void test4() {
		System.out.println("jdbc开始");
		DruidDataSource dataSource=new DruidDataSource();
		dataSource.setUrl("jdbc:mysql://47.104.244.164:3306/wapple");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		
		//本地根路径下的ini文件
		JdbcRealm jdbcRealm=new JdbcRealm();
		jdbcRealm.setDataSource(dataSource);
		jdbcRealm.setPermissionsLookupEnabled(true);
		//创建一个默认的环境
	    String sql="select password from t_test_shiro_user where username=?";
		jdbcRealm.setAuthenticationQuery(sql);
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		//将认证realm放入环境中
		defaultSecurityManager.setRealm(jdbcRealm);
		//将环境放入工具类中
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		//获得一个subject
		Subject subject = SecurityUtils.getSubject();
		//实例化一个认证
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhang", "123456");
		//开始认证
		subject.login(usernamePasswordToken);
		//输入结果
		System.out.println(subject.isAuthenticated());
		

	}
	
	
	/**
	 * shiro 自定义realm
	 */
	public static void test5() {
		System.out.println("开始");
		//定义一个简单用户范围
		CustomRealm customRealm=new CustomRealm();
		// 用户受理管理员角色 root
		//创建一个默认的环境
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		//将认证realm放入环境中
		defaultSecurityManager.setRealm(customRealm);
		//将环境放入工具类中
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		//获得一个subject
		Subject subject = SecurityUtils.getSubject();
		//实例化一个认证
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhangsan", "123456");
		//开始认证
		subject.login(usernamePasswordToken);
		//输入结果
		System.out.println(subject.isAuthenticated());
		//增加认证
		subject.checkRole("admin");
		subject.checkPermission("user:update1");

	}
	
	/**
	 * 自定义加密
	 */
	public static void test6() {
		System.out.println("开始");
		//定义一个简单用户范围
		CustomRealm customRealm=new CustomRealm();
		// 用户受理管理员角色 root
		//创建一个默认的环境
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		//将认证realm放入环境中
		defaultSecurityManager.setRealm(customRealm);
		
		HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("md5");
		matcher.setHashIterations(1);
		customRealm.setCredentialsMatcher(matcher);
		
		//将环境放入工具类中
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		//获得一个subject
		Subject subject = SecurityUtils.getSubject();
		//实例化一个认证
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhangsan", "123456");
		//开始认证
		subject.login(usernamePasswordToken);
		//输入结果
		System.out.println(subject.isAuthenticated());
		//增加认证
		subject.checkRole("admin");
		subject.checkPermission("user:update1");

	}
	
	
	public static void main(String[] args) {
		test5();

	}

}
