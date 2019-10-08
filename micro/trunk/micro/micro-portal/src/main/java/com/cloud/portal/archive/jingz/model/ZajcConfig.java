package com.cloud.portal.archive.jingz.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author ryt
 * @date Created in 2019/6/16 15:14
 * @description: 警综治安检查（日常检查）检查项内容
 * @modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ZajcConfig extends Model<ZajcConfig> {

    private String systemid;
    private String gnmkname;
    /**
     * 检查项名称
     */
    private String jcxmname;
    private String nrmkname;
    private Integer sywh;
    private Integer xh;

    /**
     * 检查情况 1是 0否
     */
    @TableField(exist = false)
    private String checkState;
}
