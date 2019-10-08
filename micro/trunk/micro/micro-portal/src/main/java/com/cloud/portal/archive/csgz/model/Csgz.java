package com.cloud.portal.archive.csgz.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cloud.admin.api.annotation.LogField;
import com.cloud.portal.archive.common.base.model.DataModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
/**
 * @author lvlinc
 * @date Created in 2019/5/22 10:22:00
 * @description: 特行场所关注信息
 * @modified By:lvlinc
 */
@Data
public class Csgz extends DataModel<Csgz> {
    /**id*/
    @TableId(value = "id", type = IdType.UUID)
    @LogField(title = "主键")
   private String id;
    /**创建时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdate;
    /**来源系统*/
    private String lyxt;
    /**特行场所单位编号*/
    private String csdwbh;
    /**是否关注*/
    private int isgz;
    /**用户id*/
    private String userid;
    /**单位编号*/
    private String dwbh;
    /**单位名称*/
    private String dwmc;
    /**经营状态*/
    private String jyzt;
   /**行业类别*/
    private String hylb;
    /**所属地市*/
    private String ssds;
    /**所属区县*/
    private String ssqx;
    /**所属派出所*/
    private String  sspcs;
    /**单位地址*/
    private String  dwdz;
    /**来源表*/
    private String lyb;
}
