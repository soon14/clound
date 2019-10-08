package com.cloud.portal.point.detail.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.cloud.common.security.service.MicroUser;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.point.index.model.PointIndex;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author wengshij
 * @date Created in 2019/7/16 14:23
 * @description: 积分指标明细实体类 （包含自动积分指标明细和手动积分指标明细）
 * @modified By:wengshij
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class IndexDetail extends Model<IndexDetail> {

    /**
     * @param indexUdId    指标唯一标识符
     * @param indexVersion 指标版本号
     * @param pointType    指标类型
     * @param delFlag      删除标识 （0 正常 1 撤回）
     * @param cycleId      当前周期ID
     * @return
     */
    public static IndexDetail searchInitDetail(String indexUdId, String indexVersion, String pointType, String delFlag, String cycleId) {
        IndexDetail indexDetail = new IndexDetail();
        indexDetail.setIndexUdId(indexUdId);
        indexDetail.setIndexVersion(indexVersion);
        indexDetail.setPointType(pointType);
        indexDetail.setDelFlag(delFlag);
        indexDetail.setCycleId(cycleId);
        return indexDetail;

    }

    /**
     * @param indexUdId    指标唯一标识符
     * @param indexVersion 指标版本号
     * @param pointType    指标类型
     * @param delFlag      删除标识 （0 正常 1 撤回）
     * @param user         用户信息
     * @return
     */
    public static IndexDetail updateStatus(String indexUdId, String indexVersion, String pointType, String delFlag, MicroUser user) {
        IndexDetail indexDetail = searchInitDetail(indexUdId, indexVersion, pointType, delFlag, null);
        if (null == user) {
            user = SecurityUtils.getUser();
        }
        indexDetail.setUpdateBy(user.getId());
        indexDetail.setUpdater(user.getSysUser().getName());
        return indexDetail;
    }

    /**
     * 行政区划
     */
    private String cityCode;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 方案所属周期表ID
     */
    private String cycleId;
    /**
     * 场所所属派出所代码
     */
    private String deptCode;
    /**
     * 明细（扣分或加分详细说明）
     */
    private String detail;
    /**
     * 主键ID
     */
    private String id;
    /**
     * 积分指标标识符
     */
    private String indexUdId;
    /**
     * 积分指标版本号
     */
    private String indexVersion;
    /**
     * 场所主键ID
     */
    private String placeId;
    /**
     * 场所名称
     */
    private String placeName;
    /**
     * 行业类别（七行八所）
     */
    private String placeType;
    /**
     * 场所来源系统 字典（警综、旅业、机修、娱乐场所）
     */
    private String sourceType;
    /**
     * 总分（加减分值）
     */
    private Integer totalScore;
    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    /**
     * 加分时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bonusTime;

    /**
     * 创建人 （手动积分特有）
     */
    private String createBy;


    /**
     * 更新人 自动积分明细特有
     */
    private String updateBy;
    /**
     * 积分指标类型（自动积分和手动积分）
     */
    private String pointType;

    /**
     * 删除标识位
     * 0 标识正常项 1 标识已经被撤回的指标项
     */
    private String delFlag;
    /**
     * 更新者姓名
     */
    private String updater;
    /**
     * 创建者姓名
     */
    private String creator;
    /**
     * 创建者所属部门代码
     */
    private String creatorDept;
    /**
     * 次数
     */
    private Integer num;
    /**
     * 指标项信息
     */
    private PointIndex pointIndex;
    /**
     * 权限标识信息
     */
    @JsonIgnore
    @TableField(exist = false)
    private String dataScope;


}
