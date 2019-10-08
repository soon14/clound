package com.cloud.portal.archive.csgz.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cloud.admin.api.annotation.LogField;
import com.cloud.portal.archive.common.base.model.DataModel;
import lombok.Data;

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
   private Date createdate;
    /**来源系统*/
    private String lyxt;
    /**特行场所单位编号*/
    private String csdwbh;
    /**是否关注*/
    private int isgz;
    /**用户id*/
    private String userid;
}
