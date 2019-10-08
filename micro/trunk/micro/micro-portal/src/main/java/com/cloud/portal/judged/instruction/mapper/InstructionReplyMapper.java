package com.cloud.portal.judged.instruction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.portal.judged.instruction.model.InstructionReply;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * @author Molly
 * @date Created in 2019/6/24
 * @description: 智能预警-反馈mapper接口
 * @modified By: Molly
 */
public interface InstructionReplyMapper extends BaseMapper<InstructionReply> {

    /**
     * 根据指令获取反馈列表
     * @param instructionId 指令id
     * @return List<InstructionReply>
     */
    public List<InstructionReply> findListByInstructionId(@Param("instructionId") String instructionId);

}
