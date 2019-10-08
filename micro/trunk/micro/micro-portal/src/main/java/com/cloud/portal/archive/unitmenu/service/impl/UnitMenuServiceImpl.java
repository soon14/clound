package com.cloud.portal.archive.unitmenu.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.core.util.R;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.archive.unitmenu.entity.UnitMenu;
import com.cloud.portal.archive.unitmenu.mapper.UnitMenuMapper;
import com.cloud.portal.archive.unitmenu.service.UnitMenuService;
import lombok.AllArgsConstructor;
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
 * @date Created in 2019/5/7 11:21
 * @description: 场所分类显示信息
 * @modified By:
 */
@Service
@AllArgsConstructor
public class UnitMenuServiceImpl extends ServiceImpl<UnitMenuMapper, UnitMenu> implements UnitMenuService {

    /**
     * 获取所有场所菜单信息
     *
     *@param cacheKey 缓存key值
     * @return
     */
    @Override
    @Cacheable(value = "unitMenu_details", key = "#cacheKey" )
    public List<UnitMenu> findAll(String cacheKey) {
        return this.list(Wrappers.emptyWrapper())
                    .stream()
                .sorted(Comparator.comparingInt(UnitMenu::getSort))
                .collect(Collectors.toList());
    }

    /**
     * 保存场所菜单信息
     *
     *@param unitMenu
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "unitMenu_details", allEntries = true)
    public R saveMenu(UnitMenu unitMenu) {
        unitMenu = initParentIds(unitMenu);
        if (StringUtils.isBlank(unitMenu.getParentId()) || StringUtils.isBlank(unitMenu.getParentIds())) {
            return R.builder()
                    .code(CommonConstants.FAIL)
                    .msg("父级ID为空").build();
        }
        unitMenu.setCreateBy(SecurityUtils.getUser().getId());
        unitMenu.setUpdateBy(SecurityUtils.getUser().getId());
        unitMenu.setCreateTime(LocalDateTime.now());
        unitMenu.setUpdateTime(LocalDateTime.now());
        return new R(this.save(unitMenu));
    }

    private UnitMenu initParentIds(UnitMenu unitMenu) {
        if (StringUtils.isBlank(unitMenu.getParentId()) || "-1".equals(unitMenu.getParentId())) {
            unitMenu.setParentId("-1");
            unitMenu.setParentIds("-1,");
        } else {
            UnitMenu tempSysMenu = this.getById(unitMenu.getParentId());
            if (null != tempSysMenu) {
                unitMenu.setParentIds(tempSysMenu.getParentIds() + tempSysMenu.getId() + ",");
            }
        }
        return unitMenu;
    }

    /**
     * 更新场所菜单信息
     *
     *@param unitMenu
     * @return
     */
    @Override
    @CacheEvict(value = "unitMenu_details", allEntries = true)
    public R updateMenuById(UnitMenu unitMenu) {
        UnitMenu tempUnitMenu = this.getById(unitMenu.getId());
        unitMenu = initParentIds(unitMenu);
        if (StringUtils.isBlank(unitMenu.getParentId()) || StringUtils.isBlank(unitMenu.getParentIds()) || null == tempUnitMenu) {
            return R.builder()
                    .code(CommonConstants.FAIL)
                    .msg("父级ID为空").build();
        }
        String[] parentIds = StringUtils.split(unitMenu.getParentIds(), ",");
        if (Arrays.asList(parentIds).contains(unitMenu.getId())) {
            return R.builder()
                    .code(CommonConstants.FAIL)
                    .msg("父级菜单不能为当前菜单或其子级").build();
        }
        unitMenu.setUpdateBy(SecurityUtils.getUser().getId());
        unitMenu.setUpdateTime(LocalDateTime.now());
        baseMapper.updateChild(tempUnitMenu.getParentIds() + tempUnitMenu.getId() + ",", unitMenu.getParentIds() + unitMenu.getId() + ",");
        return new R(this.updateById(unitMenu));
    }

    /**
     * 级联删除菜单
     *
     * @param id 场所菜单ID
     * @return 成功、失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "unitMenu_details", allEntries = true)
    public R removeMenuById(String id) {
        // 查询父节点为当前节点的节点
        UnitMenu tempUnitMenu = this.getById(id);
        if (null == tempUnitMenu) {
            return R.builder()
                    .code(CommonConstants.FAIL)
                    .msg("无法找到该节点").build();
        }
        return new R(baseMapper.deleteChild(id, tempUnitMenu.getParentIds() + id + ","));
    }
}
