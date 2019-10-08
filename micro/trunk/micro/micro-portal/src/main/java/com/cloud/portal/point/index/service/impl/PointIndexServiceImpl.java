package com.cloud.portal.point.index.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.cloud.admin.api.entity.SysDict;
import com.cloud.admin.api.entity.SysUser;
import com.cloud.admin.api.feign.RemoteDictService;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.core.util.R;
import com.cloud.common.security.component.PermissionService;
import com.cloud.common.security.service.MicroUser;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.common.constant.PortalConstants;
import com.cloud.portal.common.util.DateUtil;
import com.cloud.portal.point.detail.model.IndexDetail;
import com.cloud.portal.point.detail.service.IndexDetailService;
import com.cloud.portal.point.index.mapper.PointIndexMapper;
import com.cloud.portal.point.index.model.IndexTree;
import com.cloud.portal.point.index.model.PointIndex;
import com.cloud.portal.point.index.service.PointIndexService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wengshij
 * @date Created in 2019/6/20 10:12
 * @description: 积分指标（手动与自动）
 * @modified By:wengshij
 */
@Service
@AllArgsConstructor
public class PointIndexServiceImpl extends ServiceImpl<PointIndexMapper, PointIndex> implements PointIndexService {


    private final IndexDetailService indexDetailService;
    /**
     * 菜单权限控制
     */
    private final PermissionService pms;
    /**
     * 微服务远程调用字典信息
     */
    private final RemoteDictService remoteDictService;
    /**
     * redis 缓存信息
     */
    private final CacheManager cacheManager;


    private final String CACHE_KEY = "point_index";


    /**
     * 保存时 初始化应有的参数
     *
     * @param pointIndex
     * @return
     */
    private PointIndex initSaveParams(PointIndex pointIndex) {
        MicroUser user = SecurityUtils.getUser();
        SysUser sysUser = user.getSysUser();
        pointIndex.setCreateBy(user.getId());
        pointIndex.setDeptCode(user.getSysDept() == null ? null : user.getSysDept().getCode());
        pointIndex.setUpdater(sysUser.getName());
        pointIndex.setCreator(sysUser.getName());
        pointIndex.setUpdateBy(user.getId());
        pointIndex.setDelFlag(CommonConstants.STATUS_NORMAL);
        pointIndex.setCreateTime(new Date());
        //默认状态下 为手动积分指标
        pointIndex.setType(StringUtils.isBlank(pointIndex.getType()) ? PortalConstants.POINT_PASSIVE : pointIndex.getType());
        pointIndex.setUpdateTime(pointIndex.getCreateTime());
        pointIndex.setVersion(DateUtil.getTimeUdId());
        return pointIndex;
    }

    @Override
    @CacheEvict(value = CACHE_KEY, allEntries = true)
    public R saveIndex(PointIndex pointIndex) {
        boolean hasAreaPower = pms.hasPermission(PortalConstants.POINT_INDEX_MANAGER_AREA_POWER);
        boolean hasTypePower = pms.hasPermission(PortalConstants.POINT_INDEX_MANAGER_TYPE_POWER);
        if (StringUtils.isBlank(pointIndex.getType()) && !hasTypePower) {
            return R.failed("您没有操作该项的权限、请联系管理员授权！");
        }
        pointIndex = initSaveParams(pointIndex);
        if (StringUtils.isNotBlank(pointIndex.getCity()) && !hasAreaPower) {
            return R.failed("您没有操作该项的权限、请联系管理员授权！");
        } else {
            //适用地区 、如果没有不是省厅权限、适用地区则为空、需要截取当前用后部门所在地区
            String deptCode = pointIndex.getDeptCode();
            Integer deptLen = 12;
            String deptStart = "44";
            String deptEnd = "00";
            if (StringUtils.isBlank(deptCode) || !deptCode.startsWith(deptStart) || deptCode.length() != deptLen) {
                return R.failed("您没有操作该项的权限、您所在的机构代码不合符国标！");
            } else {
                pointIndex.setCity((deptCode.substring(0, 4)).concat(deptEnd));
            }
        }
        return R.ok(baseMapper.insert(pointIndex));
    }

