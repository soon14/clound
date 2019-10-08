package com.cloud.portal.archive.jingz.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author wengshij
 * @date Created in 2019/6/18 14:06
 * @description: 治安管辖单位照片信息（从业人员、单位照片信息）
 * @modified By:wengshij
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PhotoEntity  extends Model<PhotoEntity> {

    /**创建时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;
    /**创建者*/
    private String creator;
    /**删除标志*/
    private String deleteFlag;
    /**部门编号*/
    private String departmentCode;
    /**下传标志*/
    private String downloadFlag;
    /**最终修改人*/
    private String lastUpdatedBy;
    /**最后修改时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastUpdatedTime;
    /**录入单位*/
    private String lrdw;
    /**录入人*/
    private String lrr;
    /**录入时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lrsj;
    /**照片*/
    private byte[] photo;
    /**照片名称*/
    private String photoName;
    /**照片编号*/
    private String photoNo;
    /**照片登记时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date photoRegTime;
    /**照片来源*/
    private String photoSource;
    /**照片类别*/
    private String photoType;
    /**更新时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date refreshTime;
    /**密级*/
    private String securityGrade;
    /**系统编号*/
    private String systemId;
    /**上传标志*/
    private String uploadFlag;
    /**业务表编号*/
    private String ywbbh;

    /**保留字段1*/
    private String reservation01;
    /**保留字段2*/
    private String reservation02;
    /**保留字段3*/
    private String reservation03;
    /**保留字段4*/
    private String reservation04;
    /**保留字段5*/
    private String reservation05;
    /**保留字段6*/
    private String reservation06;
    /**保留字段7*/
    private String reservation07;
    /**保留字段8*/
    private String reservation08;
    /**保留字段9*/
    private String reservation09;
    /**保留字段10*/
    private String reservation10;

}
