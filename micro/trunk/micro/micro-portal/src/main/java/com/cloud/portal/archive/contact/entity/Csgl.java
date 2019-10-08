package com.cloud.portal.archive.contact.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author ryt
 * @date Created in 2019/6/3 16:31
 * @description: 场所关联信息
 * @modified By:
 */
@TableName("t_thcs_glxx")
@Data
@EqualsAndHashCode(callSuper = true)
public class Csgl extends BaseModel<Csgl> {

    private static final long serialVersionUID = 1L;

    /** 场所id(警综) */
    private String jzdwbh;
    /** 其他来源系统(娱乐场所、机修、旅馆业) */
    private String lyxtType;
    /** 其他系统场所ID */
    private String qtdwbh;
    /** 创建时间 */
    private LocalDateTime createTime;

}
