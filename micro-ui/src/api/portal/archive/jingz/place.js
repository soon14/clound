import request from '@/router/axios'

/**
 *  警综管辖单位 获取明细信息
 * @param query 旅馆从业人员信息
 */
export function findOnePlace (query) {
    return request({
        url: 'portal/archive/place/findOne',
        method: 'get',
        params: query
    })
}

