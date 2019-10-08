package com.cloud.common.data.util;

import cn.hutool.core.collection.CollectionUtil;
import com.cloud.admin.api.entity.SysDept;
import com.cloud.admin.api.entity.SysRole;
import com.cloud.admin.api.entity.SysRoleMenuScope;
import com.cloud.common.core.exception.CheckedException;
import com.cloud.common.data.constant.ParmConstant;
import com.cloud.common.data.constant.enums.DataScopeTypeEnum;
import com.cloud.common.data.datascope.DataScope;
import com.cloud.common.security.service.MicroUser;
import com.cloud.common.security.util.SecurityUtils;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @author wengshij
 * @Date Created in 2019/2/28 8:39
 * Description: 权限工具类
 * Modified By:
 */
@UtilityClass
public class DataScopeUtil {


    /**
     * 获取权限拼接的SQL
     * 例子: AND CODE LIKE '4401%'
     *
     * @param dataScope
     * @return
     */
    public String getDataScopeSql(DataScope dataScope) {
        StringBuffer searchSql = new StringBuffer(" ");
        if (null == dataScope) {
            return searchSql.toString();
        }
        // 优先获取赋值数据
        MicroUser user = SecurityUtils.getUser();
        if (user == null) {
            throw new CheckedException("auto datascope, set up security details true");
        }
        //超级管理员无限制
        if (user.isAdmin()) {
            return searchSql.toString();
        }
        SysRole sysRole = user.getSysRole();
        SysDept sysDept = user.getSysDept();
        String notEqStr = " 1=2 ";
        String linkStr = " AND ";
        String andStr = " AND ";
        if (!dataScope.getAndOr()) {
            linkStr = " OR ";
        }
        //如果角色为空和当前用户所属部门为空，则这个用户无法查询任何数据
        if (null == sysRole || null == sysDept || StringUtils.isBlank(sysDept.getCode()) || StringUtils.isBlank(dataScope.getScopeName())) {
            searchSql.append(andStr).append(notEqStr);
            return searchSql.toString();
        }
        String permission = dataScope.getPermission();
        String deptCodes = sysRole.getDsScope();
        String dsType = sysRole.getDsType();
        //判断是否包含了数据权限代码，如果有则按照菜单自定义权限优先执行
        if (StringUtils.isNotBlank(permission)) {
            List<SysRoleMenuScope> sysRoleMenuScopeList = sysRole.getRoleMenuScopeList();
            if (CollectionUtil.isNotEmpty(sysRoleMenuScopeList)) {
                for (SysRoleMenuScope sysRoleMenuScope : sysRoleMenuScopeList) {
                    if (permission.equals(sysRoleMenuScope.getPermission())) {
                        dsType = sysRoleMenuScope.getDsType();
                        deptCodes = sysRoleMenuScope.getDsScope();
                        break;
                    }
                }
            }
        }
        String deptCode = sysDept.getCode();
        String scopeName = dataScope.getScopeName();
        String userScopeName = dataScope.getUserScopeName();
        DataScopeTypeEnum dataScopeTypeEnum = DataScopeTypeEnum.getEnumByType(dsType);
        if (null == dataScopeTypeEnum) {
            return searchSql.toString();
        }
        switch (dataScopeTypeEnum) {
            case ALL:
                return searchSql.toString();
            case USER_DEPT_CITY:
                searchSql.append(linkStr).append(scopeName).append(" like '").append(StringUtils.substring(deptCode, 0, 4)).append("%' ");
                break;
            case USER_DEPT_COUNTY:
                searchSql.append(linkStr).append(scopeName).append(" like '").append(StringUtils.substring(deptCode, 0, 6)).append("%' ");
                break;
            case USER_DEPT_BASE:
                searchSql.append(linkStr).append(scopeName).append(" like '").append(StringUtils.substring(deptCode, 0, 8)).append("%' ");
                break;
            case USER_DEPT_AND_CHILD:
                String searchCode;
                //去除部门后面的0
                String tempDept = deptCode.replaceAll("0*$", "");
                //部门按偶数截取 比如 440304500000与440304510000是同级
                int num = deptCode.length() - tempDept.length();
                if (num % ParmConstant.NUMBER_2 == 0) {
                    searchCode = tempDept;
                } else {
                    searchCode = tempDept + "0";
                }
                searchSql.append(linkStr).append(scopeName).append(" like '").append(searchCode).append("%' ");
                break;
            case USER_DEPT:
                searchSql.append(linkStr).append(scopeName).append(" = '").append(deptCode).append("' ");
                break;
            case ONLY_ME:
                //如果是只能看个人数据权限、则看有没有配置用户ID。如果没有配置，则默认只能看当前部门的权限
                if (StringUtils.isBlank(userScopeName)) {
                    searchSql.append(linkStr).append(scopeName).append(" = '").append(deptCode).append("' ");
                } else {
                    //只能看个人数据
                    searchSql.append(linkStr).append(userScopeName).append(" = '").append(user.getId()).append("' ");
                }
                break;
            case DEFINED:
                //自定义部门代码，能看到当前用户所属部门数据和其他数据
                if (StringUtils.isBlank(deptCodes)) {
                    searchSql.append(linkStr).append(scopeName).append(" = '").append(deptCode).append("' ");
                } else {
                    StringBuffer code = new StringBuffer(" ");
                    for (String tempCode : deptCodes.split(ParmConstant.THE_COMMA)) {
                        if (!tempCode.equals(deptCode)) {
                            code.append(" '").append(tempCode).append("'").append(ParmConstant.THE_COMMA);
                        }
                    }
                    code.append("'").append(deptCode).append("'");
                    searchSql.append(linkStr).append(scopeName).append(" IN (").append(code.toString()).append(") ");
                }
                break;
            default:
                //不归属于任何类型的角色、肯定是出错了、自求多福吧
                searchSql.append(andStr).append(notEqStr);
        }
        //最后作为数据保障、如果为空、任何数据均不让访问
        if (StringUtils.isBlank(searchSql.toString())) {
            searchSql.append(andStr).append(notEqStr);
        }
        return searchSql.toString();
    }

}
