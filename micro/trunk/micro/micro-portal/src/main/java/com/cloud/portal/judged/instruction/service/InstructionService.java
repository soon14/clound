package com.cloud.portal.judged.instruction.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.judged.instruction.model.Instruction;

import java.util.List;


/**
 * @author Molly
 * @date Created in 2019/6/24
 * @description: 智能预警-指令service接口
 * @modified By: Molly
 * */
public interface InstructionService extends IService<Instruction> {

    /**
     * 查询列表（分页）
     * @param page
     * @param instruction
     * @return
     */
    public IPage<List<Instruction>> page(IPage<Instruction> page, Instruction instruction);

    /**
     * 查询超时反馈指令（分页）
     * @param page
     * @param instruction
     * @return
     */
    public IPage<List<Instruction>> findOvertimeListPage(IPage<Instruction> page,Instruction instruction);

    /**
     * 查询当前用户发布的指令列表（分页）
     * @param page
     * @param instruction
     * @return
     */
    public IPage<List<Instruction>> findCurrentUserSendListPage(IPage<Instruction> page,Instruction instruction);

    /**
     * 根据id更新指令状态
     * @param instruction
     * @return
     */
    public boolean updateStatus(Instruction instruction);


}
