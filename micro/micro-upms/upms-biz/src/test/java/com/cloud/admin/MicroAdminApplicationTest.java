

package com.cloud.admin;

import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.core.env.StandardEnvironment;

/**
 * @author ygnet
 * @date 2018/10/7
 * <p>
 * 加解密单元测试
 */
public class MicroAdminApplicationTest {
	@Test
	public void testJasypt() {
		// 对应application-dev.yml 中配置的根密码
		System.setProperty("jasypt.encryptor.password", "sunshine");
		StringEncryptor stringEncryptor = new DefaultLazyEncryptor(new StandardEnvironment());

		//加密方法
		System.out.println(stringEncryptor.encrypt("sunshine"));

		//解密方法
		//System.out.println(stringEncryptor.decrypt("ltJPpR50wT0oIY9kfOe1Iw==="));
	}
}
