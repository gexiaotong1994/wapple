package com.wapple.security.shiro;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义Realm
 * 
 * @author Administrator
 *
 */
public class CustomRealm extends AuthorizingRealm {

	private Map userMap;
	private Set<String> roles;
	private Set<String> periss;
	
	

	public CustomRealm() {
		super.setName("customRealm");
		userMap = new HashMap<>();
		roles=new HashSet<>();
		periss=new HashSet<>();
		userMap.put("zhangsan", "123456");
		userMap.put("lisi", "12345678");
		roles.add("admin");
		periss.add("user:update");
		
	}

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username=principals.getPrimaryPrincipal().toString();
		SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.setRoles(roles);
		simpleAuthorizationInfo.setStringPermissions(periss);
		return simpleAuthorizationInfo;
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = token.getPrincipal().toString();// 主题用户名
		Object password = userMap.get(username);
		if (password == null) {
			System.out.println("该用户名不存在");
			return null;
		}
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,
				userMap.get(username).toString(), "customRealm");

		return simpleAuthenticationInfo;
	}

}
