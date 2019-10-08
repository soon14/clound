package com.cloud.portal.search.show.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wengshij
 * @date Created in 2019/8/5 19:13
 * @description:
 * @modified By:wengshij
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SearchShow extends Model<SearchShow> {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 查询条件封装
     */
    private Map<String, Object> searchMap = new HashMap<>();
    /**
     * 方案主键ID
     */
    private String schemeId;
    /**
     * 字段英文名称
     */
    private List<ColumnKey> columns;
    /**
     * API 接口地址
     */
    private String apiUrl;
    /**
     * token 地址
     */
    private String tokenUrl;
    /**
     * 接口用户名
     */
    private String apiUserName;
    /**
     * 接口地址
     */
    private String apiPwd;
    /**
     * 权限字段名称
     */
    private String dataScope;
    /**
     * 查询条件封装
     */
    private List<SearchKey> searchKeyList;
    /**
     * 排序字段
     */
    private Map<String, String> orderByKey;
    /**
     * 请求方式 目前支持POST 请求和 GET请求
     */
    private String method;
    /**
     * 查询方式
     * 0 业务表
     * 1 rest接口方式
     */
    private String searchType;
}
