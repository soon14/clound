import request from '@/router/axios'

import {validatenull} from '@/util/validate'
/**
 *  旅业信息 分页列表
 * @param query 旅馆从业人员信息
 */
export function findOneHotel (query) {
    return request({
        url: 'portal/archive/ly/findOne',
        method: 'get',
        params: query
    })
}

