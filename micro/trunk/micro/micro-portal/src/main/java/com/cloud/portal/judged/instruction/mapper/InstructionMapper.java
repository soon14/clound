package com.cloud.portal.judged.instruction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.judged.instruction.model.Instruction;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author Molly
 * @date Created in 2019/6/24
 * @description: 智能预警-指令mapper接口
 * @modified By: Molly
 */
public interface InstructionMapper extends BaseMapper<Instruction> {

    IPage<List<Instruction>> findListPage(IPage<Instruction> page,@Param("instruction") Instruction instruction);

    IPage<List<Instruction>> findOvertimeListPage(IPage<Instruction> page,@Param("instruction") Instruction instruction);

    int updateStatus(@Param("instruction") Instruction instruction);

}
