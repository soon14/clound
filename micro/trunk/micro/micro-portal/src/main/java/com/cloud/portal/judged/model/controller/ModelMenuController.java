package com.cloud.portal.judged.model.controller;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.api.vo.TreeUtil;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.common.security.service.MicroUser;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.common.constant.PortalConstants;
import com.cloud.portal.judged.model.model.ModelMenu;
import com.cloud.portal.judged.model.model.ModelMenuTree;
import com.cloud.portal.judged.model.service.ModelMenuService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author ryt
 * @date Created in 2019/6/21 17:18
 * @description: 研判模型配置
 * @modified By:
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/judged/model")
@Api(value = "model", description = "研判模型配置")
public class ModelMenuController {

    /**redis 缓存信息*/
    private final CacheManager cacheManager;
    private final ModelMenuService modelMenuService;


    /**
     * 返回研判模型配置树形菜单集合
     *
     * @return 树形菜单
     */
    @GetMapping(value = "/tree")
    @SysLog("研判模型配置菜单查询")
    public R getTree(ModelMenu modelMenu) {
        List<ModelMenuTree> menuTreeList;
        List<ModelMenu> modelMenuList;
        if (StringUtils.isBlank(modelMenu.getName()) && StringUtils.isBlank(modelMenu.getType())) {
            modelMenuList = modelMenuService.findAll(PortalConstants.ALL_MODEL_MENU_CACHE_KEY);
        } else {
            modelMenuList = modelMenuService.queryAll(modelMenu);
        }menuTreeList = initTree(modelMenuList);

        return new R<>(TreeUtil.bulid(menuTreeList, "-1"));
    }

    /**
     * 通过ID查询菜单的详细信息
     *
     * @param id 菜单ID
     * @return 菜单详细信息
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable String id) {
        return new R<>(modelMenuService.getById(id));
    }

    /**
     * 新增模型
     *
     * @param modelMenu 模型配置信息
     * @return
     */
    @SysLog("新增模型")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('model_menu_add')")
    public R save(@Valid @RequestBody ModelMenu modelMenu) {
        return modelMenuService.saveModel(modelMenu);
    }

    /**
     * 更新研判模型配置
     *
     * @param modelMenu
     * @return
     */
    @SysLog("更新研判模型配置")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('model_menu_edit')")
    public R update(@Valid @RequestBody ModelMenu modelMenu) {
        return modelMenuService.updateModel(modelMenu);
    }

    /**
     * 删除研判模型
     *
     * @param id 模型ID
     * @return
     */
    @SysLog("根据id删除研判模型")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('model_menu_del')")
    public R removeById(@PathVariable String id) {
        return modelMenuService.removeMenuById(id);
    }

    /**
     * 初始化树形菜单
     *
     * @param modelMenuList
     * @return
     */
    private List<ModelMenuTree> initTree(List<ModelMenu> modelMenuList) {
        return modelMenuList.stream().map(modelMenu -> {
            ModelMenuTree treeNode = new ModelMenuTree();
            treeNode.setId(modelMenu.getId());
            treeNode.setName(modelMenu.getName());
            treeNode.setParentId(modelMenu.getParentId());
            treeNode.setParentIds(modelMenu.getParentIds());
            treeNode.setType(modelMenu.getType());
            treeNode.setLinks(modelMenu.getLinks());
            treeNode.setOpenType(modelMenu.getOpenType());
            treeNode.setPermission(modelMenu.getPermission());
            treeNode.setPlanLibId(modelMenu.getPlanLibId());
            treeNode.setShowHide(modelMenu.getShowHide());
            treeNode.setUseBest(modelMenu.getUseBest());
            treeNode.setSort(modelMenu.getSort());
            treeNode.setStars(modelMenu.getStars());
            treeNode.setUdId(modelMenu.getUdId());
            treeNode.setRemark(modelMenu.getRemark());
            treeNode.setCreateDate(modelMenu.getCreateDate());
            treeNode.setState(modelMenu.getState());
            return treeNode;
        }).collect(Collectors.toList());
    }

    /**
     * 校验唯一标识符是否存在
     *
     * @param udId
     * @return
     */
    @GetMapping("/checkUdId/{udId}")
    public R checkUdId(@PathVariable String udId) {
        ModelMenu condition = new ModelMenu();
        condition.setUdId(udId);
        return new R<>(modelMenuService.getOne(new QueryWrapper<>(condition)));
    }

    /**
     * 上传文件
     *
     * @param multipartRequest 资源
     * @return
     */
    @PostMapping("/upload")
    public R upload(MultipartHttpServletRequest multipartRequest) {
        Map<String, Object> result;
        try {
            result = modelMenuService.uploadIcon(multipartRequest);
            return R.builder().data(result).build();
        } catch (Exception e) {
            e.printStackTrace();
            return R.builder().data(e.getMessage()).build();
        }
    }

