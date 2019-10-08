import request from '@/router/axios'

/**
 *  警综管辖单位 获取明细信息
 * @param query 警综从业人员信息
 */
export function findEmployeeJzPage (query) {
    return request({
        url: 'portal/jz/employee/page',
        method: 'get',
        params: query
    })
}

/**
 * 统计 从业人员 离岗和上岗 人数
 * @param unitId
 */
export function employeeMonthCount (unitId) {
    return request({
        url: 'portal/jz/employee/count/' + unitId,
        method: 'get'
    })
}

/**
 * 根据系统id获取从业人员
 * @param id
 */
export function findById(id) {
    return request({
        url:"portal/jz/employee/"+id,
        method:"get"
    })
}
