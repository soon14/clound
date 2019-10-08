package com.cloud.portal.wechat.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cloud.admin.api.dto.UserInfo;
import com.cloud.admin.api.entity.SysUser;
import com.cloud.common.core.http.HttpClientUtil;
import com.cloud.common.core.http.JsonMapper;
import com.cloud.common.core.http.Result;
import com.cloud.common.core.http.entity.RequestParam;
import com.cloud.common.core.util.R;
import com.cloud.portal.wechat.config.WeChatApiConfig;
import com.cloud.portal.wechat.entity.WeChatToken;
import com.cloud.portal.wechat.entity.WeChatUser;
import com.cloud.portal.wechat.service.WeChatService;
import com.cloud.portal.wechat.util.WeChatUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wengshij
 * @date Created in 2019/8/20 17:18
 * @description:政务微信接口控制层
 * @modified By:wengshij
 */
@Service
public class WeChatServiceImpl implements WeChatService {


    /**
     * 获取用户唯一凭证信息【证件号码】
     *
     * @param aCode 政务微信跳转时获取到的 有效凭证
     * @return
     */
    @Override
    public R getIdCardByACode(String aCode) {
        return R.ok(WeChatUtil.getUserInfo(aCode));
    }
}
