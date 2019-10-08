

package com.cloud.portal.common.constant;

/**
 * @author ygnet
 * @date 2017/10/29
 */
public interface PortalConstants {

    /**
     * 所有场所分类菜单缓存key值
     */
    String ALL_UNIT_MENU_CACHE_KEY = "all_unitMenu";
    /**
     * 所有行政区划缓存key值
     */
    String ALL_AREA_CACHE_KEY = "all_area";
    /**
     * 所有行政区划缓存key值
     */
    String TYJG_CACHE_KEY = "all_tyjg";
    /**
     * 部门等级-省厅级别
     */
    String DEPT_LEVEL_ALL = "0";
    /**
     * 部门等级-市局级别
     */
    String DEPT_LEVEL_CITY = "1";
    /**
     * 部门等级-分局级别
     */
    String DEPT_LEVEL_COUNTY = "2";
    /**
     * 部门等级-科所队
     */
    String DEPT_LEVEL_BASE = "3";
    /**
     * 部门等级-其他
     */
    String DEPT_LEVEL_DEFAULT = "4";


    /**
     * 研判模型菜单类型-标题
     */
    String MODEL_MENU_TYPE_TITLE = "1";

    /**
     * 研判模型菜单类型-目录
     */
    String MODEL_MENU_TYPE_DIR = "2";
    /**
     * 所有研判模型菜单缓存key值
     */
    String ALL_MODEL_MENU_CACHE_KEY = "all_modelMenu";

    /**
     * 研判模型菜单类型-模型
     */
    String MODEL_MENU_TYPE_MODEL = "3";

    /**
     * 研判模型 图片缓存值
     */
    String MODEL_MENU_ICON = "model_icon";
    /**
     * 指标状态- 启用
     */
    String POINT_INDEX_STATUS_OPEN = "0";
    /**
     * 指标状态 - 停用
     */
    String POINT_INDEX_STATUS_CLOSE = "1";
    /**
     * 自动积分指标
     */
    String POINT_AUTO = "0";
    /**
     * 手动积分指标
     */
    String POINT_PASSIVE = "1";
    /***
     * 菜单权限标识符-积分管理-控制积分类型
     * 可操作整个指标管理、包括手动指标和自动指标以及其他地市指标
     */
    String POINT_INDEX_MANAGER_AREA_POWER = "point_index_manager_area";

    /***
     * 菜单权限标识符 -积分管理模块-控制积分类型
     * 包括手动指标和自动指标
     */
    String POINT_INDEX_MANAGER_TYPE_POWER = "point_index_manage_type";
    /**
     * 指标 版本和指标唯一标识符的连接符号
     */
    String POINT_INDEX_VERSION_UID_LINK = "#";

    /**
     * 等级评定方案状态- 启用
     */
    String POINT_LEVEl_STATUS_OPEN = "0";
    /**
     * 等级评定方案状态 - 停用
     */
    String POINT_LEVEl_STATUS_CLOSE = "1";
    /**
     * 等级评定方案是否通用-是
     */
    String POINT_UNIVERSAL_TRUE = "0";
    /**
     * 等级评定方案是否通用-否
     */
    String POINT_UNIVERSAL_FALSE = "1";

    /***
     * 菜单权限标识符
     * 是否通用（地市、场所）
     */
    String POINT_LEVEL_MANAGER_UNIVERSAL = "point_level_manager_universal";

    /**
     * 积分方案状态- 启用
     */
    String POINT_SCHEME_STATUS_OPEN = "0";

    /**
     * 积分方案状态- 停用
     */
    String POINT_SCHEME_STATUS_CLOSE = "1";

    /**
     * 积分方案是否初始化分值- 是
     */
    String POINT_SCHEME_ISINIT = "0";

    /**
     * 积分方案是否初始化分值- 否
     */
    String POINT_SCHEME_ONTINIT = "1";

    /***
     * 菜单权限标识符
     * 可操作其他地市的积分方案
     */
    String POINT_SCHEME_AREA = "point_scheme_area";

    /***
     * 菜单权限标识符
     * 是否通用（地市、场所）
     */
    String POINT_SCHEME_UNIVERSAL = "point_scheme_universal";
    /**
     * 来源系统是警综的
     * 0-警综
     * 1-娱乐场所
     * 2-机修
     * 3-旅馆
     * 4-刻字
     */
    String SOURCE_TYPE_JZ = "0";
    /**
     * 积分指标状态（自动积分和手动积分）  正常
     */
    String INDEX_DETAIL_TYPE_NORMAL = "0";

    /**
     * 积分指标明细（自动积分和手动积分）状态  已撤回
     */
    String INDEX_DETAIL_TYPE_BACK = "1";
    /**
     * 综合查询请求方式
     * 业务表方式
     */
    String SEARCH_TYPE_TABLE = "0";
    /**
     * 综合查询rest请求方式
     * 接口请求方式
     */
    String SEARCH_TYPE_REST_API = "1";
    /**
     * 综合查询-查询方案缓存key值
     */
    String SEARCH_SCHEME_CACHE_KEY = "searchScheme_details";
}
