package com.cloud.portal.wechat.entity;

import cn.hutool.json.JSONObject;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * @author wengshij
 * @date Created in 2019/8/20 20:51
 * @description:
 * @modified By:wengshij
 */
@Data
public class WeChatUser implements Serializable {
    /**
     * 头像路径
     */
    private String avatar;
    /**
     * 所属部门
     */
    private String department;
    /**
     * 警号信息
     */
    private String extJh;
    /**
     * 性别
     */
    private String gender;
    /**
     * 警号【身份证】
     */
    private String jh;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 姓名
     */
    private String name;
    /**
     * 职位
     */
    private String position;

    public static WeChatUser initUser(JSONObject jsonObject) {
        if (null != jsonObject) {
            String avatarKey = "avatar",
                    departmentKey = "department",
                    extJhKey = "extJh",
                    genderKey = "gender",
                    jhKey = "jh", mobileKey = "mobile", nameKey = "name", positionKey = "position";
            String jh = jsonObject.getStr(jhKey);
            if (StringUtils.isNotBlank(jh)) {
                WeChatUser weChatUser = new WeChatUser();
                weChatUser.setJh(jh);
                weChatUser.setAvatar(jsonObject.getStr(avatarKey));
                weChatUser.setDepartment(jsonObject.getStr(departmentKey));
                weChatUser.setExtJh(jsonObject.getStr(extJhKey));
                weChatUser.setGender(jsonObject.getStr(genderKey));
                weChatUser.setMobile(jsonObject.getStr(mobileKey));
                weChatUser.setName(jsonObject.getStr(nameKey));
                weChatUser.setPosition(jsonObject.getStr(positionKey));
                return weChatUser;
            }
        }
        return null;
    }


}
