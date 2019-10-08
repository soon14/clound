

package com.cloud.common.data.datascope;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ygnet
 * @date 2018/8/30
 * 数据权限查询参数
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataScope extends HashMap {

    /**
     * @param scopeName     字段代码 例如：T.deptCode 或者  deptCode
     * @param permission    权限代码 例如 user_add
     * @param userScopeName 用户所属字段
     */
    public DataScope(String scopeName, String permission, String userScopeName) {
        this.scopeName = scopeName;
        this.permission = permission;
        this.userScopeName = userScopeName;
    }

    /**
     * @param scopeName     字段代码 例如：T.deptCode 或者  deptCode
     * @param permission    权限代码 例如 user_add

     */
    public DataScope(String scopeName, String permission){
        this.scopeName = scopeName;
        this.permission = permission;
    }
    /**
     * @param scopeName     字段代码 例如：T.deptCode 或者  deptCode
     * @param permission    权限代码 例如 user_add
     * @param userScopeName 用户所属字段
     * @param andOr         用AND 连接 或者 OR 连接
     */

    public DataScope(String scopeName, String permission, String userScopeName, boolean andOr) {
        this.scopeName = scopeName;
        this.permission = permission;
        this.userScopeName = userScopeName;
        this.andOr = andOr;
    }

    /**
     * 限制范围的字段名称 用于过滤部门代码数据
     */
    private String scopeName;
    /**
     * 根据菜单权限标识、
     * 查询该菜单是否自定义了权限
     */
    private String permission;
    /**
     * 定义只能是当前用户下权限时使用
     */
    private String userScopeName;
    /**
     * 查询条件默认用AND 来拼接
     * 如果有需要用OR的 请设置为 false
     */
    private Boolean andOr = Boolean.TRUE;
}
