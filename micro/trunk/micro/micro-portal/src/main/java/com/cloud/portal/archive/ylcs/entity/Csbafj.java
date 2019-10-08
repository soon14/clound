package com.cloud.portal.archive.ylcs.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author ryt
 * @date Created in 2019/5/9 17:34
 * @description: 娱乐场所-场所备案附件(证件信息)
 * @modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Csbafj extends Model<Csbafj> {
    private static final long serialVersionUID = 1L;
    /**附件上传时间*/
    private String fjId;
    /**备案ID*/
    private String csbaId;
    /**附件名称*/
    private String fjMingcheng;
    /**附件大小*/
    private Long fjDaxiao;
    /**附件内容*/
    private byte[] fjNeirong;
    /**附件类型*/
    private String fjLeixingid;
    /**附件上传时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fjShijian;
    /**业务类型*/
    private String ywLx;
    /**附件类型名称*/
    private String fileTypeName;
}
