package com.cloud.portal.point.level.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.core.util.R;
import com.cloud.common.security.component.PermissionService;
import com.cloud.common.security.service.MicroUser;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.common.constant.PortalConstants;
import com.cloud.portal.point.level.mapper.PointLevelMapper;
import com.cloud.portal.point.level.model.PointLevel;
import com.cloud.portal.point.level.model.PointLevelChild;
import com.cloud.portal.point.level.service.PointLevelService;
import com.cloud.portal.point.score.service.PointScoreService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * @author ryt
 * @date Created in 2019/6/21 10:12
 * @description: 等级评定方案
 * @modified By:
 */
@Service
@AllArgsConstructor
public class PointLevelServiceImpl extends ServiceImpl<PointLevelMapper, PointLevel> implements PointLevelService {

    /**
     * 菜单权限控制
     */
    private final PermissionService permissionService;
    private final PointScoreService pointScoreService;


    /**
     * 获取等级评定方案分页信息
     *
     * @param page 分页信息
     * @param pointLevel 查询参数
     * @return
     */
    @Override
    public IPage<PointLevel> findPage(IPage<PointLevel> page, PointLevel pointLevel){
        boolean hsaUniversalPower = permissionService.hasPermission(PortalConstants.POINT_LEVEL_MANAGER_UNIVERSAL);
        if (!hsaUniversalPower) {
            if(null==pointLevel){
                pointLevel =new PointLevel();
            }
            MicroUser user = SecurityUtils.getUser();
            String deptCode = (user.getSysDept() == null || StringUtils.isBlank(user.getSysDept().getCode())) ? "000000" : user.getSysDept().getCode();
            pointLevel.setCreateBy(user.getId());
            pointLevel.setDataScope(deptCode);
        }
        return this.baseMapper.findPage(page,pointLevel);
    }

    /**
     * 根据方案id查询等级评定子项
     *
     * @param levelId 方案id
     * @return
     */
    @Override
    public List<PointLevelChild> findChildList(String levelId){
        return this.baseMapper.findChildList(levelId);
    }

    /**
     * 新增等级评定方案
     *
     * @param pointLevel
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R savePointLevel(PointLevel pointLevel){
        /*是否通用*/
        boolean hsaUniversalPower = permissionService.hasPermission(PortalConstants.POINT_LEVEL_MANAGER_UNIVERSAL);

        if (StringUtils.isNotBlank(pointLevel.getUniversal())&&PortalConstants.POINT_UNIVERSAL_TRUE.equals(pointLevel.getUniversal()) && !hsaUniversalPower) {
            return R.failed("您没有操作该项的权限、请联系管理员授权！");
        }

        /*设置参数*/
        if(StringUtils.isBlank(pointLevel.getId())){
            pointLevel.setId(UUID.randomUUID().toString());
        }
        pointLevel.setDelFlag(CommonConstants.STATUS_NORMAL);
        MicroUser user = SecurityUtils.getUser();
        pointLevel.setCreateBy(user.getId());
        pointLevel.setCreateTime(new Date());
        pointLevel.setUpdateBy(user.getId());
        pointLevel.setUpdateTime(pointLevel.getCreateTime());
        pointLevel.setDeptCode(user.getSysDept() == null ? null : user.getSysDept().getCode());
        pointLevel.setCreator(user.getSysUser().getName());
        pointLevel.setUpdater(pointLevel.getCreator());
        /*评定方案是否通用 默认为否*/
        pointLevel.setUniversal(StringUtils.isBlank(pointLevel.getUniversal()) ? PortalConstants.POINT_UNIVERSAL_FALSE : pointLevel.getUniversal());

        if (StringUtils.isNotBlank(pointLevel.getCityCode()) && !hsaUniversalPower) {
            return R.failed("您没有操作该项的权限、请联系管理员授权！");
        }else{
            String deptCode = pointLevel.getDeptCode();
            Integer deptLen = 12;
            String deptStart = "44";
            String deptEnd = "00";
            if (StringUtils.isBlank(deptCode) || !deptCode.startsWith(deptStart) || deptCode.length() != deptLen) {
                return R.failed("您没有操作该项的权限、您所在的机构代码不合符国标");
            } else {
                if(StringUtils.isNotBlank(pointLevel.getCityCode()) && hsaUniversalPower){
                    pointLevel.setCityCode(pointLevel.getCityCode());
                }else{
                    pointLevel.setCityCode((deptCode.substring(0, 4)).concat(deptEnd));
                }
            }
        }
        /*校验 地区、场所是否存在启动状态的评定方案*/
        if(pointLevel.getStatus().equals(PortalConstants.POINT_LEVEl_STATUS_OPEN)){
            Integer result = checkStatus(pointLevel);
            if(result > 0){
                return R.failed("该地区、场所已存在启动状态的评定方案、请先停用正在启动的评定方案或选择该方案状态为停用！");
            }
        }else{
            pointLevel.setStatus(PortalConstants.POINT_LEVEl_STATUS_CLOSE);
        }

