package com.cloud.portal.point.score.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.common.core.util.R;
import com.cloud.common.data.util.DataScopeUtil;
import com.cloud.common.security.service.MicroUser;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.archive.contact.entity.Csgl;
import com.cloud.portal.archive.contact.service.CsglService;
import com.cloud.portal.common.constant.PortalConstants;
import com.cloud.portal.point.detail.model.IndexDetail;
import com.cloud.portal.point.detail.service.IndexDetailService;
import com.cloud.portal.point.level.model.PointLevelChild;
import com.cloud.portal.point.scheme.model.PointScheme;
import com.cloud.portal.point.scheme.service.PointSchemeService;
import com.cloud.portal.point.score.mapper.PointScoreMapper;
import com.cloud.portal.point.score.model.PointScore;
import com.cloud.portal.point.score.service.PointScoreService;
import com.cloud.portal.point.util.ServiceApiUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author wengshij
 * @date Created in 2019/7/24 9:59
 * @description: 特行场所历史分值
 * @modified By:wengshij
 */
@Service
@AllArgsConstructor
public class PointScoreServiceImpl extends ServiceImpl<PointScoreMapper, PointScore> implements PointScoreService {

    private final PointSchemeService pointSchemeService;
    private final CsglService relateService;
    private final IndexDetailService indexDetailService;

