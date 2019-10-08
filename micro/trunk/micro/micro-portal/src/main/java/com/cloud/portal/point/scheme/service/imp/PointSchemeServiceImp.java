package com.cloud.portal.point.scheme.service.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.cloud.admin.api.entity.SysUser;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.core.util.R;
import com.cloud.common.security.component.PermissionService;
import com.cloud.common.security.service.MicroUser;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.common.constant.PortalConstants;
import com.cloud.portal.common.util.DateUtil;
import com.cloud.portal.point.level.model.PointLevelChild;
import com.cloud.portal.point.scheme.mapper.PointSchemeMapper;
import com.cloud.portal.point.scheme.model.Cycle;
import com.cloud.portal.point.scheme.model.PointScheme;
import com.cloud.portal.point.scheme.service.PointSchemeService;
import com.cloud.portal.point.score.mapper.PointScoreMapper;
import com.cloud.portal.point.score.model.PointScore;
import com.cloud.portal.point.util.ServiceApiUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lvlinc
 * @date Created in 2019/7/15
 * @description:积分方案管理
 * @modified By:lvlinc
 */
@Service
@AllArgsConstructor
public class PointSchemeServiceImp extends ServiceImpl<PointSchemeMapper, PointScheme> implements PointSchemeService {
    private final PointScoreMapper pointScore;
    /**
     * 菜单权限控制
     */
    private final PermissionService permissionService;

    @Override
    public IPage<PointScheme> findPage(IPage<PointScheme> page, PointScheme pointScheme) {
        boolean hasAreaPower = permissionService.hasPermission(PortalConstants.POINT_SCHEME_AREA);
        if (!hasAreaPower) {
            if(null==pointScheme){
                pointScheme =new PointScheme();
            }
            MicroUser user = SecurityUtils.getUser();
            String deptCode = (user.getSysDept() == null || StringUtils.isBlank(user.getSysDept().getCode())) ? "000000" : user.getSysDept().getCode();
            pointScheme.setCreateBy(user.getId());
            pointScheme.setDataScope(deptCode);
        }
        return baseMapper.findPage(page,pointScheme);
    }

    @Override
    public List<PointScheme> cycleList(String id) {
        return baseMapper.cycleList(id);
    }

    @Override
    public PointScheme starCycle() {
        return baseMapper.starCycle();
    }

    @Override
    public PointScheme areaStarCycle(PointScheme pointScheme) {
        return baseMapper.areaStarCycle(pointScheme);
    }

    @Override
    public R saveScheme(PointScheme pointScheme) {

        boolean hasAreaPower = permissionService.hasPermission(PortalConstants.POINT_SCHEME_AREA);
        boolean hasUntversalPower = permissionService.hasPermission(PortalConstants.POINT_SCHEME_UNIVERSAL);
        if (StringUtils.isBlank(pointScheme.getUniversal()) && !hasUntversalPower) {
            return R.failed("您没有操作该项的权限、请联系管理员授权！");
        }
        pointScheme = initSaveParams(pointScheme);
        if (StringUtils.isNotBlank(pointScheme.getCityCode()) && !hasAreaPower) {
            return R.failed("您没有操作该项的权限、请联系管理员授权！");
        } else {
            //适用地区 、如果没有不是省厅权限、适用地区则为空、需要截取当前用后部门所在地区
            String deptCode = pointScheme.getDeptCode();
            Integer deptLen = 12;
            String deptStart = "44";
            String deptEnd = "00";
            if (StringUtils.isBlank(deptCode) || !deptCode.startsWith(deptStart) || deptCode.length() != deptLen) {
                return R.failed("您没有操作该项的权限、您所在的机构代码不合符国标！");
            } else {
                pointScheme.setCityCode((deptCode.substring(0, 4)).concat(deptEnd));
            }
        }
        for (Cycle cycle:pointScheme.getCycleList()){
            cycle.setSchemeId(pointScheme.getId());
            baseMapper.saveEdit(cycle);
        }
        return R.ok(baseMapper.saveScheme(pointScheme));
    }

