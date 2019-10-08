

package com.cloud.common.data.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;

/**
 * @author ygnet
 * @date 2018/12/26
 * <p>
 * 数据权限类型
 */
@Getter
@AllArgsConstructor
public enum DataScopeTypeEnum {

    ALL("0", "全部数据"),

    USER_DEPT_CITY("1", "用户所属部门市局数据"),

    USER_DEPT_COUNTY("2", "用户所属部门分局数据"),

    USER_DEPT_BASE("3", "用户所属部门科所队数据"),

    USER_DEPT_AND_CHILD("4", "用户所属部门及子级数据"),

    USER_DEPT("5", "用户所属部门数据"),

    ONLY_ME("6", "仅本人数据"),

    DEFINED("7", "自定义数据权限");


    /**
     * 类型
     */
    private final String type;
    /**
     * 描述
     */
    private final String description;

    /**
     * 通过type取枚举
     *
     * @param type
     * @return
     */
    public static DataScopeTypeEnum getEnumByType(String type) {
        if (StringUtils.isBlank(type)) {
            return null;
        }
        for (DataScopeTypeEnum enums : DataScopeTypeEnum.values()) {
            if (enums.getType().equals(type)) {
                return enums;
            }
        }
        return null;
    }

}
