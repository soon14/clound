package com.cloud.portal.judged.instruction.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.data.util.DataScopeUtil;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.judged.instruction.model.Instruction;
import com.cloud.portal.judged.instruction.service.InstructionService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/*
 * @author Molly
 * @date Created in 2019/6/24
 * @description: 智能预警-指令控制层
 * @modified By: Molly
 */
@RestController
@AllArgsConstructor
@RequestMapping("/judged/instruction")
public class InstructionController {

    @Autowired
    private InstructionService instructionService;

    @GetMapping("/page")
    @SysLog("预警中心查询")
    @ApiOperation(httpMethod = "GET", value = "预警查询")
    public R findListPage(Page page, Instruction instruction){
        instruction.setNotContainDraft(true);
        return new R<>(instructionService.page(page,instruction));
    }

    @GetMapping("/current_user/send_page")
    @SysLog("查询当前用户发布的指令列表")
    @ApiOperation(httpMethod = "GET", value = "查询当前用户发布的指令列表")
    public R findCurrentUserSendListPage(Page page, Instruction instruction){
        return new R<>(instructionService.findCurrentUserSendListPage(page,instruction));
    }

    @GetMapping("overtime_page")
    @SysLog("查询超时反馈指令")
    @ApiOperation(httpMethod = "GET",value = "查询超时反馈指令")
    public R findOvertimeListPage(Page page,Instruction instruction){
        return new R(instructionService.findOvertimeListPage(page,instruction));
    }

    @PostMapping("/save_artificial_instruction")
    @SysLog("保存人工指令")
    @ApiOperation(httpMethod = "POST", value = "保存人工指令")
    public R saveArtificialInstruction(@Valid @RequestBody Instruction instruction) {
        instruction.setType(Instruction.ARTIFICIAL_INSTRUCTION_TYPE);
        instruction.setStatus(Instruction.DRAFT_INSTRUCTION_STATUS);
        return new R<>(instructionService.saveOrUpdate(instruction));
    }

    @PostMapping("/send_artificial_instruction")
    @SysLog("下发人工指令")
    @ApiOperation(httpMethod = "POST", value = "下发人工指令")
    public R sendArtificialInstruction(@Valid @RequestBody Instruction instruction) {
        instruction.setType(Instruction.ARTIFICIAL_INSTRUCTION_TYPE);
        instruction.setStatus(Instruction.SEND_SUCCESS_INSTRUCTION_STATUS);  //发送成功
        instruction.setSendTime(LocalDateTime.now());
        instruction.setSendOrg(SecurityUtils.getUser().getSysDept().getCode());
        return new R<>(instructionService.saveOrUpdate(instruction));
    }

    @GetMapping("/delete/{id}")
    @SysLog("删除指令")
    @ApiOperation(httpMethod = "GET", value = "删除指令")
    public R delete(@PathVariable("id") String id){
        return new R<>(instructionService.removeById(id));
    }

}
