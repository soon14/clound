

package com.cloud.admin.service;

import com.cloud.common.core.util.R;

/**
 * @author ygnet
 * @date 2018/11/14
 */
public interface MobileService {
	/**
	 * 发送手机验证码
	 *
	 * @param mobile mobile
	 * @return code
	 */
	R<Boolean> sendSmsCode(String mobile);
}
