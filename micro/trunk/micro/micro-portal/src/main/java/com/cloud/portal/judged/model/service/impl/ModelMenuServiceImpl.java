package com.cloud.portal.judged.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.core.util.R;
import com.cloud.common.security.service.MicroUser;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.common.constant.PortalConstants;
import com.cloud.portal.common.feign.SysMenuApiUtil;
import com.cloud.portal.judged.model.mapper.ModelMenuMapper;
import com.cloud.portal.judged.model.model.ModelMenu;
import com.cloud.portal.judged.model.service.ModelMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author ryt
 * @date Created in 2019/6/21 17:18
 * @description: 研判模型配置
 * @modified By:
 */
@Slf4j
@Service
@Component
public class ModelMenuServiceImpl extends ServiceImpl<ModelMenuMapper, ModelMenu> implements ModelMenuService {

    @Resource
    private RedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(getClass());



    /**
     * 获取所有场所菜单信息
     *
     * @param cacheKey 缓存key值
     * @return
     */
    @Override
    @Cacheable(value = "modelMenu_details", key = "#cacheKey")
    public List<ModelMenu> findAll(String cacheKey) {
        return this.baseMapper.queryAll(new ModelMenu())
                .stream()
                .sorted(Comparator.comparingInt(ModelMenu::getSort))
                .collect(Collectors.toList());
    }

    /**
     * 获取所有符合查询条件研判模型菜单信息
     *
     * @param modelMenu
     * @return
     */
    @Override
    public List<ModelMenu> queryAll(ModelMenu modelMenu) {
        return this.baseMapper.queryAll(modelMenu)
                .stream()
                .sorted(Comparator.comparingInt(ModelMenu::getSort))
                .collect(Collectors.toList());
    }

