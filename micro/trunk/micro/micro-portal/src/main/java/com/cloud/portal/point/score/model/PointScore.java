package com.cloud.portal.point.score.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author wengshij
 * @date Created in 2019/7/24 9:58
 * @description: 特行场所历史分值
 * @modified By:wengshij
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PointScore extends Model<PointScore> {
    /**
     * 21个地市 旅馆信息表
     */
    public static String[] hotelTableName = {"4401", "4402", "4403", "4404", "4405", "4406", "4407", "4408", "4409", "4412", "4413", "4414", "4415", "4416", "4417", "4418", "4419", "4420", "4451", "4452", "4453"};

    /**
     * 所属周期表ID
     */
    private String cycleId;
    /**
     * 主键ID
     */
    private String id;
    /**
     * 最新评定时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date judgeTime;
    /**
     * 等级ID
     */
    private String levelId;
    /**
     * 等级（字典 A级 B级 C 级 ）字典代码
     */
    private String levelType;
    /**
     * 场所ID
     */
    private String placeId;
    /**
     * 总分
     */
    private Integer totalScore;

    /**
     * 存储所有旅馆业的表名称
     */
    @TableField(exist = false)
    @JsonIgnore
    private List<String> tableName;
    /**
     * 所属机构部门代码
     */
    private String deptName;
    /**
     * 所属机构代码
     */
    private String deptCode;
    /**
     * 行政区划代码
     */
    private String cityCode;
    /**
     * 场所名称
     */
    private String placeName;
    /**
     * 来源系统类型（四大系统）
     */
    private String sourceType;
    /**
     * 场所地址
     */
    private String placeAddress;
    /**
     * 场所类型
     */
    private String placeType;
    /**
     * 权限控制代码
     */
    @JsonIgnore
    private String dataScope;
    /**
     * 周期名称
     */
    private String cycleName;
    /**
     * 周期类型 0标识当前周期
     * 1标识历史周期
      */
    private String cycleType;
    /**
     * 周期时间
     */
    private String cycleTime;
}
