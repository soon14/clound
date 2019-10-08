import {validatenull} from '@/util/validate'
import request from '@/router/axios'

/**
 * 查询预警列表(分页)
 * @param query
 */
export function findListPage(query){
    return request({
        url: 'portal/judged/instruction/page',
        method: 'get',
        params: query
    })
}

/**
 * 查询当前用户发布的指令列表（分页）
 * @param query
 */
export function findCurrentUserSendListPage(query) {
    return request({
        url:"portal/judged/instruction/current_user/send_page",
        method:"get",
        params:query
    })
}

/**
 * 查询超时反馈指令（分页）
 * @param query
 */
export function findOvertimeListPage(query) {
    return request({
        url:"portal/judged/instruction/overtime_page",
        method:"get",
        params:query
    })
}

/**
 * 保存人工指令
 * @param query
 */
export function saveArtificialInstruction(query) {
    return request({
        url:"portal/judged/instruction/save_artificial_instruction",
        method:"post",
        data:query
    })
}

/**
 * 下发人工指令
 * @param query
 */
export function sendArtificialInstruction(query) {
    return request({
        url:"portal/judged/instruction/send_artificial_instruction",
        method:"post",
        data:query
    })
}

/**
 * 删除指令
 * @param query
 */
export function delObj(query) {
    return request({
        url:"portal/judged/instruction/delete/"+query,
        method:"get"
    })
}

/**
 * 根据指令id获取反馈列表
 * @param query
 */
export function findReplyListByInstructionId(query) {
    return request({
        url:"portal/judged/instruction_reply/list_by_instructid",
        method:"get",
        params:query
    })
}

/**
 * 保存反馈信息
 * @param query
 */
export function saveReply(query) {
    return request({
        url:"portal/judged/instruction_reply/save",
        method:"post",
        data:query
    })
}


