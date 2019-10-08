

package com.cloud.portal.judged.model.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cloud.admin.api.dto.TreeNode;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author ryt
 * @date Created in 2019/6/22 11:17
 * @description: 研判模型tree
 * @modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ModelMenuTree extends TreeNode {
    /**链接地址*/
    private String links;
    /**模型名称*/
    private String name;
    /**模型打开方式 */
    private String openType;
    /**父级ID*/
    private String parentIds;
    /**权限标识*/
    private String permission;
    /**预案库ID集合*/
    private String planLibId;
    /**简要信息*/
    private String remark;
    /**显示或隐藏 字典是否*/
    private String showHide;
    /**排序*/
    private Integer sort;
    /**评价星级（5星）*/
    private Integer stars;
    /**类型（标题、目录 、模型）*/
    private String type;
    /**模型唯一标识符（可作为字典）*/
    private String udId;
    /**推荐使用 字典是否*/
    private String useBest;
    /**浏览次数*/
    private Integer viewTimes;

    /**创建时间 研判模型展示用*/
    @TableField(exist = false)
    private String createDate;
    /**是否收藏*/
    private String isCollect;
    /**模型状态（0启用、1建设中）仅用于模型跳转控制*/
    private String state;
}
