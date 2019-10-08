package com.cloud.admin.api.feign;

import com.cloud.admin.api.entity.SysMenu;
import com.cloud.common.core.constant.ServiceNameConstants;
import com.cloud.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wengshij
 * @date Created in 2019/6/22 10:51
 * @description: 菜单远程调用微服务接口
 * @modified By:wengshij
 */
@FeignClient(value = ServiceNameConstants.UMPS_SERVICE)
public interface RemoteMenuService {

    /**
     * 新增菜单信息
     *
     * @param sysMenu 菜单实体类型
     * @return
     */
    @PostMapping("/menu/model/save")
    R<Boolean> modelSave(@RequestBody SysMenu sysMenu);

    /**
     * 更新菜单信息
     *
     * @param sysMenu 菜单实体类型（需包含菜单主键ID）
     * @return
     */
    @PutMapping("/menu/model/update")
    R<Boolean> modelUpdate(@RequestBody SysMenu sysMenu);

    /**
     * 删除菜单信息
     *
     * @param ids 菜单主键ID
     * @return
     */
    @DeleteMapping("/menu/model/remove")
    R<Boolean> modelRemove(@RequestParam("ids") String ids);

    /**
     * 还原已删除的菜单信息
     *
     * @param ids 菜单主键ID
     * @return
     */
    @PutMapping("/menu/model/rollback")
    R<Boolean> modelRollback(@RequestParam("ids") String ids);

    /**
     * 根据角色ID获取该角色下所有的研判模型
     * @param roleId
     * @return
     */
    @GetMapping("/menu/model/list/{roleId}")
    R<List<SysMenu>> findModelMenuList(@PathVariable("roleId") Integer roleId);
}
