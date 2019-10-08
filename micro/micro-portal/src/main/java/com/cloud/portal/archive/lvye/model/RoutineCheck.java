package com.cloud.portal.archive.lvye.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author wengshij
 * @date Created in 2019/5/23 13:54
 * @description: 旅馆日常检查信息
 * @modified By:wengshij
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoutineCheck extends Model<RoutineCheck> {
    /**
     * 报告制度
     */
    private String bgzd;
    /**
     * 报告制度 备注
     */
    private String bgzdBz;
    /**
     * 被检查单
     */
    private String bjcdw;
    /**
     * 备注
     */
    private String bz;
    /***/
    private String cljg;
    /**
     *
     */
    private String clyj;
    /**
     * 财务报告制度
     */
    private String cwbgzd;
    /**
     * 财务报告制度 备注
     */
    private String cwbgzdBz;
    /**
     * 当天录入信息齐全
     */
    private String dtlrxx;
    /**
     * 当前录入信息备注
     */
    private String dtlrxxBz;
    /**
     * 检查单位
     */
    private String jcdw;
    /**
     * 检查单位中文名称
     */
    private String jcdwZh;
    /**
     * 检查方式
     */
    private String jcfs;
    /**
     * 检查ID
     */
    private String jcId;
    /**
     * 检查民警
     */
    private String jcmj;
    /**
     * 检查民警
     */
    private String jcmjTwo;
    /**
     * 检查民警 -中文
     */
    private String jcmjTwoZh;
    /**
     * 检查民警-中文
     */
    private String jcmjZh;
    /**
     * 检查时间
     */
    private String jcsj;
    /**
     * 来访登记制度
     */
    private String lfdjzd;
    /**
     * 来访登记制度 备注
     */
    private String lfdjzdBz;
    /**
     * 当天录入信息齐全
     */
    private String lkdjylr;
    /**
     * 当前录入信息齐全 备注
     */
    private String lkdjylrBz;
    /**
     * 录入
     */
    private String lr;
    /**
     * 录入人
     */
    private String lrr;
    /**
     * 录入时间
     */
    private String lrsj;
    /***/
    private String ltime;
    /**
     * 旅馆编号
     */
    private String noHotel;
    /**
     * 其他检查人
     */
    private String qtjcr;
    /**
     * 其他检查情况
     */
    private String qtqk;
    /***/
    private String qydzfzr;
    /**
     * 入住登记制度落实 (1合格 0 不合格)
     */
    private String rzdjzd;
    /**
     * 入住登记制度落实备注
     */
    private String rzdjzdBz;
    /**
     * 上传及时
     */
    private String scjs;
    /**
     * 上传及时 备注
     */
    private String scjsBz;
    /**
     * 上传数量与录入数量相符
     */
    private String scslylrslxf;
    /**
     * 传数量与录入数量相符 备注
     */
    private String scslylrslxfBz;
    /**
     * 消防设备
     */
    private String xfsbjhyq;
    /**
     * 消防设备 备注
     */
    private String xfsbjhyqBz;
    /***/
    private String xqzgrq;
    /**
     * 信息系统正常使用
     */
    private String xxxt;
    /**
     * 信息系统正常使用备注
     */
    private String xxxtBz;

    /**
     * 值班巡查制度
     */
    private String zbxczd;
    /**
     * 值班巡查制度 备注
     */
    private String zbxczdBz;
    /**
     * 查询条件-开始时间 字符串
     */
    private String startTime;
    /**
     * 查询条件-结束时间字符串
     */
    private String endTime;


}
