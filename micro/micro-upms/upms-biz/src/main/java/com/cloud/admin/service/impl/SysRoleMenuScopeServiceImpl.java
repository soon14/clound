
package com.cloud.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.entity.SysRoleMenuScope;

import com.cloud.admin.mapper.SysRoleMenuScopeMapper;
import com.cloud.admin.service.SysRoleMenuScopeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自定义角色某类菜单权限
 *
 * @author wengshij
 * @date 2019-02-25 10:35:00
 */
@Service
public class SysRoleMenuScopeServiceImpl extends ServiceImpl<SysRoleMenuScopeMapper, SysRoleMenuScope> implements SysRoleMenuScopeService {


    @Override
    public List<SysRoleMenuScope> findRoleMenuScopeByRoleId(Integer roleId) {
        return baseMapper.findRoleMenuScopeByRoleId(roleId);
    }
}
