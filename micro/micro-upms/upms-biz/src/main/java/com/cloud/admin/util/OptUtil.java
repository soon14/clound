package com.cloud.admin.util;

import com.cloud.admin.api.entity.SysRole;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.core.util.R;
import com.cloud.common.data.constant.enums.DataScopeTypeEnum;
import com.cloud.common.security.service.MicroUser;
import com.cloud.common.security.util.SecurityUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author wengshij
 * @Date Created in 2019/3/5 13:51
 * Description: 逻辑操作工具类
 * Modified By:
 */
public class OptUtil {

    /**
     * 角色-数据类型校验、防止越权操作
     *
     * @param sysRole
     * @return
     */
    public static R check(SysRole sysRole) {
        MicroUser user = SecurityUtils.getUser();
        if (user.isAdmin()) {
            return null;
        }
        SysRole currentRole = user.getSysRole();
        R result = R.builder()
                .code(CommonConstants.FAIL)
                .msg("您没有该项操作权限或越权操作，请联系管理员").build();
        if (null == currentRole) {
            return result;
        }
        String currentDsType = currentRole.getDsType();
        DataScopeTypeEnum dataScopeTypeEnum = DataScopeTypeEnum.getEnumByType(currentDsType);
        if (null == dataScopeTypeEnum) {
            return result;
        }
        Boolean hasPerm;
        switch (dataScopeTypeEnum) {
            case ALL:
                return null;
            case USER_DEPT_CITY:
                if (DataScopeTypeEnum.ALL.getType().equals(sysRole.getDsType())) {
                    return result;
                }
                break;
            case USER_DEPT_COUNTY:
                hasPerm = (DataScopeTypeEnum.ALL.getType().equals(sysRole.getDsType())
                        || DataScopeTypeEnum.USER_DEPT_CITY.getType().equals(sysRole.getDsType()));
                if (hasPerm) {
                    return result;
                }
                break;
            case USER_DEPT_BASE:
                hasPerm = (DataScopeTypeEnum.ALL.getType().equals(sysRole.getDsType())
                        || DataScopeTypeEnum.USER_DEPT_CITY.getType().equals(sysRole.getDsType())
                        || DataScopeTypeEnum.USER_DEPT_COUNTY.getType().equals(sysRole.getDsType()));
                if (hasPerm) {
                    return result;
                }
                break;
            //如果是部门及部门以下角色、需要判断该用户部门是什么级别
            case USER_DEPT_AND_CHILD:
                String deptLevel = getDeptLevel(null);
                switch (deptLevel) {
                    case OptConstant.DEPT_LEVEL_ALL:
                        if (DataScopeTypeEnum.ALL.getType().equals(sysRole.getDsType())) {
                            return result;
                        }
                        break;
                    case OptConstant.DEPT_LEVEL_CITY:
                        if (DataScopeTypeEnum.ALL.getType().equals(sysRole.getDsType())) {
                            return result;
                        }
                        break;
                    case OptConstant.DEPT_LEVEL_COUNTY:
                        hasPerm = (DataScopeTypeEnum.ALL.getType().equals(sysRole.getDsType())
                                || DataScopeTypeEnum.USER_DEPT_CITY.getType().equals(sysRole.getDsType()));
                        if (hasPerm) {
                            return result;
                        }
                        break;
                    default:
                        hasPerm = (DataScopeTypeEnum.ALL.getType().equals(sysRole.getDsType())
                                || DataScopeTypeEnum.USER_DEPT_CITY.getType().equals(sysRole.getDsType())
                                || DataScopeTypeEnum.USER_DEPT_COUNTY.getType().equals(sysRole.getDsType())
                                || DataScopeTypeEnum.USER_DEPT_BASE.getType().equals(sysRole.getDsType())
                        );
                        if (hasPerm) {
                            return result;
                        }
                }
                break;
            default:
                hasPerm = (DataScopeTypeEnum.ALL.getType().equals(sysRole.getDsType())
                        || DataScopeTypeEnum.USER_DEPT_CITY.getType().equals(sysRole.getDsType())
                        || DataScopeTypeEnum.USER_DEPT_COUNTY.getType().equals(sysRole.getDsType())
                        || DataScopeTypeEnum.USER_DEPT_BASE.getType().equals(sysRole.getDsType()));
                if (hasPerm) {
                    return result;
                }
                break;
        }


        return null;
    }

    /**
     * 获取部门代码的等级
     * （省厅（0） - 分局（1） -市局（2） -科所队室（3） - 其他（4））
     *
     * @param code 部门代码-如果部门代码为空、则获取当前用户的部门代码
     * @return
     */
    public static String getDeptLevel(String code) {
        if (StringUtils.isBlank(code)) {
            MicroUser user = SecurityUtils.getUser();
            if (null != user && user.getSysDept() != null) {
                code = user.getSysDept().getCode();
            }
        }
        String level = OptConstant.DEPT_LEVEL_DEFAULT;
        try {
            if (code.startsWith("4420")) {
                Integer flag = 0;
                if (StringUtils.isNotBlank(code) && code.length() == 12) {
                    flag = Integer.parseInt(code.substring(6, 8));
                }
                if ("4400".equals(code.substring(0, 4))) {
                    level = OptConstant.DEPT_LEVEL_ALL;
                } else if ("00000000".equals(code.substring(4, 12))) {
                    level = OptConstant.DEPT_LEVEL_CITY;
                } else if ("00".equals(code.substring(4, 6)) && flag >= 50 && "0000".equals(code.substring(8, 12))) {
                    level = OptConstant.DEPT_LEVEL_COUNTY;
                } else {
                    level = OptConstant.DEPT_LEVEL_BASE;
                }
            } else {
                if ("4400".equals(code.substring(0, 4))) {
                    level = OptConstant.DEPT_LEVEL_ALL;
                } else if ("00".equals(code.substring(4, 6))) {
                    level = OptConstant.DEPT_LEVEL_CITY;
                } else if ("00".equals(code.substring(6, 8))) {
                    level = OptConstant.DEPT_LEVEL_COUNTY;
                } else {
                    level = OptConstant.DEPT_LEVEL_BASE;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return level;

    }


}
