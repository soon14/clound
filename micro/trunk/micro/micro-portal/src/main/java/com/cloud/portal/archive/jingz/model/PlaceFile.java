package com.cloud.portal.archive.jingz.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author wengshij
 * @date Created in 2019/6/17 10:38
 * @description: 治安管辖单位附件信息
 * @modified By:wengshij
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PlaceFile extends Model<PlaceFile> {


    /**
     * 部门代码
     */
    private String departmentCode;
    /**
     * 文件
     */
    private byte[] files;
    /**
     * 附件名称
     */
    private String fjmc;
    /**
     * 管辖行业类型
     */
    private String gxhylx;
    /**
     * 上传人
     */
    private String scr;
    /**
     * 上传时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date scsj;
    /**
     * 系统编号
     */
    private String systemId;
    /**
     * 文件大小
     */
    private String wjdx;
    /**
     * 文件类型
     */
    private String wjlx;
    /**
     * 文件名
     */
    private String wjmc;
    /**
     * 治安单位编号
     */
    private String zadwbh;
    /**
     * 主表编号
     */
    private String zbbh;
    /**
     * 主表单位编号
     */
    private String zbdwbh;

    /***/
    private String reservation01;
    /***/
    private String reservation02;
    /***/
    private String reservation03;
    /***/
    private String reservation04;
    /***/
    private String reservation05;
    /***/
    private String reservation06;
    /***/
    private String reservation07;
    /***/
    private String reservation08;
    /***/
    private String reservation09;
    /***/
    private String reservation10;

}
