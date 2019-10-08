package com.cloud.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wuxx
 * @date 2019/3/4 9:50
 * @description: 文件实体
 * @modified By:
 **/
@Data
public class SysFile  extends Model<SysFile> {

    /**
     * 文件id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    /**
     * 关联表主键id
     */
    private String tbPk;
    /**
     * 关联表名称
     */
    private String tbName;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 扩展名
     */
    private String extName;
    /**
     * 文件路径
     */
    private String path;
    /**
     * bucket名称
     */
    private String bucketName;
    /**
     * 文件大小
     */
    private Float fileSize;
    /**
     * 创建人所属单位（code）
     */
    private String createDeptCode;
    /**
     * 修改人所属单位
     */
    private String updateDeptCode;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;
    /**
     * 创建人id
     */
    private String createBy;
    /**
     * 修改人id
     */
    private String updateBy;
    /**
     * 备注
      */
    private String remarks;
    /**
     * bolb 文件流
     */
    private byte[]   files;
    /**
     * 创建人所属单位（中文）
     */
    @TableField(exist = false)
    private String createDeptName;

    /**
     * 创建人所属单位（中文）
     */
    @TableField(exist = false)
    private String storageModel;

    /**
     * 文件存储策略 数据库存储
     */
    public static final String STORAGE_MODEL_DB = "db";
    /**
     * 文件存储策略 磁盘和数据库存储
     */
    public static final String STORAGE_MODEL_BOTH = "both";
    /**
     * 文件存储策略 磁盘存储
     */
    public static final String STORAGE_MODEL_DISK = "disk";
}