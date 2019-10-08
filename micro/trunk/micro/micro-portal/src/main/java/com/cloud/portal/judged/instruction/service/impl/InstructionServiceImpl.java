package com.cloud.portal.judged.instruction.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.entity.SysUser;
import com.cloud.common.data.datascope.DataScope;
import com.cloud.common.data.util.DataScopeUtil;
import com.cloud.common.security.service.MicroUser;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.judged.instruction.mapper.InstructionMapper;
import com.cloud.portal.judged.instruction.model.Instruction;
import com.cloud.portal.judged.instruction.service.InstructionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Molly
 * @date Created in 2019/6/24
 * @description: 智能预警-指令service实现类
 * @modified By: Molly
 */
@Service
public class InstructionServiceImpl extends ServiceImpl<InstructionMapper, Instruction> implements InstructionService {

    /**
     * 查询列表（分页）
     * @param page
     * @return
     */
    @Override
    public IPage<List<Instruction>> page(IPage<Instruction> page,Instruction instruction) {
        instruction.setDataScope(DataScopeUtil.getDataScopeSql(new DataScope("a.receive_org", "thcs_instruction_center")));
        if(StringUtils.isNotBlank(instruction.getReceiveOrg())){
            instruction.setReceiveOrg(instruction.getReceiveOrg().replaceAll("0*$", ""));
        }
        if(StringUtils.isNotBlank(instruction.getCityCode())){
            instruction.setCityCode(instruction.getCityCode().replaceAll("0*$", ""));
        }
        return this.baseMapper.findListPage(page,instruction);
    }

    /**
     * 查询当前用户发布的指令列表（分页）
     * @param page
     * @return
     */
    @Override
    public IPage<List<Instruction>> findCurrentUserSendListPage(IPage<Instruction> page,Instruction instruction){
        instruction.setCreateBy(SecurityUtils.getUser().getId());
        instruction.setReceiveOrg(instruction.getReceiveOrg().replaceAll("0*$", ""));
        instruction.setCityCode(instruction.getCityCode().replaceAll("0*$", ""));
        return page(page,instruction);
    }

    /**
     * 查询当前部门及以下的超时反馈指令（分页）
     * @param page
     * @param instruction
     * @return
     */
    @Override
    public IPage<List<Instruction>> findOvertimeListPage(IPage<Instruction> page, Instruction instruction) {
        instruction.setDataScope(DataScopeUtil.getDataScopeSql(new DataScope("a.receive_org", "thcs_instruction_overtime")));
        instruction.setReceiveOrg(SecurityUtils.getUser().getSysDept().getCode().replaceAll("0*$", ""));
        instruction.setCityCode(instruction.getCityCode().replaceAll("0*$", ""));
        return this.baseMapper.findOvertimeListPage(page,instruction);
    }

    /**
     * 保存指令(草稿)
     * @param entity
     * @return
     */
    @Override
    public boolean save(Instruction entity) {
        MicroUser user = SecurityUtils.getUser();
        SysUser sysUser = user.getSysUser();
        entity.setCreateBy(user.getId());
        entity.setCreateByName(sysUser.getName());
        entity.setUpdaeBy(user.getId());
        entity.setUpdateByName(sysUser.getName());
        entity.setUpdateTime(LocalDateTime.now());
        if(StringUtils.isNotBlank(entity.getReceiveOrg())){
            entity.setCityCode(entity.getReceiveOrg().substring(0,6));
        }
        return super.save(entity);
    }

    /**
     * 更新指令
     * @param entity
     * @return
     */
    @Override
    public boolean updateById(Instruction entity) {
        MicroUser user = SecurityUtils.getUser();
        SysUser sysUser = user.getSysUser();
        entity.setUpdaeBy(user.getId());
        entity.setUpdateByName(sysUser.getName());
        entity.setUpdateTime(LocalDateTime.now());
        if(StringUtils.isNotBlank(entity.getReceiveOrg())){
            entity.setCityCode(entity.getReceiveOrg().substring(0,6));
        }
        return super.updateById(entity);
    }

    /**
     * 更新指令
     * @param instruction
     * @return
     */
    @Override
    public boolean updateStatus(Instruction instruction) {
        instruction.setUpdaeBy(SecurityUtils.getUser().getId());
        instruction.setUpdateTime(LocalDateTime.now());
        return this.retBool(this.baseMapper.updateStatus(instruction));
    }
}
