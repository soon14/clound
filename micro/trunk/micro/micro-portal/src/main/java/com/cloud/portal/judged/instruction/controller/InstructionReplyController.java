package com.cloud.portal.judged.instruction.controller;

import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.judged.instruction.model.Instruction;
import com.cloud.portal.judged.instruction.model.InstructionReply;
import com.cloud.portal.judged.instruction.service.InstructionReplyService;
import com.cloud.portal.judged.instruction.service.InstructionService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
 * @author Molly
 * @date Created in 2019/6/24
 * @description: 智能预警-反馈控制层
 * @modified By: Molly
 */
@RestController
@AllArgsConstructor
@RequestMapping("/judged/instruction_reply")
public class InstructionReplyController {

    @Autowired
    private InstructionReplyService instructionReplyService;
    @Autowired
    private InstructionService instructionService;

    @GetMapping("/list_by_instructid")
    @SysLog("根据指令查询反馈信息")
    @ApiOperation(httpMethod = "GET", value = "根据指令查询反馈信息")
    public R findListByInstructionId(@RequestParam("instructionId") String instructionId){
        return new R<>(instructionReplyService.findListByInstructionId(instructionId));
    }

    @PostMapping("/save")
    @SysLog("添加反馈信息")
    @ApiOperation(httpMethod = "POST", value = "添加反馈信息")
    public R save(@Valid @RequestBody InstructionReply reply) {
        boolean flag = instructionReplyService.saveReply(reply);
        if(flag){
            Instruction instruction = new Instruction();
            instruction.setId(reply.getInstructId());
            instruction.setStatus(Instruction.PROCESSED_INSTRUCTION_STATUS);
            flag = instructionService.updateStatus(instruction);
        }
        return new R<>(flag);
    }

}
