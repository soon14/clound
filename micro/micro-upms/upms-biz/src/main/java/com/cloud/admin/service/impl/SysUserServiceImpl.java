

package com.cloud.admin.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.dto.UserDTO;
import com.cloud.admin.api.dto.UserInfo;
import com.cloud.admin.api.entity.*;
import com.cloud.admin.api.vo.MenuVO;
import com.cloud.admin.api.vo.UserVO;
import com.cloud.admin.mapper.SysUserMapper;
import com.cloud.admin.service.*;
import com.cloud.admin.util.OptConstant;
import com.cloud.admin.util.OptUtil;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.core.util.R;
import com.cloud.common.data.datascope.DataScope;
import com.cloud.common.data.util.DataScopeUtil;
import com.cloud.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ygnet
 * @date 2017/10/31
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    private final SysMenuService sysMenuService;
    private final SysDeptService sysDeptService;
    private final SysUserRoleService sysUserRoleService;
    private final SysDeptRelationService sysDeptRelationService;
    private final SysRoleMenuScopeService sysRoleMenuScopeService;

    /**
     * 保存用户信息
     *
     * @param userDto DTO 对象
     * @return success/fail
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveUser(UserDTO userDto) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto, sysUser);
        sysUser.setDelFlag(CommonConstants.STATUS_NORMAL);
        if(StringUtils.isBlank(userDto.getPassword())){
            userDto.setPassword(CommonConstants.USER_PASSWORD);
        }
        sysUser.setPassword(ENCODER.encode(userDto.getPassword()));
        baseMapper.insert(sysUser);

        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(sysUser.getUserId());
        userRole.setRoleId(userDto.getRole());
        return sysUserRoleService.save(userRole);
    }

    @Override
    public UserVO getUserVoDetailByUserName(String userName) {
        return baseMapper.getUserVoDetailByUserName(userName);
    }

    /**
     * 通过查用户的全部信息
     *
     * @param sysUser 用户
     * @return
     */
    @Override
    public UserInfo findUserInfo(SysUser sysUser) {
        UserInfo userInfo = new UserInfo();
        userInfo.setSysUser(sysUser);
        //设置角色列表  （ID）
        UserVO userVO = this.getUserVoDetailByUserName(sysUser.getUsername());
        if (null != userVO) {
            userInfo.setSysDept(userVO.getSysDept());
            if (null != userVO.getSysRole()) {
                SysRole sysRole = userVO.getSysRole();
                if (null != userInfo.getSysDept()) {
                    sysRole.setCodeLevel(OptUtil.getDeptLevel(userVO.getSysDept().getCode()));
                } else {
                    sysRole.setCodeLevel(OptConstant.DEPT_LEVEL_DEFAULT);
                }
                sysRole.setRoleMenuScopeList(sysRoleMenuScopeService.list(Wrappers.<SysRoleMenuScope>query().lambda().eq(SysRoleMenuScope::getRoleId, userVO.getSysRole().getRoleId())));
                userInfo.setSysRole(sysRole);
                userInfo.setRoles(userVO.getSysRole().getRoleId());
                userInfo.setSysRole(userVO.getSysRole());
                List<String> permissionList;
                //超级管理员获取所有菜单权限
                if(CommonConstants.ADMIN_FLAG.equals(sysUser.getUserId())){
                     permissionList = sysMenuService.findAll(CommonConstants.ALL_MENU_CACHE_KEY)
                            .stream()
                            .filter(menuVo -> StringUtils.isNotEmpty(menuVo.getPermission()))
                            .map(SysMenu::getPermission)
                            .collect(Collectors.toList());
                }else{
                    permissionList = sysMenuService.findMenuByRoleId(userVO.getSysRole().getRoleId())
                            .stream()
                            .filter(menuVo -> StringUtils.isNotEmpty(menuVo.getPermission()))
                            .map(MenuVO::getPermission)
                            .collect(Collectors.toList());
                }

                userInfo.setPermissions(ArrayUtil.toArray((permissionList), String.class));
            }
        }

        return userInfo;
    }

    /**
     * 分页查询用户信息（含有角色信息）
     *
     * @param page    分页对象
     * @param userDTO 参数列表
     * @return
     */
    @Override
    public IPage getUsersWithRolePage(Page page, UserDTO userDTO) {
        userDTO.setDataScope(DataScopeUtil.getDataScopeSql(new DataScope("d.code", "sys_user_list")));
        return baseMapper.getUserVosPage(page, userDTO);
    }

    /**
     * 通过ID查询用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @Override
    public UserVO selectUserVoById(String id) {
        return baseMapper.getUserVoById(id);
    }

    /**
     * 删除用户
     *
     * @param sysUser 用户
     * @return Boolean
     */
    @Override
    @CacheEvict(value = "user_details", key = "#sysUser.username")
    public Boolean deleteUserById(SysUser sysUser) {
        sysUserRoleService.deleteByUserId(sysUser.getUserId());
        this.removeById(sysUser.getUserId());
        return Boolean.TRUE;
    }

    @Override
    @CacheEvict(value = "user_details", key = "#userDto.username")
    public R<Boolean> updateUserInfo(UserDTO userDto) {
        UserVO userVO = baseMapper.getUserVoByUsername(userDto.getUsername());
        SysUser sysUser = new SysUser();
        if (StrUtil.isNotBlank(userDto.getPassword())
                && StrUtil.isNotBlank(userDto.getNewpassword1())) {
            if (ENCODER.matches(userDto.getPassword(), userVO.getPassword())) {
                sysUser.setPassword(ENCODER.encode(userDto.getNewpassword1()));
            } else {
                log.warn("原密码错误，修改密码失败:{}", userDto.getUsername());
                return new R<>(Boolean.FALSE, "原密码错误，修改失败");
            }
        }
        sysUser.setPhone(userDto.getPhone());
        sysUser.setUserId(userVO.getUserId());
        sysUser.setAvatar(userDto.getAvatar());
        return new R<>(this.updateById(sysUser));
    }

    @Override
    @CacheEvict(value = "user_details", key = "#userDto.username")
    public Boolean updateUser(UserDTO userDto) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto, sysUser);
        sysUser.setUpdateTime(LocalDateTime.now());

        if (StrUtil.isNotBlank(userDto.getPassword())) {
            sysUser.setPassword(ENCODER.encode(userDto.getPassword()));
        }
        this.updateById(sysUser);
        sysUserRoleService.remove(Wrappers.<SysUserRole>update().lambda()
                .eq(SysUserRole::getUserId, userDto.getUserId()));
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(sysUser.getUserId());
        userRole.setRoleId(userDto.getRole());
        userRole.insert();
        return Boolean.TRUE;
    }

    /**
     * 查询上级部门的用户信息
     *
     * @param username 用户名
     * @return R
     */
    @Override
    public List<SysUser> listAncestorUsers(String username) {
        SysUser sysUser = this.getOne(Wrappers.<SysUser>query().lambda()
                .eq(SysUser::getUsername, username));

        SysDept sysDept = sysDeptService.getById(sysUser.getDeptId());
        if (sysDept == null) {
            return null;
        }

        String parentId = sysDept.getParentId();
        return this.list(Wrappers.<SysUser>query().lambda()
                .eq(SysUser::getDeptId, parentId));
    }

    /**
     * 获取当前用户的子部门信息
     *
     * @return 子部门列表
     */
    private List<String> getChildDepts() {
        String deptId = SecurityUtils.getUser().getDeptId();
        //获取当前部门的子部门
        return sysDeptRelationService
                .list(Wrappers.<SysDeptRelation>query().lambda()
                        .eq(SysDeptRelation::getAncestor, deptId))
                .stream()
                .map(SysDeptRelation::getDescendant)
                .collect(Collectors.toList());
    }

}
