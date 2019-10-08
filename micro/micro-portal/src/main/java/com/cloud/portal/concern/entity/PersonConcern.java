
package com.cloud.portal.concern.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.cloud.admin.api.entity.SysUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 *
 *
 * @author ygnet
 * @date 2019-02-27 10:48:28
 */
@Data
@TableName("t_person_concern")
@EqualsAndHashCode(callSuper = true)
public class PersonConcern extends Model<PersonConcern> {

    private static final long serialVersionUID = 1L;

    /**
   * 主键id
   */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
   * 姓名
   */
    private String name;
    /**
   * 证件号码
   */
    private String idCard;
    /**
   * 证件类型
   */
    private String cardType;
    /**
   * 人员类型
   */
    private String peopleType;
    /**
   * 创建时间
   */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    /**
   * 创建人
   */
    private String createBy;
    /**
   * 更新时间
   */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;
    /**
   * 更新人
   */
    private String updateBy;
    /**
     * 0-正常，1-删除
     */
    @TableLogic
    private String delFlag;
    /**
   * 备注
   */
    private String remarks;
    /**
   * 民警id
   */
    private String userId;

    /**
     * 头像
     */
    @TableField(exist = false)
    private SysUser sysUser;






    /**
   * 组织机构id
   */
    private String officeId;
    /**
   * 结果推送渠道
   */
    private String channel;
    /**
   * 名单来源
   */
    private String listSource;
    /**
   * 应用id
   */
    private String appId;
    /**
   * 关注开始时间
   */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    /**
   * 关注结束时间
   */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    /**
   * 状态(已读、未读)
   */
    private String status;
    /**
   * 上一次查阅时间
   */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastDate;
    /**
   * 姓名拼音
   */
    private String namepy;
    /**
   * 性别
   */
    private String sex;
    /**
   * 民族
   */
    private String nation;
    /**
   * 出生日期
   */
    private String bdate;
    /**
   * 常住行政区划
   */
    private String xzqh;
    /**
   * 常住地址
   */
    private String address;
    /**
   * 警号，比如张三是帅哥关注，此处填帅哥的警号
   */
    private String userNo;
    /**
   * 头像
   */
    private String img;





    /**
     * 权限标识SQL
     */
    @TableField(exist = false)
    private String dataScope;

}
