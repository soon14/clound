package com.cloud.portal.statistics.employee.model;

import com.cloud.admin.api.dto.TreeNode;
import lombok.Data;

/**
 * @author Molly
 * @date Created in 2019/8/7
 * @description: 统计查询-统计报表-从业人员情况分析实体
 * @modified By: Molly
 */
@Data
public class EmployeeStatistics extends TreeNode {

    private static final long serialVersionUID = 1L;
    /** 行业类型 */
    private String hylx;
    /** 行政区划id */
    private String id;
    /** 上级id */
    private String parentId;
    /** 区划代码 */
    private String code;
    /** 从业人数 */
    private String total;
    /** 涉黄 */
    private String shry;
    /** 涉赌 */
    private String dbry;
    /** 涉恐 */
    private String skry;
    /** 涉稳 */
    private String swry;
    /** 在逃 */
    private String ztry;
    /** 涉毒 */
    private String xdry;
    /** 刑事犯罪前科 */
    private String xsfzry;
    /** 肇事肇祸精神病人 */
    private String zszhjsbry;
    /** 重点上访人员 */
    private String zdsfry;
}