    /**
     * 保存时 初始化应有的参数
     *
     * @param pointScheme
     * @return
     */
    private PointScheme initSaveParams(PointScheme pointScheme) {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        MicroUser user = SecurityUtils.getUser();
        pointScheme.setId(uuid);
        pointScheme.setCreateBy(user.getId());
        pointScheme.setCreator(user.getSysUser().getName());
        pointScheme.setUpdateBy(user.getId());
        pointScheme.setUpdater(user.getSysUser().getName());
        pointScheme.setDelFlag(CommonConstants.STATUS_NORMAL);
        pointScheme.setCreateTime(new Date());
        pointScheme.setUpdateTime(pointScheme.getCreateTime());
        pointScheme.setDeptCode(user.getSysDept() == null ? null : user.getSysDept().getCode());
        pointScheme.setCycleList(getEditData(pointScheme));
        return pointScheme;
    }

    public List<Cycle> getEditData(PointScheme pointScheme) {
        // 年度1,半年度2,季度3,月度4   获取周期开始结束时间
        List<Cycle> rangeSet =new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String[] numStr =null;
        Date end_date = null;
        String beginDate =pointScheme.getStartTime();
        String endDate = pointScheme.getEndTime();
        //定义日期实例
        Calendar dd = Calendar.getInstance();
        //设置日期起始时间
        try {
            dd.setTime(sdf.parse(beginDate));
            end_date = sdf.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //判断是否到结束日期
        while(!dd.getTime().after(end_date)){
            Cycle cycle = new Cycle();
            if (pointScheme.getCycleType().equals("1")){
                numStr=  sdf.format(dd.getTime()).split("-",0);
                String Y = Integer.valueOf(numStr[0])+"";
                try {
                    cycle.setCycleStartTime(sdf1.parse(Y+"-01-01 00:00:00"));
                    cycle.setCycleEndTime(sdf1.parse(Y+"-12-31 23:59:59"));
                    cycle.setCycle(Y+"年度");
                    rangeSet.add(cycle);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dd.add(Calendar.YEAR, 1);
            }else if (pointScheme.getCycleType().equals("2")){
                numStr=  sdf.format(dd.getTime()).split("-",0);
                int M = Integer.parseInt(numStr[1]);
                String Y = Integer.valueOf(numStr[0])+"";
                if (M>=1 && M<=6){
                    cycle.setCycleStartTime(DateUtil.getHalfYearStartTime( dd.getTime()));
                    cycle.setCycleEndTime(DateUtil.getHalfYearEndTime( dd.getTime()));
                    cycle.setCycle(Y+"上半年");
                    rangeSet.add(cycle);
                }else if (M>=7 && M<=12){
                    cycle.setCycleStartTime(DateUtil.getHalfYearStartTime( dd.getTime()));
                    cycle.setCycleEndTime(DateUtil.getHalfYearEndTime( dd.getTime()));
                    cycle.setCycle(Y+"下半年");
                    rangeSet.add(cycle);
                }
                dd.add(Calendar.MONTH, 6);
            }else if (pointScheme.getCycleType().equals("3")){
                numStr=  sdf.format(dd.getTime()).split("-",0);
                String Y = Integer.valueOf(numStr[0])+"";
                int Q = Integer.parseInt(numStr[1]);
                if (Q>=1 && Q<=3){
                    cycle.setCycleStartTime(DateUtil.getCurrentQuarterStartTime( dd.getTime()));
                    cycle.setCycleEndTime(DateUtil.getCurrentQuarterEndTime( dd.getTime()));
                    cycle.setCycle(Y+"第一季度");
                    rangeSet.add(cycle);
                }else if (Q>=4 && Q<=6){
                    cycle.setCycleStartTime(DateUtil.getCurrentQuarterStartTime( dd.getTime()));
                    cycle.setCycleEndTime(DateUtil.getCurrentQuarterEndTime( dd.getTime()));
                    cycle.setCycle(Y+"第二季度");
                    rangeSet.add(cycle);
                }else if (Q>=7 && Q<=9){
                    cycle.setCycleStartTime(DateUtil.getCurrentQuarterStartTime( dd.getTime()));
                    cycle.setCycleEndTime(DateUtil.getCurrentQuarterEndTime( dd.getTime()));
                    cycle.setCycle(Y+"第三季度");
                    rangeSet.add(cycle);
                }else if (Q>=10 && Q<=12){
                    cycle.setCycleStartTime(DateUtil.getCurrentQuarterStartTime( dd.getTime()));
                    cycle.setCycleEndTime(DateUtil.getCurrentQuarterEndTime( dd.getTime()));
                    cycle.setCycle(Y+"第四季度");
                    rangeSet.add(cycle);
                }
                dd.add(Calendar.MONTH, 3);
            }else {
                String month = sdf.format(dd.getTime());
                cycle.setCycleStartTime(DateUtil.getCurrentMonthStartTime(dd.getTime()));
                cycle.setCycleEndTime(DateUtil.getCurrentMonthEndTime(dd.getTime()));
                cycle.setCycle(month+"月度");
                rangeSet.add(cycle);
                dd.add(Calendar.MONTH, 1);
            }
        }
        return rangeSet;
    }

    @Override
    public R updateScheme(PointScheme pointScheme) {
        MicroUser user = SecurityUtils.getUser();
        boolean hasOptPerm = hasOptPower(user, pointScheme);
        if (!hasOptPerm) {
            return R.failed("您没有操记录的权限、请联系管理员授权");
        }
        pointScheme.setCreateBy(user.getId());
        pointScheme.setUpdater(user.getSysUser().getName());
        pointScheme.setUpdateTime(new Date());
        //每次修改删除旧周期 重新生成周期时间
        baseMapper.removeEdit(pointScheme.getId());
        pointScheme.setCycleList(getEditData(pointScheme));
        for (Cycle cycle:pointScheme.getCycleList()){
            cycle.setSchemeId(pointScheme.getId());
            baseMapper.saveEdit(cycle);
        }
        pointScheme.setCycleList(null);
        return R.ok(baseMapper.updateById(pointScheme));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R removePointScheme(String id){
        PointScheme pointScheme = baseMapper.getByid(id);
        if(null != pointScheme){
            MicroUser user = SecurityUtils.getUser();
            boolean hasOptPerm = hasOptPower(user, pointScheme);
            if (!hasOptPerm) {
                return R.failed("您没有操记录的权限、请联系管理员授权!");
            }
        }
        return R.ok(baseMapper.removePointScheme(id));
    }

    @Override
    public R updateStatus(String id, String status) {
        //是否参数缺失
        boolean loseParams = StringUtils.isBlank(id) || StringUtils.isBlank(status) || (!status.equals(PortalConstants.POINT_INDEX_STATUS_OPEN) && !status.equals(PortalConstants.POINT_INDEX_STATUS_CLOSE));
        if (loseParams){
            return R.failed("参数丢失、操作失败");
        }
        MicroUser user = SecurityUtils.getUser();
        PointScheme tempPointScheme = baseMapper.getByid(id);
        boolean hasOptPower = hasOptPower(user,tempPointScheme);
        if (!hasOptPower) {
            return R.failed("您没有操记录的权限、 请联系管理员授权！");
        }
        //判断当前是否存在已启动状态的方案
        if(status.equals(PortalConstants.POINT_SCHEME_STATUS_CLOSE)){
            Integer result = checkStatus(tempPointScheme);
            if(result > 0){
                return R.failed("已存在启动的方案、请停用其他方案在启动！");
            }
        }

        PointScheme pointScheme = new PointScheme();
        pointScheme.setId(id);
        //替换参数、因为前端传过来是默认的状态、需要启用或者停用、需要交换当前状态
        pointScheme.setStatus(status.equals(PortalConstants.POINT_SCHEME_STATUS_OPEN) ? PortalConstants.POINT_SCHEME_STATUS_CLOSE : PortalConstants.POINT_SCHEME_STATUS_OPEN);
        pointScheme.setUpdateBy(SecurityUtils.getUser().getId());
        pointScheme.setUpdateTime(new Date());
        return R.ok(SqlHelper.delBool(baseMapper.updateStatus(pointScheme)));
    }

    @Override
    public boolean resetScore() {
        PointScheme pointScheme = baseMapper.starCycle();
        //重置该方案历史周期分数
        if (null == pointScheme) {
            PointScheme tmp = new PointScheme();
            tmp.setTableName(Arrays.asList(PointScore.hotelTableName));
            //历史周期保存至历史表
            baseMapper.savePointScore(tmp);
            //当前没有周期全部更新为空
            baseMapper.resetScore(tmp);
        }else {
            PointScore pointScore = new PointScore();
            pointScore.setCycleId(pointScheme.getId());
            pointScore.setTableName(Arrays.asList(PointScore.hotelTableName));
            pointScheme.setTableName(Arrays.asList(PointScore.hotelTableName));

            //历史周期保存至历史表
            baseMapper.savePointScore(pointScheme);
            //更新当前周期分值 等级评定信息
            baseMapper.updateScore(pointScheme);
            this.pointScore.resetCurrentCycleScore(pointScore);
            //重置关联分值
            this.pointScore.resetRelateScore(pointScore);
        }
        return Boolean.TRUE;
    }

    @Override
    public boolean deleteScore(String id) {
        PointScheme pointScheme = baseMapper.getByid(id);
        pointScheme.setTableName(Arrays.asList(PointScore.hotelTableName));
        //该方案所有周期保存至历史表
        baseMapper.saveSchemeScore(pointScheme);
        //该方案所有周期更新为空
        baseMapper.resetSchemeScore(pointScheme);
        //逻辑删除方案周期详情
        baseMapper.removeEdit(id);
        return Boolean.TRUE;
    }

    /**
     * 校验 是否存在启动状态的方案
     *
     * @param pointScheme
     * @return int
     */
    public int checkStatus(PointScheme pointScheme) {
        PointScheme tmpPointScheme = new PointScheme();
        tmpPointScheme.setStatus(PortalConstants.POINT_SCHEME_STATUS_OPEN);
        return baseMapper.checkStatus(tmpPointScheme);
    }
    /**
     * 判断是否有操作方案的权限
     *
     * @param user
     * @param pointScheme
     * @return
     */
    private boolean hasOptPower(MicroUser user, PointScheme pointScheme) {
        if (null != pointScheme) {
            String deptEnd = "00";
            //如果不是同一个地市、并且不是当前用户创建的
            String deptCode = (user.getSysDept() == null || StringUtils.isBlank(user.getSysDept().getCode())) ? "000000" : ((user.getSysDept().getCode().substring(0, 4)).concat(deptEnd));
            if (!user.getId().equals(pointScheme.getCreateBy()) && !deptCode.equals(pointScheme.getCityCode())) {
                boolean hasAreaPower = permissionService.hasPermission(PortalConstants.POINT_SCHEME_AREA);
                if (!hasAreaPower) {
                    return Boolean.FALSE;
                }
                boolean hsaUniversalPower = permissionService.hasPermission(PortalConstants.POINT_SCHEME_UNIVERSAL);
                //如果没有通用 权限 、并且通用为true
                if (!hsaUniversalPower && PortalConstants.POINT_UNIVERSAL_TRUE.equals(pointScheme.getUniversal())) {
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }


}
