package com.cloud.cas.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wengshij
 * @date Created in 2019/8/27 15:48
 * @description:
 * @modified By:wengshij
 */
@Data
public class CasUser implements Serializable {
    /**
     * 统一用户ID
     */
    private String userId;
    /**
     * 统一用户身份证
     */
    private String idCode;
    /**
     * 姓名
     */
    private String name;
    /**
     * 警号
     */
    private String policeNumber;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 部门代码
     */
    private String deptCode;
    /**
     * 所属警钟
     */
    private String policeCategory;
}