    /**
     * 重置积分
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R resetCurrentCycleScore() {
        /**
         * 因为重置分数只影响到当前周期的分值历史分值不在改变
         * 1、首先拿到当前周期的周期ID
         * 2、计算出所有地市和省厅级别的等级方案、每类（七行八所）及每个地市的等级方案均可能不一样、所以只能通过合并计算
         * 3、统计 手动积分和自动积分每个特行场所的总分、重新计算出分数【只限当前周期的分值】
         * 4、查看有没有关联的特行场所、如果有、所有警综系统的场所 均以四大系统为基准
         * 5、
         */
        PointScheme pointScheme = pointSchemeService.starCycle();
        if (null == pointScheme) {
            return R.failed("无法获取当前周期信息、请先定义方案周期！");
        }
        PointScore pointScore = new PointScore();
        pointScore.setCycleId(pointScheme.getId());
        //旅馆信息 分了21个地市的表 需要每张表更新 后期可考虑合并成一张表
        pointScore.setTableName(Arrays.asList(PointScore.hotelTableName));
        this.baseMapper.resetCurrentCycleScore(pointScore);
        //重置关联分值
        this.baseMapper.resetRelateScore(pointScore);
        return R.ok("积分重置成功");
    }

    @Override
    public IPage<PointScore> findCycleScorePage(IPage<PointScore> page, PointScore pointScore) {
        pointScore.setDataScope(DataScopeUtil.getDataScopeSql("A.DEPT_CODE", "point_score_ranking"));
        if (StringUtils.isBlank(pointScore.getCycleType())) {
            pointScore.setCycleType("0");
        }
        return this.baseMapper.findCycleScorePage(page, pointScore);
    }

    @Override
    public boolean levelExitInScore(String levelIds) {
        if (StringUtils.isBlank(levelIds)) {
            return Boolean.FALSE;
        }
        List<String> stringList = Arrays.asList(StringUtils.split(levelIds, ","));
        return this.baseMapper.levelExitInScore(stringList) > 0;
    }

    @Override
    public void resetPlaceScore(PointScore pointScore) {
        this.baseMapper.resetPlaceScore(pointScore);
    }

    /**
     * 单个场所撤回积分算法规则
     * 1、根据当前场所 当前周期和指定的指标项、只能撤回当前周期 当前场所 当前指标的场所积分
     * 2、根据前端传输过来的分值【已经处理好撤回的分值 剩余分值=总分-撤回的分值】、
     * 计算出这个场所分值所属的等级评定等级（场所类型、场所地市和场所分值）
     * 3、关联积分重置计算、如果是警综业务系统的场所、需要查询其关联的业务系统的场所信息、积分规则以其他业务系统为准
     * 、如果是其他业务系统的场所、需要查询出其关联的警综系统场所信息、等级评定计算均是以其他业务系统场所的类型和地市计算
     * 4、将 自动积分或者手动积分项的分值删除
     *
     * @param indexDetail
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R rollBackScore(IndexDetail indexDetail) {
        boolean isLose = StringUtils.isBlank(indexDetail.getPlaceId())
                || StringUtils.isBlank(indexDetail.getIndexVersion())
                || StringUtils.isBlank(indexDetail.getPlaceId())
                || StringUtils.isBlank(indexDetail.getCycleId())
                || null == indexDetail.getTotalScore();
        if (isLose) {
            return R.failed("参数丢失、撤回失败、请刷新后重试！");
        }
        IndexDetail oldIndexDetail = new IndexDetail();
        BeanUtil.copyProperties(indexDetail, oldIndexDetail);

        boolean isNotJz = StringUtils.isNotBlank(indexDetail.getSourceType()) && !PortalConstants.SOURCE_TYPE_JZ.equals(indexDetail.getSourceType());
        boolean isJz = StringUtils.isNotBlank(indexDetail.getSourceType()) && PortalConstants.SOURCE_TYPE_JZ.equals(indexDetail.getSourceType());
        if (isJz) {
            //根据警综系统ID 获取其关联的其他业务系统ID
            Csgl entity = relateService.findEntityById(indexDetail.getPlaceId(), null, Boolean.TRUE);
            if (null != entity && StringUtils.isNotBlank(entity.getQtdwbh())) {
                indexDetail.setPlaceId(entity.getQtdwbh());
                indexDetail.setSourceType(entity.getLyxtType());
                indexDetail.setCityCode(entity.getAreaCode());
                indexDetail.setPlaceType(entity.getHylx());

            }
        } else if (isNotJz) {
            //根据业务系统ID 获取关联的警综系统ID
            Csgl entity = relateService.findEntityById(null, indexDetail.getPlaceId(), Boolean.FALSE);
            if (null != entity && StringUtils.isNotBlank(entity.getJzdwbh())) {
                oldIndexDetail.setPlaceId(entity.getJzdwbh());
                oldIndexDetail.setSourceType(PortalConstants.SOURCE_TYPE_JZ);
            }
        }

        //获取等级评定信息、如果是警综系统、需要拿回关联的场所信息【包括场所类型、行政区划】
        PointLevelChild pointLevelChild = ServiceApiUtil.getPointLevel(indexDetail.getPlaceType(), indexDetail.getCityCode(), indexDetail.getTotalScore());
        if (null == pointLevelChild || StringUtils.isBlank(pointLevelChild.getId()) || StringUtils.isBlank(pointLevelChild.getType())) {
            return R.failed("重置分值失败、无法获取评定等级信息、请刷新后重试！");
        }

        PointScore pointScore = new PointScore();
        pointScore.setSourceType(indexDetail.getSourceType());
        pointScore.setLevelId(pointLevelChild.getId());
        pointScore.setLevelType(pointLevelChild.getType());
        pointScore.setTotalScore(indexDetail.getTotalScore());
        pointScore.setPlaceId(indexDetail.getPlaceId());
        pointScore.setCycleId(indexDetail.getCycleId());
        pointScore.setTableName(Arrays.asList(PointScore.hotelTableName));
        //重置的是除了警综系统、其他业务系统的分值
        this.baseMapper.resetPlaceScore(pointScore);
        //如果前端传输过来的场所ID 和 重置关联后的场所ID不相等、说明存在关联信息、如果将两个有关联的场所积分重置
        if (!oldIndexDetail.getPlaceId().equals(indexDetail.getPlaceId())) {
            pointScore.setPlaceId(oldIndexDetail.getPlaceId());
            pointScore.setSourceType(oldIndexDetail.getSourceType());
            //重置警综系统特行场所的分值
            this.baseMapper.resetPlaceScore(pointScore);
        }
        MicroUser user = SecurityUtils.getUser();
        indexDetail.setUpdateBy(user.getId());
        indexDetail.setUpdater(user.getSysUser().getName());
        indexDetail.setDelFlag(PortalConstants.INDEX_DETAIL_TYPE_BACK);
        return R.ok(indexDetailService.updateFlag(indexDetail));
    }

    @Override
    public PointScore findPlaceScore(String placeId) {
        return this.baseMapper.findPlaceScore(placeId);
    }

    @Override
    public List<PointScore> getPlaceAllScore(String placeId) {
        return this.baseMapper.getPlaceAllScore(placeId);
    }

    @Override
    public void resetAllScore() {
        PointScheme pointScheme = pointSchemeService.starCycle();
        String cycleId = null == pointScheme ? null : pointScheme.getId();
        String isInit = null == pointScheme ? null : pointScheme.getIsInit();
        Integer defaultScore = null == pointScheme ? null : pointScheme.getDefaultScore();
        this.baseMapper.resetAllScore(cycleId, isInit, defaultScore);
    }

    @Override
    public List<PointScore> findCycleScoreList(PointScore pointScore) {
        pointScore.setDataScope(DataScopeUtil.getDataScopeSql("A.DEPT_CODE", "point_score_ranking"));
        if (StringUtils.isBlank(pointScore.getCycleType())) {
            pointScore.setCycleType("0");
        }
        return this.baseMapper.findCycleScoreList(pointScore);
    }

    @Override
    public PointScore getCurrentCycleScoreByPlaceId(String placeId) {
        return this.baseMapper.getCurrentCycleScoreByPlaceId(placeId);
    }
}