    /**
     * 根据id获取图片
     *
     * @param id   模型菜单ID
     * @param flag 标识来自展示页面还是配置页面
     * @return
     */
    @GetMapping("/icon/{id}/{flag}")
    public void getIcon(@PathVariable String id, @PathVariable String flag, HttpServletResponse response) {
        try {
            InputStream in = null;
            ModelMenu modelMenu;
            Cache cache = cacheManager.getCache("modelMenu_details");
            Object object = null != cache ? cache.get("modelIcon_" + id) : null;
            if (null != object) {
                modelMenu = (ModelMenu) ((SimpleValueWrapper) object).get();
            } else {
                modelMenu = modelMenuService.getIcon(id);
            }
            if (null != modelMenu && null != modelMenu.getIcon()) {
                String operate = "edit";
                if(operate.equals(flag)){
                    modelMenuService.cacheIcon(modelMenu);
                }
                in = new ByteArrayInputStream(modelMenu.getIcon());
            }
            response.setContentType("application/octet-stream; charset=UTF-8");
            IoUtil.copy(in, response.getOutputStream());
        } catch (Exception e) {
        }
    }

    /**
     * 删除研判模型图标
     *
     * @param
     * @return
     */
    @DeleteMapping("/delIcon")
    public void delIcon() {
        modelMenuService.delIcon();
    }

    /**
     * 返回研判模型配置树形菜单集合
     *
     * @return 树形菜单
     */
    @GetMapping(value = "/getModelByLevel")
    @SysLog("分级获取研判模型页面展示")
    public R getModelByLevel(ModelMenu modelMenu) {
        MicroUser user = SecurityUtils.getUser();
        List<ModelMenuTree> menuTreeList = new ArrayList<>();
        List<ModelMenu> modelMenuList = null;
        Integer roleId = (null != user.getSysRole() && null != user.getSysRole().getRoleId()) ? user.getSysRole().getRoleId() : null;
        if (null != roleId) {
            Cache cache = cacheManager.getCache("modelMenu_details");
            Object object = null != cache ? cache.get(roleId + "_" + modelMenu.getLevel() + "_" + modelMenu.getId()) : null;
            if (null != object) {
                modelMenuList = (List<ModelMenu>) ((SimpleValueWrapper) object).get();
            } else {
                modelMenuList = modelMenuService.getModelByLevel(modelMenu, roleId);
            }
        }
        if(null!=modelMenuList){
            menuTreeList = initTree(modelMenuList);
            return new R<>(TreeUtil.bulid(menuTreeList, "-1"));
        }else{
            return new R<>(menuTreeList);
        }

    }

    /**
     * 统计目录下的主题模型（app）数量
     *
     * @param id
     * @return
     */
    @GetMapping("/count/{id}")
    public Integer countModel(@PathVariable String id) {
        MicroUser user = SecurityUtils.getUser();
        int count = 0;
        Integer roleId = (null != user.getSysRole() && null != user.getSysRole().getRoleId()) ? user.getSysRole().getRoleId() : null;
        if (null != roleId) {
            Cache cache = cacheManager.getCache("modelMenu_details");
            Object object = null != cache ? cache.get("count_" + roleId + "_" + id) : null;
            if (null != object) {
                count = (Integer) ((SimpleValueWrapper) object).get();
            } else {
                count = modelMenuService.countModel(roleId, id);
            }
        }
        return count;
    }

    /**
     * 获取所有模型（app）
     *
     * @param page,modelMenu
     * @return
     */
    @GetMapping(value = "/app")
    @SysLog("获取所有研判模型（App）")
    public R index(Page page, ModelMenu modelMenu) {
        List<ModelMenu> modelMenuList;
        List<ModelMenu> tempModelMenuList;
        MicroUser user = SecurityUtils.getUser();
        Integer roleId = (null != user.getSysRole() && null != user.getSysRole().getRoleId()) ? user.getSysRole().getRoleId() : null;
        if (null != roleId) {
            Cache cache = cacheManager.getCache("modelMenu_details");
            Object object = null != cache ? cache.get("app_" + roleId) : null;
            if (null != object) {
                modelMenuList = (List<ModelMenu>) ((SimpleValueWrapper) object).get();
            } else {
                ModelMenu temp = new ModelMenu();
                modelMenuList = modelMenuService.getApp(temp, roleId);
            }
            List<ModelMenu> menuList = modelMenuList;
            if(StringUtils.isNotBlank(modelMenu.getName())){
                tempModelMenuList = menuList.stream().filter(modelMenu1 ->
                    modelMenu1.getName().contains(modelMenu.getName())
                ).collect(Collectors.toList());
            }else{
                tempModelMenuList = menuList.stream().collect(Collectors.toList());
            }
            //总数
            int count = tempModelMenuList.size();
            //每页开始数
            int start=(int)(page.getCurrent() - 1) * (int)page.getSize();
            page.setRecords(tempModelMenuList.subList(start,count-start>(int)page.getSize()?start+(int)page.getSize():count));
            page.setTotal(Long.valueOf(count));
        }
        return R.ok(page);
    }
}
