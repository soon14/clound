package com.cloud.portal.common.feign;

import com.cloud.admin.api.entity.SysMenu;
import com.cloud.admin.api.feign.RemoteMenuService;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.core.util.R;
import com.cloud.common.core.util.SpringContextHolder;
import com.cloud.common.security.service.MicroUser;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.common.constant.PortalConstants;
import com.cloud.portal.judged.model.model.ModelMenu;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wengshij
 * @date Created in 2019/6/22 11:15
 * @description: 系统菜单API接口调用工具类
 * @modified By:wengshij
 */
@Slf4j
public class SysMenuApiUtil {

    private static RemoteMenuService remoteMenuService = SpringContextHolder.getBean(RemoteMenuService.class);
    private static CacheManager cacheManager = SpringContextHolder.getBean(CacheManager.class);

    /**
     * @param modelMenu
     * @return
     */
    public static boolean save(ModelMenu modelMenu) {
        boolean result = Boolean.FALSE;
        try {
            R<Boolean> data = remoteMenuService.modelSave(initMenu(modelMenu));
            if (null != data && null != data.getData()) {
                result = data.getData();
            }
        } catch (Exception e) {
            log.error("保存菜单模型数据异常,ex={}", e.getMessage(), e);
        }
        return result;
    }

    public static boolean update(ModelMenu modelMenu) {
        boolean result = Boolean.FALSE;
        try {
            R<Boolean> data = remoteMenuService.modelUpdate(initMenu(modelMenu));
            if (null != data && null != data.getData()) {
                result = data.getData();
            }
        } catch (Exception e) {
            log.error("更新菜单模型数据异常,ex={}", e.getMessage(), e);
        }
        return result;

    }

    /**
     * 删除模型数据
     *
     * @param ids 主键ID 如果是多个 可逗号隔开 如：id1,id2,id3
     * @return
     */
    public static boolean remove(String ids) {
        boolean result = Boolean.FALSE;
        try {
            R<Boolean> data = remoteMenuService.modelRemove(ids);
            if (null != data && null != data.getData()) {
                result = data.getData();
            }
        } catch (Exception e) {
            log.error("更新菜单模型数据异常,ex={}", e.getMessage(), e);
        }
        return result;

    }

    /**
     * 恢复模型数据
     *
     * @param ids 主键ID 如果是多个 可逗号隔开 如：id1,id2,id3
     * @return
     */
    public static boolean rollback(String ids) {
        boolean result = Boolean.FALSE;
        try {
            R<Boolean> data = remoteMenuService.modelRollback(ids);
            if (null != data && null != data.getData()) {
                result = data.getData();
            }
        } catch (Exception e) {
            log.error("更新菜单模型数据异常,ex={}", e.getMessage(), e);
        }
        return result;

    }

    /**
     * 权限过滤菜单
     * @param modelMenuList
     * @return
     */
    public static List<ModelMenu> filterMenu(List<ModelMenu> modelMenuList) {
        MicroUser user = SecurityUtils.getUser();
        List<ModelMenu> tempModelMenuList;
        if (user.isAdmin()) {
            return modelMenuList;
        } else {
            List<SysMenu> sysMenuList = null;
            Integer roleId = (null != user.getSysRole() && null != user.getSysRole().getRoleId()) ? user.getSysRole().getRoleId() : null;
            if (null != roleId) {
                Cache cache = cacheManager.getCache("menu_details");
                Object object = null != cache ? cache.get("model_" + roleId) : null;
                if (null != object) {
                    sysMenuList =(List<SysMenu>) ((SimpleValueWrapper) object).get();
                } else {
                    try {
                        R<List<SysMenu>> data = remoteMenuService.findModelMenuList(roleId);
                        if (null != data && null != data.getData()) {
                            sysMenuList = data.getData();
                        }
                    } catch (Exception e) {
                        log.error("更新菜单模型数据异常,ex={}", e.getMessage(), e);
                    }
                }

            }
            List<SysMenu> finalSysMenuList = sysMenuList;
            tempModelMenuList = modelMenuList.stream().filter(modelMenu -> {
                        if (PortalConstants.MODEL_MENU_TYPE_MODEL.equals(modelMenu.getType())) {
                            if (null == finalSysMenuList) {
                                return Boolean.FALSE;
                            } else {
                                for (SysMenu sysMenu : finalSysMenuList) {
                                    if (StringUtils.isNotBlank(sysMenu.getMenuId()) &&
                                            StringUtils.isNotBlank(modelMenu.getId()) &&
                                            sysMenu.getMenuId().equals(modelMenu.getId())) {
                                        return Boolean.TRUE;
                                    }
                                }
                                return Boolean.FALSE;
                            }
                        }
                        return Boolean.TRUE;
                    }
            ).collect(Collectors.toList());
        }


        return tempModelMenuList;
    }

    private static SysMenu initMenu(ModelMenu modelMenu) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setMenuId(modelMenu.getId());
        sysMenu.setName(modelMenu.getName());
        sysMenu.setPermission(modelMenu.getPermission());
        sysMenu.setSort(modelMenu.getSort());
        sysMenu.setPath(modelMenu.getLinks());
        sysMenu.setParentId(CommonConstants.SYS_MENU_MODEL_ID);
        sysMenu.setType(CommonConstants.MENU);
        return sysMenu;
    }


}
