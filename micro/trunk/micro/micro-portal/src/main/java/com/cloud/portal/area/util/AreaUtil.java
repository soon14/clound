package com.cloud.portal.area.util;

import com.cloud.portal.common.constant.PortalConstants;
import org.apache.commons.lang.StringUtils;

/**
 * @author ryt
 * @date Created in 2019/5/27 20:41
 * @description:
 * @modified By:
 */
public class AreaUtil {

    private final static String CODE_END = "00";


    /**
     * 获取部门代码的等级
     * （省厅（0） - 市局（1） -分局（2） -科所队室（3） - 其他（4））
     *
     * @param code 部门代码-如果部门代码为空、则获取当前用户的部门代码
     * @return
     */
    public static String getDeptLevel(String code) {
        String level = PortalConstants.DEPT_LEVEL_DEFAULT;
        try {
            if (code.startsWith("4420")) {
                Integer flag = 0;
                if (StringUtils.isNotBlank(code) && code.length() == 12) {
                    flag = Integer.parseInt(code.substring(6, 8));
                }
                if ("4400".equals(code.substring(0, 4))) {
                    level = PortalConstants.DEPT_LEVEL_ALL;
                } else if ("00000000".equals(code.substring(4, 12))) {
                    level = PortalConstants.DEPT_LEVEL_CITY;
                } else if ("00".equals(code.substring(4, 6)) && flag >= 50 && "0000".equals(code.substring(8, 12))) {
                    level = PortalConstants.DEPT_LEVEL_COUNTY;
                } else {
                    level = PortalConstants.DEPT_LEVEL_BASE;
                }
            } else {
                if ("4400".equals(code.substring(0, 4))) {
                    level = PortalConstants.DEPT_LEVEL_ALL;
                } else if ("00".equals(code.substring(4, 6))) {
                    level = PortalConstants.DEPT_LEVEL_CITY;
                } else if ("00".equals(code.substring(6, 8))) {
                    level = PortalConstants.DEPT_LEVEL_COUNTY;
                } else {
                    level = PortalConstants.DEPT_LEVEL_BASE;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return level;

    }

    /**
     * 处理6位行政区划代码用于查询所有辖区单位
     *
     * @param areaCode 6位行政区划代码
     * @return
     */
    public static String getAreaCodeForSearch(String areaCode) {
        int index = areaCode.lastIndexOf(CODE_END);
        String result = areaCode;
        if (index >= 0) {
            areaCode = areaCode.substring(0, index);
            result = getAreaCodeForSearch(areaCode);
        }
        return result;
    }
}
