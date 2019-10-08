

package com.cloud.common.core.constant;

/**
 * @author ygnet
 * @date 2017/10/29
 */
public interface CommonConstants {

    /**
     * 删除
     */
    String STATUS_DEL = "1";
    /**
     * 正常
     */
    String STATUS_NORMAL = "0";
    /**
     * 新建用户，默认密码
     */
    String USER_PASSWORD = "123456";
    /**
     * 锁定
     */
    String STATUS_LOCK = "9";

    /**
     * 菜单
     */
    String MENU = "0";

    /**
     * 编码
     */
    String UTF8 = "UTF-8";

    /**
     * 前端工程名
     */
    String FRONT_END_PROJECT = "micro-ui";

    /**
     * 后端工程名
     */
    String BACK_END_PROJECT = "micro";

    /**
     * 路由存放
     */
    String ROUTE_KEY = "gateway_route_key";

    /**
     * spring boot admin 事件key
     */
    String EVENT_KEY = "event_key";

    /**
     * 验证码前缀
     */
    String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY_";

    /**
     * 成功标记
     */
    Integer SUCCESS = 0;
    /**
     * 失败标记
     */
    Integer FAIL = 1;

    /**
     * 默认存储bucket
     */
    String BUCKET_NAME = "ygnet";
    /**
     * 超级管理员标识
     */
    String ADMIN_FLAG = "1";
    /**
     * 所有菜单缓存key值
     */
    String ALL_MENU_CACHE_KEY = "all_menu";

    /**
     * 菜单显示状态
     */
    String MENU_SHOW = "0";
    /**
     * 菜单隐藏状态
     */
    String MENU_HIDE = "1";
    /**
     * 菜单-系统菜单类型
     */
    String MENU_BUSS_TYPE_SYS = "0";
    /**
     * 菜单-系统模型类型
     */
    String MENU_BUSS_TYPE_MODEL = "1";
    /**
     * 模型主键ID
     */
    String SYS_MENU_MODEL_ID = "app_model_id";
    /**
     * 模型主键父ID集合
     */
    String SYS_MENU_MODEL_PARENT_IDS = "-1,app_model_id,";
    /**
     * 路由缓存开启
     */
    String KEEP_ALIVE_OPEN = "0";

    /**
     * 请求超时时间 默认为二分钟
     */
    Integer HTTP_TIMEOUT_MILLION_SECONDS = 120 * 1000;


    String HTTPS_PROTOCOL = "https";

    Integer POOL_SIZE = 20;

    /**
     * 其他异常信息
     */
    Integer DEFAULT_ERROR_CODE = 0;

    String DEFAULT_CHARSET = "UTF-8";


    String AUTHORIZATION_BEARER = "Bearer ";
    /**
     * 秘钥代码值
     */
    String SECURITY_CODE = "securityCode";

}
