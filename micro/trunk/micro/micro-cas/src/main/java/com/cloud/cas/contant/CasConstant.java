package com.cloud.cas.contant;

/**
 * @author wengshij
 * @date Created in 2019/8/27 15:53
 * @description:单点登录统一参数管理
 * @modified By:wengshij
 */
public interface CasConstant {

    /**
     * 统一登录 从参数中获取身份证信息的key值
     */
    String TICKET_ID_CODE_PARAMS = "gmsfzh";
    /**
     * 统一登录 从参数中获姓名的key值
     */
    String TICKET_NAME_PARAMS = "name";

    /**
     * 统一登录 从参数中获机构代码的key值
     */
    String TICKET_DEPT_CODE_PARAMS = "orgCode";
    /**
     * 统一登录 从参数中获机构机构名称的key值
     */
    String TICKET_POLICE_DEPT_NAME_PARAMS = "orgName";

    /**
     * 统一登录 从参数中获取警号的key值
     */
    String TICKET_POLICE_NUMBER_PARAMS = "policeNumber";

    /**
     * 统一登录 从参数中获取警种信息的key值
     */
    String TICKET_POLICE_CATEGORY_PARAMS = "policeCategory";

    String SESSION_USER_PARAMS = "casUser";
    /**
     * 单点登录服务器地址
     */
    String CAS_SERVER_URL = "dcuc.casServerUrlPrefix";
    /**
     * 应用服务器地址
     */
    String CAS_CLIENT_URL = "dcuc.serverName";
    /**
     * 跳转地址
     */
    String REDIRECT_URL = "redirectUrl";
    /**
     * 退出登录地址
     */
    String LOGIN_OUT_URL = "/logout";
    /**
     * 服务地址拼接
     */
    String SERVICE_URL = "?service=";

}