        if(pointLevel.getPointLevelChildList().size()>0){
           boolean result=saveChild(pointLevel.getPointLevelChildList(),pointLevel.getId());
           if (result!=Boolean.TRUE){
               return R.failed("保存等级评定子项异常");
           }
        }
        return R.ok(this.baseMapper.insert(pointLevel));
    }

    /**
     * 更新等级评定方案
     *
     * @param pointLevel
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R updatePointLevel(PointLevel pointLevel){
        MicroUser user = SecurityUtils.getUser();
        pointLevel.setUpdateBy(user.getId());
        pointLevel.setUpdateTime(new Date());
        pointLevel.setUpdater(user.getSysUser().getName());
        if (StringUtils.isNotBlank(pointLevel.getCreateBy()) && user.getId().equals(pointLevel.getCreateBy())) {
            pointLevel.setCreator(pointLevel.getUpdater());
        }
        PointLevel tempPointLevel = baseMapper.selectById(pointLevel.getId());
        boolean hasOptPerm = hasOptPower(user, tempPointLevel);
        if (!hasOptPerm) {
            return R.failed("您没有操记录的权限、请联系管理员授权");
        }
        //获取旧等级评定方案 如果旧等级评定项不为空 查询该等级是否被使用到历史周期积分总计中
        List<PointLevelChild> oldChildList=baseMapper.findChildList(pointLevel.getId());
        if(oldChildList.size()>0){
            String ids="";
            for(int i=0;i<oldChildList.size();i++){
                ids += oldChildList.get(i).getId();
                if(i<oldChildList.size()-1){
                    ids += ",";
                }
            }
            Boolean result = pointScoreService.levelExitInScore(ids);
            if(result){
                //如果已被使用到历史周期中,先删除再插入一条新数据
                PointLevel temp = baseMapper.selectById(pointLevel.getId());
                baseMapper.deleteById(temp.getId());
                PointLevel newPointLevel=new PointLevel();
                BeanUtil.copyProperties(pointLevel, newPointLevel);
                newPointLevel.setId(UUID.randomUUID().toString());
                newPointLevel.setDelFlag(CommonConstants.STATUS_NORMAL);
                newPointLevel.setCreateBy(temp.getCreateBy());
                newPointLevel.setCreateTime(temp.getCreateTime());
                newPointLevel.setDeptCode(temp.getDeptCode());
                if(StringUtils.isBlank(newPointLevel.getCreator())){
                    newPointLevel.setCreator(temp.getCreator());
                }
                if(pointLevel.getPointLevelChildList().size()>0){
                    boolean rs = saveChild(pointLevel.getPointLevelChildList(),newPointLevel.getId());
                    if (!rs){
                        return R.failed("更新等级评定子项异常");
                    }
                }
                return R.ok(baseMapper.insert(newPointLevel));
            }
            this.baseMapper.removeChild(pointLevel.getId());
        }

        if(pointLevel.getPointLevelChildList().size()>0){
            boolean result = saveChild(pointLevel.getPointLevelChildList(),pointLevel.getId());
            if (result!=Boolean.TRUE){
                return R.failed("更新等级评定子项异常");
            }
        }
        return R.ok(this.baseMapper.updateById(pointLevel));
    }

    /**
     * 保存等级评定子项
     *
     * @param childList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean saveChild(List<PointLevelChild> childList,String levelId){
        List<PointLevelChild> pointLevelChildList = new ArrayList<>();
        for (PointLevelChild pc: childList){
            PointLevelChild child = new PointLevelChild();
            child.setLevelId(levelId);
            child.setType(pc.getType());
            child.setLinkType(pc.getLinkType());
            child.setLeftScore(pc.getLeftScore());
            child.setRightScore(pc.getRightScore()== null ? "" : pc.getRightScore());
            child.setRemark(pc.getRemark() == null ? "" : pc.getRemark());
            pointLevelChildList.add(child);
        }
        return this.baseMapper.saveAllChild(pointLevelChildList);
    }

    /**
     * 删除等级评定方案
     *
     * @param id 方案id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R removePointLevel(String id){
        PointLevel pointLevel = baseMapper.selectById(id);
        if(null != pointLevel){
            MicroUser user = SecurityUtils.getUser();
            boolean hasOptPerm = hasOptPower(user, pointLevel);
            if (!hasOptPerm) {
                return R.failed("您没有操记录的权限、请联系管理员授权");
            }
        }
        return R.ok(SqlHelper.delBool(baseMapper.deleteById(id)));
    }

    /**
     * 等级评定方案状态更新
     * @param id 方案id
     * @param status 状态（启用、停用）
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
   public R updateStatus(String id, String status){
        boolean loseParams = StringUtils.isBlank(id) || StringUtils.isBlank(status) || (!status.equals(PortalConstants.POINT_LEVEl_STATUS_OPEN) && !status.equals(PortalConstants.POINT_LEVEl_STATUS_CLOSE));
        if (loseParams) {
            return R.failed("信息缺失、操作失败");
        }
        MicroUser user = SecurityUtils.getUser();
        PointLevel tempPointLevel = baseMapper.selectById(id);
        boolean hasOptPower = hasOptPower(user, tempPointLevel);
        if (!hasOptPower) {
            return R.failed("您没有操记录的权限、请联系管理员授权！");
        }
        //判断当前是否存在已启动状态的评定方案
        if(status.equals(PortalConstants.POINT_LEVEl_STATUS_CLOSE)){
            if(baseMapper.findChildList(id).size()>0){
                Integer result = checkStatus(tempPointLevel);
                if(result > 0){
                    return R.failed("该地区、场所已存在启动状态的评定方案、若要启动该条方案请先停用正在启动的评定方案！");
                }
            }else{
                return R.failed("该方案没有等级评定子项，请先添加等级评定子项！");
            }
        }
        PointLevel pointLevel =new PointLevel();
        pointLevel.setUpdateBy(user.getId());
        pointLevel.setUpdater(user.getSysUser().getName());
        pointLevel.setUpdateTime(new Date());
        pointLevel.setId(id);
        pointLevel.setStatus(status.equals(PortalConstants.POINT_LEVEl_STATUS_OPEN) ? PortalConstants.POINT_LEVEl_STATUS_CLOSE : PortalConstants.POINT_LEVEl_STATUS_OPEN);
        return R.ok(SqlHelper.delBool(baseMapper.updateStatus(pointLevel)));
   }

    /**
     * 判断是否有操作评定方案的权限
     *
     * @param user
     * @param pointLevel
     * @return
     */
    private boolean hasOptPower(MicroUser user, PointLevel pointLevel) {
        if(null!=pointLevel){
            String deptCode = (user.getSysDept() == null || StringUtils.isBlank(user.getSysDept().getCode())) ? "000000" : ((user.getSysDept().getCode().substring(0, 4)).concat("00"));
            //如果没有通用 权限 、并且通用为true
            if(PortalConstants.POINT_UNIVERSAL_TRUE.equals(pointLevel.getUniversal())||(!user.getId().equals(pointLevel.getCreateBy()) && !deptCode.equals(pointLevel.getCityCode()))){

                boolean hsaUniversalPower = permissionService.hasPermission(PortalConstants.POINT_LEVEL_MANAGER_UNIVERSAL);
                if (!hsaUniversalPower) {
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }
    /**
     * 校验 地区、场所是否存在启动状态的评定方案
     *
     * @param pointLevel
     * @return 启动状态的方案数量
     */
    public Integer checkStatus(PointLevel pointLevel){
        PointLevel tempPointLevel =new PointLevel();
        if(pointLevel.getUniversal().equals(PortalConstants.POINT_UNIVERSAL_TRUE)) {
            tempPointLevel.setUniversal(PortalConstants.POINT_UNIVERSAL_TRUE);
        }else{
            tempPointLevel.setUniversal(PortalConstants.POINT_UNIVERSAL_FALSE);
            tempPointLevel.setPlaceType(pointLevel.getPlaceType());
            tempPointLevel.setCityCode(pointLevel.getCityCode());
        }
        Integer result = baseMapper.checkStatus(tempPointLevel);
        return result;
    }

    /**
     * 根据分值获取该地区、场所评定的等级
     * @param score 分值
     * @param placeType 所属场所
     * @param cityCode 所属地区
     * @return 等级、等级id
     */
    @Override
    public PointLevelChild getLevel(String placeType,String cityCode,Integer score){
        String level = baseMapper.getLevel(placeType,cityCode,score);
        PointLevelChild child = null;
        if (StringUtils.isNotBlank(level)){
            Integer arrLength = 2;
            String[] params = StringUtils.split(level, "#");
            if (ArrayUtils.isNotEmpty(params) && params.length == arrLength) {
                child = new PointLevelChild();
                child.setId(params[0]);
                child.setType(params[1]);
            }
        }
        return  child;
    }


}