    /**
     * 保存模型配置
     * 规则：类型为模型的话，先去操作菜单表，新增记录，操作成功则在本模型配置表中插入记录，若失败则回滚事务；
     *       若本模型配置表插入数据时失败，则删除菜单表中的记录；
     *      （若类型为目录、标题则不操作菜单表；若类型为模型，该模型下不允许有子级）
     * @param modelMenu
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "modelMenu_details", allEntries = true)
    public R saveModel(ModelMenu modelMenu) {
        modelMenu = initParentId(modelMenu);
        if (StringUtils.isBlank(modelMenu.getParentId()) || StringUtils.isBlank(modelMenu.getParentIds())) {
            return R.builder()
                    .code(CommonConstants.FAIL)
                    .msg("父级ID为空").build();
        }
        ModelMenu tempModelMenu = this.getById(modelMenu.getParentId());
        if (null != tempModelMenu) {
            if (PortalConstants.MODEL_MENU_TYPE_MODEL.equals(tempModelMenu.getType())) {
                return R.builder()
                        .code(CommonConstants.FAIL)
                        .msg("由于父级菜单类型为模型，故当前菜单类型不能为模型.").build();
            }
            if (tempModelMenu.getType().equals(modelMenu.getType())) {
                if(PortalConstants.MODEL_MENU_TYPE_TITLE.equals(modelMenu.getType())){
                    return R.builder()
                            .code(CommonConstants.FAIL)
                            .msg("由于父级菜单类型为标题，故当前菜单类型不能为标题.").build();
                }

            }
        }
        MicroUser user = SecurityUtils.getUser();
        modelMenu.setCreateBy(user.getId());
        modelMenu.setUpdateBy(user.getId());
        modelMenu.setCreateTime(new Date());
        modelMenu.setUpdateTime(modelMenu.getCreateTime());
        modelMenu.setDelFlag(CommonConstants.STATUS_NORMAL);
        try {
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            byte[] bytes = (byte[]) redisTemplate.opsForHash().get(PortalConstants.MODEL_MENU_ICON,"model_menu_id");
            if (null!=bytes){
                modelMenu.setIcon(bytes);
            }else{
                modelMenu.setIcon(new byte[0]);
            }
        }catch (NullPointerException e){
            logger.error(e.getMessage());
        }

        /*判断模型类型，为模型则先插入菜单表，再操作本模型配置表，为目录、标题则不操作菜单表*/
        if (PortalConstants.MODEL_MENU_TYPE_MODEL.equals(modelMenu.getType())) {
            boolean isSuccess = SysMenuApiUtil.save(modelMenu);
            if (isSuccess != Boolean.FALSE) {
                boolean result = Boolean.FALSE;
                R<Boolean> data;
                data = new R(this.save(modelMenu));
                if (null != data && null != data.getData()) {
                    result = data.getData();
                }
                if (result == Boolean.FALSE) {
                    SysMenuApiUtil.remove(modelMenu.getId());
                    return R.builder()
                            .code(CommonConstants.FAIL)
                            .msg("保存菜单模型数据异常").build();
                } else {
                    delIcon();
                    return new R();
                }
            } else {
                return R.builder()
                        .code(CommonConstants.FAIL)
                        .msg("保存菜单模型数据异常").build();
            }
        }
        delIcon();
        return new R(this.save(modelMenu));
    }

    /**
     * 更新模型配置
     * 规则：类型为模型的话，先去更新菜单表，操作成功后则更新本模型配置表，若失败则回滚事务；
     * 若更新本模型配置表时失败，则还原菜单表中刚更新的记录
     *（若类型为目录、标题则不操作菜单表；若类型为模型，该模型下不允许有子级）
     * @param modelMenu
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "modelMenu_details", allEntries = true)
    public R updateModel(ModelMenu modelMenu) {
        ModelMenu tempModelMenu = this.getById(modelMenu.getId());
        modelMenu = initParentId(modelMenu);
        if (StringUtils.isBlank(modelMenu.getParentId()) || StringUtils.isBlank(modelMenu.getParentIds()) || null == tempModelMenu) {
            return R.builder()
                    .code(CommonConstants.FAIL)
                    .msg("父级ID为空.").build();
        }
        String[] parentIds = StringUtils.split(modelMenu.getParentIds(), ",");
        if (Arrays.asList(parentIds).contains(modelMenu.getId())) {
            return R.builder()
                    .code(CommonConstants.FAIL)
                    .msg("父级模型菜单不能为当前模型或其子级").build();
        }
        ModelMenu temp = this.getById(modelMenu.getParentId());
        if (null != temp) {
            if (PortalConstants.MODEL_MENU_TYPE_MODEL.equals(temp.getType())) {
                return R.builder()
                        .code(CommonConstants.FAIL)
                        .msg("由于父级菜单类型为模型，故当前菜单类型不能修改为模型").build();
            }
            if (temp.getType().equals(modelMenu.getType())) {
                if (PortalConstants.MODEL_MENU_TYPE_TITLE.equals(modelMenu.getType())) {
                    return R.builder()
                            .code(CommonConstants.FAIL)
                            .msg("由于父级菜单类型为标题，故当前菜单类型不能修改为标题").build();
                }
            }
        }

        MicroUser user = SecurityUtils.getUser();
        modelMenu.setUpdateBy(user.getId());
        modelMenu.setUpdateTime(new Date());
        try {
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            byte[] bytes = (byte[]) redisTemplate.opsForHash().get(PortalConstants.MODEL_MENU_ICON,"model_menu_id");
            if (null==bytes){
                modelMenu.setIcon(new byte[0]);
            }else{
                modelMenu.setIcon(bytes);
            }
        }catch (NullPointerException e){
            logger.error(e.getMessage());
        }

        /*判断修改前后类型是否一致，标题、目录类型不允许修改为模型*/
        if(tempModelMenu.getType().equals(modelMenu.getType())) {
            if (PortalConstants.MODEL_MENU_TYPE_MODEL.equals(modelMenu.getType())) {
                boolean isSuccess = SysMenuApiUtil.update(modelMenu);
                if (isSuccess != Boolean.FALSE) {
                    boolean result = Boolean.FALSE;
                    this.baseMapper.updateChild(modelMenu.getShowHide(),tempModelMenu.getParentIds() + tempModelMenu.getId() + ",", modelMenu.getParentIds() + modelMenu.getId() + ",");
                    R<Boolean> data = new R(this.updateById(modelMenu));
                    if (null != data && null != data.getData()) {
                        result = data.getData();
                    }
                    if (result == Boolean.FALSE) {
                        SysMenuApiUtil.update(tempModelMenu);
                        return R.builder()
                                .code(CommonConstants.FAIL)
                                .msg("更新菜单模型数据异常").build();
                    } else {
                        delIcon();
                        return new R();
                    }
                } else {
                    return R.builder()
                            .code(CommonConstants.FAIL)
                            .msg("更新菜单模型数据异常").build();
                }
            }
        }else{
            if(PortalConstants.MODEL_MENU_TYPE_MODEL.equals(modelMenu.getType())){
                return R.builder()
                        .code(CommonConstants.FAIL)
                        .msg("该菜单不能修改为模型").build();
            }

        }
        delIcon();
        this.baseMapper.updateChild(modelMenu.getShowHide(),tempModelMenu.getParentIds() + tempModelMenu.getId() + ",", modelMenu.getParentIds() + modelMenu.getId() + ",");
        return new R(this.updateById(modelMenu));
    }

    /**
     * 级联删除模型配置菜单
     *规则：类型为模型的话，先去删除菜单表中的记录，操作成功后则删除本模型配置表中的记录，若失败则回滚事务；
     *      若删除本模型配置表时失败，则还原菜单表中刚删除的记录
     *     （若类型为目录、标题则不操作菜单表）
     * @param id 模型菜单ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "modelMenu_details", allEntries = true)
    public R removeMenuById(String id) {
        // 查询父节点为当前节点的节点
        ModelMenu tempModelMenu = this.getById(id);
        if (null == tempModelMenu) {
            return R.builder()
                    .code(CommonConstants.FAIL)
                    .msg("无法找到该节点.").build();
        }
        /*查找该节点下是否有子节点，判断该节点及其子节点类型是否有为模型的，若存在则先去删除菜单表中的记录*/
        List<ModelMenu> modelMenuList=this.baseMapper.getModelId(tempModelMenu.getParentIds() + id + ",");
        if(PortalConstants.MODEL_MENU_TYPE_MODEL.equals(tempModelMenu.getType())||modelMenuList.size()>0){
            boolean isSuccess = false;
            String ids ="";
            if(PortalConstants.MODEL_MENU_TYPE_MODEL.equals(tempModelMenu.getType())){
                isSuccess = SysMenuApiUtil.remove(id);
            }else{
                    for (int i=0;i<modelMenuList.size();i++){
                        ids += modelMenuList.get(i).getId();
                        if(i<modelMenuList.size()-1){
                            ids +=',' ;
                        }
                    }
                    isSuccess = SysMenuApiUtil.remove(ids);
                }
            if (isSuccess != Boolean.FALSE) {
                boolean result = Boolean.FALSE;
                R<Boolean> data = new R(this.baseMapper.deleteChild(id, tempModelMenu.getParentIds() + id + ","));
                if (null != data && null != data.getData()) {
                    result = data.getData();
                }
                if (result == Boolean.FALSE) {
                    if(PortalConstants.MODEL_MENU_TYPE_MODEL.equals(tempModelMenu.getType())){
                        SysMenuApiUtil.rollback(id);
                    }else{
                        SysMenuApiUtil.rollback(ids);
                    }
                    return R.builder()
                            .code(CommonConstants.FAIL)
                            .msg("删除菜单模型数据异常").build();
                } else {
                    delIcon();
                    return new R();
                }
            }else{
                return R.builder()
                        .code(CommonConstants.FAIL)
                        .msg("删除菜单模型数据异常").build();
            }
        }
        delIcon();
       return new R(this.baseMapper.deleteChild(id, tempModelMenu.getParentIds() + id + ","));
    }

    /**
     * 初始化模型配置父id
     *
     * @param modelMenu
     * @return
     */
    private ModelMenu initParentId(ModelMenu modelMenu) {
        if (StringUtils.isBlank(modelMenu.getParentId()) || "-1".equals(modelMenu.getParentId())) {
            modelMenu.setParentId("-1");
            modelMenu.setParentIds("-1,");
        } else {
            ModelMenu tempModelMenu = this.getById(modelMenu.getParentId());
            if (null != tempModelMenu) {
                modelMenu.setParentIds(tempModelMenu.getParentIds() + tempModelMenu.getId() + ",");
                if (CommonConstants.MENU_HIDE.equals(tempModelMenu.getShowHide())) {
                    modelMenu.setShowHide(CommonConstants.MENU_HIDE);
                }
            }
        }
        if (StringUtils.isBlank(modelMenu.getId())) {
            modelMenu.setId(UUID.randomUUID().toString());
        }
        return modelMenu;
    }

    /**
     * 文件上传
     * @param multipartRequest 带有文件流的request
     * @return 上传结果
     * @throws IOException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
   public Map<String, Object> uploadIcon(MultipartHttpServletRequest multipartRequest)throws Exception{
        Map<String, Object> result = new HashMap<>(2);
        ModelMenu modelMenu = new ModelMenu();
        MultipartFile multipartFile = multipartRequest.getFile("file");
        boolean uploadFlag = true;
        try {
            modelMenu.setIcon(multipartFile.getBytes());
            cacheIcon(modelMenu);
        } catch (IOException e) {
            uploadFlag = false;
            logger.error("获取文件失败！" + e.getMessage());
        }
        result.put("modelMenu", modelMenu);
        result.put("uploadFlag", uploadFlag);
        return result;
    }

    /**
     * 根据id获取图片
     *
     * @param id 模型菜单ID
     * @return
     */
    @Override
    @Cacheable(value = "modelMenu_details", key = "'modelIcon_'+#id")
    public ModelMenu getIcon(String id){
        ModelMenu modelMenu=null;
        if(StringUtils.isNotBlank(id)){
            modelMenu = this.baseMapper.getIcon(id);
        }
        return modelMenu;
    }

    /**
     * 获取图片放进缓存
     *
     * @param
     * @return
     */
    @Override
    public void cacheIcon(ModelMenu modelMenu){
        delIcon();
        if (null != modelMenu && null != modelMenu.getIcon()) {
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.opsForHash().put(PortalConstants.MODEL_MENU_ICON, "model_menu_id", modelMenu.getIcon());
        }
    }

    /**
     * 删除研判模型图标
     *
     * @param
     * @return
     */
    @Override
    public void delIcon(){
        redisTemplate.opsForHash().delete(PortalConstants.MODEL_MENU_ICON, "model_menu_id");
    }

    /**
     * 分级获取模型
     *
     * @param modelMenu
     * @return
     */
    @Override
    @Cacheable(value = "modelMenu_details", key = "#roleId+'_'+#modelMenu.level+'_'+#modelMenu.id")
    public List<ModelMenu> getModelByLevel(ModelMenu modelMenu,Integer roleId){
        List<ModelMenu> modelMenuList = this.baseMapper.getModelByLevel(modelMenu);
        modelMenuList = SysMenuApiUtil.filterMenu(modelMenuList);
        return modelMenuList;
    }

    /**
     * 统计目录下的主题模型（app）数量
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = "modelMenu_details", key = "'count_'+#roleId+'_'+#id")
    public Integer countModel(Integer roleId,String id){
        ModelMenu modelMenu = new ModelMenu();
        modelMenu.setId(id);
        List<ModelMenu> modelMenuList = this.baseMapper.getAppList(modelMenu);
        modelMenuList=SysMenuApiUtil.filterMenu(modelMenuList);
        return modelMenuList.size();
    }

    /**
     * 获取所有模型（app）
     * @param
     * @param modelMenu 模型查询条件
     * @return
     */
    @Override
    @Cacheable(value = "modelMenu_details", key = "'app_'+#roleId")
    public List<ModelMenu> getApp(ModelMenu modelMenu,Integer roleId){
        List<ModelMenu> modelMenuList = this.baseMapper.getAppList(modelMenu);
        modelMenuList=SysMenuApiUtil.filterMenu(modelMenuList);
        return modelMenuList;
    }

}