    @Override
    @CacheEvict(value = CACHE_KEY, allEntries = true)
    public R updateIndex(PointIndex pointIndex) {

        MicroUser user = SecurityUtils.getUser();
        pointIndex.setUpdateTime(new Date());
        pointIndex.setUpdateBy(user.getId());
        pointIndex.setUpdater(user.getSysUser().getName());
        if (StringUtils.isNotBlank(pointIndex.getCreateBy()) && user.getId().equals(pointIndex.getCreateBy())) {
            pointIndex.setCreator(pointIndex.getUpdater());
        }

        //获取当前指标项旧项
        PointIndex oldPointIndex = baseMapper.selectById(pointIndex.getId());
        //查询该指标项是否已经被使用到积分明细中(不管是正常状态的积分明细还是撤回的积分明细)
        boolean indexExitInDetail = indexDetailService.indexExitInDetail(IndexDetail.searchInitDetail(oldPointIndex.getUdId(), oldPointIndex.getVersion(), oldPointIndex.getType(), null, null));
        if (indexExitInDetail) {
            //如果该指标已经被使用、则需要先删除打个版本标识位、再插入一条新的数据
            baseMapper.deleteById(oldPointIndex.getId());
            PointIndex newPointIndex = new PointIndex();
            BeanUtil.copyProperties(oldPointIndex, newPointIndex);
            newPointIndex.setId(null);
            newPointIndex.setStatus(pointIndex.getStatus());
            newPointIndex.setUpdateTime(pointIndex.getUpdateTime());
            newPointIndex.setUpdateBy(pointIndex.getUpdateBy());
            newPointIndex.setUpdater(pointIndex.getUpdater());
            if (StringUtils.isNotBlank(pointIndex.getCreator())) {
                newPointIndex.setCreator(pointIndex.getCreator());
            }
            //重新生成一个版本号
            newPointIndex.setVersion(DateUtil.getTimeUdId());
            newPointIndex.setName(pointIndex.getName());
            newPointIndex.setStatus(pointIndex.getStatus());
            newPointIndex.setRemark(pointIndex.getRemark());
            newPointIndex.setScore(pointIndex.getScore());
            newPointIndex.setUpperLimit(pointIndex.getUpperLimit());
            return R.ok(baseMapper.insert(newPointIndex));

        } else {
            return R.ok(baseMapper.updateById(pointIndex));
        }

    }

    @Override
    public Boolean udIdExit(String id, String udId) {
        Integer result = StringUtils.isBlank(udId) ? 0 : baseMapper.udIdExit(id, udId);
        return (result == null || result <= 0) ? Boolean.FALSE : Boolean.TRUE;
    }

