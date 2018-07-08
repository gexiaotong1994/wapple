package wapple;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class AuthTest {

	SimpleAccountRealm realm=new SimpleAccountRealm();
	@Before
	public void addUser() {
		realm.addAccount("admin1", "1234567");
	}
	
	
	@Test
	public void test1() {
		// 构建环境
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

		defaultSecurityManager.setRealm(realm);
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		// 主题提交认证请求
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken("admin1", "1234567");
		
		subject.login(token);
		System.out.println("1"+subject.isAuthenticated());
	}

}
