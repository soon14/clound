package com.cloud.portal.judged.instruction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.judged.instruction.model.InstructionReply;

import java.util.List;

/**
 * @author Molly
 * @date Created in 2019/6/24
 * @description: 智能预警-反馈service接口
 * @modified By: Molly
 * */
public interface InstructionReplyService extends IService<InstructionReply> {

    /**
     * 根据指令获取反馈列表
     * @param instructionId
     * @return
     */
    public List<InstructionReply> findListByInstructionId(String instructionId);

    /**
     * 保存反馈信息
     * @param instructionReply
     * @return
     */
    public boolean saveReply(InstructionReply instructionReply);

}
