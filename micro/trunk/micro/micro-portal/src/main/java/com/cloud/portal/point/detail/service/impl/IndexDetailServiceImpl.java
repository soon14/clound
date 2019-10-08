package com.cloud.portal.point.detail.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.cloud.common.core.util.R;
import com.cloud.common.data.util.DataScopeUtil;
import com.cloud.common.security.service.MicroUser;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.archive.contact.entity.Csgl;
import com.cloud.portal.archive.contact.service.CsglService;
import com.cloud.portal.common.constant.PortalConstants;
import com.cloud.portal.point.detail.mapper.IndexDetailMapper;
import com.cloud.portal.point.detail.model.IndexDetail;
import com.cloud.portal.point.detail.service.IndexDetailService;
import com.cloud.portal.point.index.model.PointIndex;
import com.cloud.portal.point.index.service.PointIndexService;
import com.cloud.portal.point.level.model.PointLevelChild;
import com.cloud.portal.point.scheme.model.PointScheme;
import com.cloud.portal.point.scheme.service.PointSchemeService;
import com.cloud.portal.point.score.model.PointScore;
import com.cloud.portal.point.util.ServiceApiUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author wengshij
 * @date Created in 2019/7/16 14:24
 * @description: 积分指标明细信息（包含自动积分指标明细和手动积分指标明细）
 * @modified By:wengshij
 */
@Service
@AllArgsConstructor
public class IndexDetailServiceImpl extends ServiceImpl<IndexDetailMapper, IndexDetail> implements IndexDetailService {

    private final CsglService relateService;
    private final PointSchemeService pointSchemeService;


    @Override
    public boolean indexExitInDetail(IndexDetail indexDetail) {
        Integer result = this.baseMapper.indexExitInDetail(indexDetail);
        return (result != null && result > 0);
    }

    @Override
    public boolean updateFlag(IndexDetail indexDetail) {
        return SqlHelper.delBool(this.baseMapper.updateFlag(indexDetail));
    }

    @Override
    public IPage<IndexDetail> countPage(IPage<IndexDetail> page, IndexDetail indexDetail) {
        if (StringUtils.isBlank(indexDetail.getPlaceId()) || StringUtils.isBlank(indexDetail.getCycleId())) {
            return new Page<>();
        }
        //默认为正常标识 1 标识已经已撤回的
        if (StringUtils.isBlank(indexDetail.getDelFlag())) {
            indexDetail.setDelFlag(PortalConstants.INDEX_DETAIL_TYPE_NORMAL);
        }

        return this.baseMapper.countPage(page, replacePlaceId(indexDetail, Boolean.FALSE));
    }

