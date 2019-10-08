package com.cloud.portal.judged.instruction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.entity.SysUser;
import com.cloud.common.security.service.MicroUser;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.judged.instruction.mapper.InstructionReplyMapper;
import com.cloud.portal.judged.instruction.model.InstructionReply;
import com.cloud.portal.judged.instruction.service.InstructionReplyService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Molly
 * @date Created in 2019/6/24
 * @description: 智能预警-反馈service实现类
 * @modified By: Molly
 */
@Service
public class InstructionReplyServiceImpl extends ServiceImpl<InstructionReplyMapper, InstructionReply> implements InstructionReplyService {

    @Override
    public List<InstructionReply> findListByInstructionId(String instructionId) {
        return this.baseMapper.findListByInstructionId(instructionId);
    }

    @Override
    public boolean saveReply(InstructionReply instructionReply) {
        MicroUser user = SecurityUtils.getUser();
        SysUser sysUser = user.getSysUser();
        instructionReply.setPoliceNo(sysUser.getPoliceNumber());
        instructionReply.setPoliceName(sysUser.getName());
        instructionReply.setReplyOrg(user.getSysDept().getCode());
        instructionReply.setReplyTime(LocalDateTime.now());
        return super.save(instructionReply);
    }
}
