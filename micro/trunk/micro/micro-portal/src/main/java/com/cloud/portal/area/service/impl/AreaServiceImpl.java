package com.cloud.portal.area.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.core.util.R;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.area.entity.Area;
import com.cloud.portal.area.mapper.AreaMapper;
import com.cloud.portal.area.service.AreaService;
import com.cloud.portal.area.util.AreaUtil;
import com.cloud.portal.common.constant.PortalConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author ryt
 * @date Created in 2019/5/27 14:58
 * @description: 区域service实现
 * @modified By:
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {

    /**
     * 获取所有行政区划
     *
     *@param cacheKey 缓存key值
     * @return
     */
    @Override
    @Cacheable(value = "area_details", key = "#cacheKey" )
    public List<Area> findAll(String cacheKey){
        return this.list(Wrappers.emptyWrapper())
                .stream()
                .sorted(Comparator.comparingInt(Area::getSort))
                .collect(Collectors.toList());
    }


    /**
     * 返回当前用户所属区域范围集合
     * @param area
     * @return
     */
    @Override
    public List<Area> getAreaList(Area area) {
        List<Area> areaList;
        String code = area.getCode();
        if(StringUtils.isBlank(code)){
            code = SecurityUtils.getUser().getSysDept().getCode();
        }
        String level = AreaUtil.getDeptLevel(code);

        if (StringUtils.isNotBlank(level)){
            if (PortalConstants.DEPT_LEVEL_ALL.equals(level)){
                code = code.substring(0,2);
            } else if (PortalConstants.DEPT_LEVEL_CITY.equals(level)){
                code = code.substring(0,4);
            }else if(PortalConstants.DEPT_LEVEL_COUNTY.equals(level)){
                code = code.substring(0,6);
            }
        }
        area.setCode(code);
        return this.list(Wrappers.query(new Area()).like(
                StringUtils.isNotBlank(area.getCode()),Area::getCode,area.getCode()))
                .stream().sorted(Comparator.comparingInt(Area::getSort))
                .collect(Collectors.toList());
    }

    /**
     * 保存行政区划
     * @param area
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "area_details", allEntries = true)
    public R saveArea(Area area){
        area = initParentIds(area);
        if (StringUtils.isBlank(area.getParentId()) || StringUtils.isBlank(area.getParentIds())) {
            return R.builder()
                    .code(CommonConstants.FAIL)
                    .msg("父级ID为空").build();
        }
        area.setCreateBy(SecurityUtils.getUser().getId());
        area.setUpdateBy(SecurityUtils.getUser().getId());
        area.setCreateDate(LocalDateTime.now());
        area.setUpdateDate(LocalDateTime.now());
        return new R(this.save(area));
    }

    private Area initParentIds(Area area) {
        if (StringUtils.isBlank(area.getParentId()) || "0".equals(area.getParentId())) {
            area.setParentId("0");
            area.setParentIds("0,");
        } else {
            Area tempArea = this.getById(area.getParentId());
            if (null != tempArea) {
                area.setParentIds(tempArea.getParentIds() + tempArea.getId() + ",");
            }
        }
        return area;
    }

    /**
     * 更新行政区划
     *
     * @param area 行政区划信息
     * @return 成功、失败
     */
    @Override
    @CacheEvict(value = "area_details", allEntries = true)
    public R updateAreaById(Area area){
        Area tempArea = new Area();
        area = initParentIds(area);
        if (StringUtils.isBlank(area.getParentId()) || StringUtils.isBlank(area.getParentIds()) || null == tempArea) {
            return R.builder()
                    .code(CommonConstants.FAIL)
                    .msg("父级ID为空").build();
        }
        String[] parentIds = StringUtils.split(area.getParentIds(), ",");
        if (Arrays.asList(parentIds).contains(area.getId())) {
            return R.builder()
                    .code(CommonConstants.FAIL)
                    .msg("父级菜单不能为当前菜单或其子级").build();
        }
        area.setUpdateBy(SecurityUtils.getUser().getId());
        area.setUpdateDate(LocalDateTime.now());
        baseMapper.updateChildById(tempArea.getParentIds() + tempArea.getId() + ",", area.getParentIds() + area.getId() + ",");
        return new R(this.updateById(area));
    }

    /**
     * 级联删除行政区划
     *
     * @param id 区划ID
     * @return 成功、失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "area_details", allEntries = true)
    public R removeAreaById(String id){
        Area tempArea = this.getById(id);
        if (null == tempArea) {
            return R.builder()
                    .code(CommonConstants.FAIL)
                    .msg("无法找到该节点").build();
        }
        return new R(baseMapper.deleteChildById(id, tempArea.getParentIds() + id + ","));
    }

    @Override
    public List<Area> findListLevel(Area area) {
        return baseMapper.findListLevel(area);
    }
}