    @Override
    public IPage<IndexDetail> detailPage(IPage<IndexDetail> page, IndexDetail indexDetail) {
        return this.baseMapper.detailPage(page, replacePlaceId(indexDetail, Boolean.FALSE));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R passivePoint(IndexDetail indexDetail) {
        if (StringUtils.isBlank(indexDetail.getCycleId())) {
            PointScheme pointScheme = pointSchemeService.starCycle();
            indexDetail.setCycleId(pointScheme == null ? null : pointScheme.getId());
        }
        boolean paramLose = (null == indexDetail
                || StringUtils.isBlank(indexDetail.getCycleId())
                || StringUtils.isBlank(indexDetail.getPlaceId())
                || StringUtils.isBlank(indexDetail.getSourceType())
                || null == indexDetail.getNum() || indexDetail.getNum() <= 0
                || null == indexDetail.getTotalScore() );
        if (paramLose) {
            return R.failed("参数丢失、评分失败、请刷新后重试");
        }
        PointIndex pointIndex = ServiceApiUtil.getPointIndexById(indexDetail.getIndexUdId());
        boolean isPerfectPointIndex = null == pointIndex || StringUtils.isBlank(pointIndex.getVersion()) || StringUtils.isBlank(pointIndex.getUdId()) || null == pointIndex.getUpperLimit();
        if (isPerfectPointIndex) {
            return R.failed("评分失败、当前指标已失效、请刷新后重试");
        }
        indexDetail.setIndexVersion(pointIndex.getVersion());
        indexDetail.setIndexUdId(pointIndex.getUdId());
        indexDetail.setPointType(PortalConstants.POINT_PASSIVE);
        IndexDetail tempIndexDetail = new IndexDetail();
        //复制一份、手动积分信息
        BeanUtil.copyProperties(indexDetail, tempIndexDetail);
        Integer totalScore = tempIndexDetail.getTotalScore() + (tempIndexDetail.getNum() * pointIndex.getScore());
        indexDetail.setTotalScore(tempIndexDetail.getNum() * pointIndex.getScore());
        //替换信息、如果是警综数据、需要替换成其他系统的场所ID
        indexDetail = replacePlaceId(indexDetail, Boolean.TRUE);
        if (!PortalConstants.SOURCE_TYPE_JZ.equals(indexDetail.getSourceType())) {
            Csgl entity = relateService.findEntityById(null, indexDetail.getPlaceId(), Boolean.FALSE);
            if (null != entity) {
                tempIndexDetail.setPlaceId(entity.getJzdwbh());
                tempIndexDetail.setSourceType(PortalConstants.SOURCE_TYPE_JZ);
            }
        }
        //查看是否有上限的分值
        if (pointIndex.getUpperLimit() > 0) {
            if (indexDetail.getNum() > pointIndex.getUpperLimit()) {
                return R.failed("评分失败、已超过指标的最大上限值：" + pointIndex.getUpperLimit());
            }
            Integer result = this.baseMapper.countPassiveDetailTotalScore(indexDetail);
            //查看指标当前周期 当前场所 当前指标项的分值是否已经上限
            if (null != result && result != 0) {
                Integer totalNum = Math.abs(result / pointIndex.getScore());
                if (indexDetail.getNum() + totalNum > pointIndex.getUpperLimit()) {
                    return R.failed("评分失败、已超过指标的最大上限值：" + pointIndex.getUpperLimit() + " 剩余评分次数:" + (pointIndex.getUpperLimit() - totalNum));
                }
            }
        }
        //获取等级评定信息、如果是警综系统、需要拿回关联的场所信息【包括场所类型、行政区划】
        PointLevelChild pointLevelChild = ServiceApiUtil.getPointLevel(indexDetail.getPlaceType(), indexDetail.getCityCode(), totalScore);
        if (null == pointLevelChild || StringUtils.isBlank(pointLevelChild.getId()) || StringUtils.isBlank(pointLevelChild.getType())) {
            return R.failed("评分失败、无法重置分值、请刷新后重试！");
        }
        PointScore ps = new PointScore();
        ps.setCycleId(indexDetail.getCycleId());
        ps.setTableName(Arrays.asList(PointScore.hotelTableName));
        ps.setLevelId(pointLevelChild.getId());
        ps.setLevelType(pointLevelChild.getType());
        ps.setTotalScore(totalScore);
        ps.setSourceType(indexDetail.getSourceType());
        ps.setPlaceId(indexDetail.getPlaceId());
        //重置的是除了警综系统、其他业务系统的分值
        ServiceApiUtil.resetPlaceScore(ps);
        //如果前端传输过来的场所ID 和 重置关联后的场所ID不相等、说明存在关联信息、如果将两个有关联的场所积分重置
        if (!tempIndexDetail.getPlaceId().equals(indexDetail.getPlaceId())) {
            ps.setPlaceId(tempIndexDetail.getPlaceId());
            ps.setSourceType(tempIndexDetail.getSourceType());
            //重置警综系统特行场所的分值
            ServiceApiUtil.resetPlaceScore(ps);
        }
        MicroUser microUser = SecurityUtils.getUser();
        indexDetail.setUpdateBy(microUser.getId());
        indexDetail.setCreateBy(microUser.getId());
        indexDetail.setCreator(microUser.getSysUser().getName());
        indexDetail.setUpdater(microUser.getSysUser().getName());
        indexDetail.setCreatorDept(microUser.getSysDept().getCode());
        return R.ok(this.baseMapper.savePassiveDetail(indexDetail));
    }

    /**
     * 如果是警综系统的特行场所 需要替换成其关联的信息表ID 来查询扣分明细
     * 判断是业务系统是否为警综系统 - 如果是警综系统则需要拿出关联信息表ID、替换成其他业务系统的ID（分值）
     *
     * @param indexDetail
     * @return
     */
    private IndexDetail replacePlaceId(IndexDetail indexDetail, boolean containJoin) {
        boolean isJz = null != indexDetail && StringUtils.isNotBlank(indexDetail.getSourceType()) && PortalConstants.SOURCE_TYPE_JZ.equals(indexDetail.getSourceType());
        if (isJz) {
            Csgl entity = relateService.findEntityById(indexDetail.getPlaceId(), null, containJoin);
            if (null != entity && StringUtils.isNotBlank(entity.getQtdwbh())) {
                indexDetail.setPlaceId(entity.getQtdwbh());
                indexDetail.setSourceType(entity.getLyxtType());
                indexDetail.setCityCode(entity.getAreaCode());
                indexDetail.setPlaceType(entity.getHylx());
                indexDetail.setPlaceName(entity.getDwmc());
            }
        }
        return indexDetail;
    }

    /**
     * 按积分类型统计当天新增积分记录总数
     * @return
     */
    @Override
    public List<Map<String,Object>> totalTodayDetail() {
        IndexDetail indexDetail = new IndexDetail();
        indexDetail.setDataScope(DataScopeUtil.getDataScopeSql("a.creator_dept", "point_monitor_search"));
        return this.baseMapper.totalTodayDetail(indexDetail);
    }

    /**
     * 获取最新10条自动积分记录
     * @return
     */
    @Override
    public List<IndexDetail> findNewestAutomaticList(String pointSchemeid) {
        IndexDetail indexDetail = new IndexDetail();
        indexDetail.setDataScope(DataScopeUtil.getDataScopeSql("a.creator_dept", "point_monitor_search"));
        indexDetail.setCycleId(pointSchemeid);
        return this.baseMapper.findNewestAutomaticList(indexDetail);
    }
}