    @Override
    @CacheEvict(value = CACHE_KEY, allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public R removeById(String id) {
        PointIndex pointIndex = baseMapper.selectById(id);
        if (null != pointIndex) {
            MicroUser user = SecurityUtils.getUser();
            boolean hasOptPerm = hasOptPower(user, pointIndex);
            if (!hasOptPerm) {
                return R.failed("您没有操记录的权限、请联系管理员授权！");
            }
        }
        return R.ok(SqlHelper.delBool(baseMapper.deleteById(id)));
    }


    @Override
    @CacheEvict(value = CACHE_KEY, allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public R updateStatus(String id, String status) {
        //是否参数缺失
        boolean loseParams = StringUtils.isBlank(id) || StringUtils.isBlank(status) || (!status.equals(PortalConstants.POINT_INDEX_STATUS_OPEN) && !status.equals(PortalConstants.POINT_INDEX_STATUS_CLOSE));
        if (loseParams) {
            return R.failed("参数丢失、操作失败!");
        }
        MicroUser user = SecurityUtils.getUser();
        PointIndex tempPointIndex = baseMapper.selectById(id);
        boolean hasOptPower = hasOptPower(user, tempPointIndex);
        if (!hasOptPower) {
            return R.failed("您没有操记录的权限、请联系管理员授权！");
        }
        PointIndex pointIndex = new PointIndex();
        pointIndex.setId(id);
        //替换参数、因为前端传过来是默认的状态、需要启用或者停用、需要交换当前状态
        pointIndex.setStatus(status.equals(PortalConstants.POINT_INDEX_STATUS_OPEN) ? PortalConstants.POINT_INDEX_STATUS_CLOSE : PortalConstants.POINT_INDEX_STATUS_OPEN);
        pointIndex.setUpdateBy(user.getId());
        pointIndex.setUpdater(user.getSysUser().getName());
        //如果是停用指标项 、则需要撤回所有的分值
        if (PortalConstants.POINT_INDEX_STATUS_CLOSE.equals(pointIndex.getStatus())) {
            indexDetailService.updateFlag(IndexDetail.updateStatus(tempPointIndex.getUdId(), tempPointIndex.getVersion(), tempPointIndex.getType(), PortalConstants.INDEX_DETAIL_TYPE_BACK, user));
        }
        return R.ok(SqlHelper.delBool(baseMapper.updateStatus(pointIndex)));
    }


    @Override
    public IPage<PointIndex> findPage(IPage<PointIndex> page, PointIndex pointIndex) {
        return this.baseMapper.findPage(page, hasSearchPower(pointIndex));
    }

    @Override
    public List<PointIndex> findList(PointIndex pointIndex) {
        return this.baseMapper.findList(hasSearchPower(pointIndex));
    }

    @Override
    public List<IndexTree> getIndexTree(String indexType, String placeType) {
        PointIndex pointIndex = new PointIndex();
        pointIndex.setPlaceType(placeType);
        pointIndex.setType(StringUtils.isBlank(indexType) ? PortalConstants.POINT_PASSIVE : indexType);
        pointIndex.setUpdateBy(SecurityUtils.getUser().getId());

        if (StringUtils.isBlank(placeType)) {
            Cache cache = cacheManager.getCache(CACHE_KEY);
            Object object = null != cache ? cache.get(pointIndex.getUpdateBy() + "-" + pointIndex.getType()) : null;
            if (null != object) {
                return (List<IndexTree>) ((SimpleValueWrapper) object).get();
            }

        }
        String dictPlaceType = "HYLX_DL";
        //设置默认查询条件 、默认查询所有启用状态的指标、并且类型是
        pointIndex.setStatus(PortalConstants.POINT_INDEX_STATUS_OPEN);
        List<PointIndex> pointIndexList = this.findList(pointIndex);
        List<SysDict> sysDictList = remoteDictService.getDictList(dictPlaceType);
        List<IndexTree> indexTreeList = sysDictList.stream().filter(sysDict -> {
            if (StringUtils.isBlank(pointIndex.getPlaceType())) {
                return Boolean.TRUE;
            } else {
                return pointIndex.getPlaceType().equals(sysDict.getValue());
            }
        }).sorted(Comparator.comparingInt(SysDict::getSort)).map(sysDict -> {
            IndexTree indexTree = new IndexTree();
            indexTree.setLabel(sysDict.getLabel());
            indexTree.setId(sysDict.getId());
            indexTree.setParentId("0");
            //用来标识不能选择父级
            indexTree.setValue("0#0#-".concat(sysDict.getValue()));
            indexTree.setDisabled(Boolean.TRUE);
            indexTree.setParent(Boolean.TRUE);
            List<Map<String, Object>> childrenTree = pointIndexList.stream().filter(pointIndex1 -> sysDict.getValue().equals(pointIndex1.getPlaceType()))
                    .sorted(Comparator.comparing(PointIndex::getUpdateTime))
                    .map(pointIndex2 -> {
                        Map<String, Object> map = new HashMap<>(6);
                        map.put("label", pointIndex2.getName());
                        map.put("remark", pointIndex2.getRemark());
                        map.put("upperLimit", pointIndex2.getUpperLimit());
                        map.put("score", pointIndex2.getScore());
                        map.put("version",pointIndex2.getVersion());
                        map.put("udId",pointIndex2.getUdId());
                        map.put("id", pointIndex2.getId());
                        map.put("parentId", indexTree.getId());
                        map.put("value", pointIndex2.getUdId().concat(PortalConstants.POINT_INDEX_VERSION_UID_LINK).concat(pointIndex2.getVersion()));
                        map.put("parent", Boolean.FALSE);
                        map.put("isDisabled", Boolean.FALSE);
                        return map;
                    }).collect(Collectors.toList());
            indexTree.setChildren(childrenTree);
            return indexTree;
        }).collect(Collectors.toList());
        if (StringUtils.isBlank(placeType)) {
            cacheManager.getCache(CACHE_KEY).put(pointIndex.getUpdateBy() + "-" + pointIndex.getType(), indexTreeList);
        }
        return indexTreeList;
    }

    @Override
    public PointIndex findIndexByUdIdAndVersion(String udId, String version) {
        if (StringUtils.isBlank(udId) || StringUtils.isBlank(version)) {
            return null;
        }
        return this.baseMapper.findIndexByUdIdAndVersion(udId, version);
    }

    /**
     * 查询列表时初始化查询条件
     *
     * @param pointIndex
     * @return
     */
    private PointIndex hasSearchPower(PointIndex pointIndex) {
        boolean hasAreaPower = pms.hasPermission(PortalConstants.POINT_INDEX_MANAGER_AREA_POWER);
        //判断是否有访问其他区域的权限
        if (!hasAreaPower) {
            if (null == pointIndex) {
                pointIndex = new PointIndex();
            }
            MicroUser user = SecurityUtils.getUser();
            String deptCode = (user.getSysDept() == null || StringUtils.isBlank(user.getSysDept().getCode())) ? "000000" : user.getSysDept().getCode();
            pointIndex.setDataScope(deptCode);
            pointIndex.setCreateBy(user.getId());
        }
        return pointIndex;
    }

    /**
     * 判断是否有操作指标的权限
     *
     * @param user
     * @param pointIndex
     * @return
     */
    private boolean hasOptPower(MicroUser user, PointIndex pointIndex) {
        if (null != pointIndex) {
            String deptEnd = "00";
            //如果不是同一个地市、并且不是当前用户创建的
            String deptCode = (user.getSysDept() == null || StringUtils.isBlank(user.getSysDept().getCode())) ? "000000" : ((user.getSysDept().getCode().substring(0, 4)).concat(deptEnd));
            if (!user.getId().equals(pointIndex.getCreateBy()) && !deptCode.equals(pointIndex.getCity())) {
                boolean hasAreaPower = pms.hasPermission(PortalConstants.POINT_INDEX_MANAGER_AREA_POWER);
                if (!hasAreaPower) {
                    return Boolean.FALSE;
                }
                boolean hasTypePower = pms.hasPermission(PortalConstants.POINT_INDEX_MANAGER_TYPE_POWER);
                //如果没有指标 类型权限 、并且是自动指标、
                if (!hasTypePower && PortalConstants.POINT_AUTO.equals(pointIndex.getType())) {
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }


}
