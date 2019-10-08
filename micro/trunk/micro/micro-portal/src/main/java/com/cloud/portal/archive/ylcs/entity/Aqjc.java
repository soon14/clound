package com.cloud.portal.archive.ylcs.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author ryt
 * @date Created in 2019/5/21 14:36
 * @description: 娱乐场所-公安日常检查,仅供公安添加
 * @modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Aqjc extends Model<Aqjc> {
    private static final long serialVersionUID = 1L;
    /**主键*/
    private String aqjcId;
    /**登记序号,自动生成,参考旧版代码生成规则*/
    private String djxh;
    /**登记单位ID,娱乐场所的登记单位是公安即检查单位名*/
    private String compId;
    /**登记单位名,也就是检查单位名*/
    private String compName;
    /**登记人姓名*/
    private String compUser;
    /**受检单位ID*/
    private String sjCompId;
    /**受检单位*/
    private String sjCompName;
    /**受检单位CODE*/
    private String sjCompCode;
    /**受检单位类型*/
    private String sjCompNature;
    /**受检单位当值负责人*/
    private String sjCompFzr;
    /**受检单位当值负责人联系电话 （没用到）*/
    private String sjCompPhone;
    /**检查类型 0日常检查*/
    private Integer checkType;
    /**检查人员1. 对应检查民警*/
    private String checkMan1;
    /**检查人员1职务. 暂未用到*/
    private String checkManZw1;
    /**检查人员2. 对应其他检查人*/
    private String checkMan2;
    /**检查人员2职务.暂未用到*/
    private String checkManZw2;
    /**参检人数*/
    private Integer checkPersNum;
    /**检查时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkDate;
    /**其他违规情况和安全隐患*/
    private String aqyh;
    /**处理意见*/
    private String zgyj;

    /**根据娱乐系统增加的*/
    /**处理结果*/
    private String cljg;
    /**当前分数*/
    private Integer dqfs;
    /**扣分分数*/
    private Integer kffs;
    /**巡查记录id*/
    private String xunchaId;
    /**0检查不需要整改，1需要整改*/
    private Integer zhenggai;
    /**整改期限*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime zhenggaiDate;
    /**登記时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordDate;
    /**是否歌舞,0否,1是*/
    private Integer gw;
    /**是否游艺,0否,1是*/
    private Integer yy;
    /**是否按摩,0否,1是*/
    private Integer am;

    /**
     * 安全检查 开始结束日期时间
     */
    @TableField(exist = false)
    private String startTime;
    @TableField(exist = false)
    private String endTime;
}
