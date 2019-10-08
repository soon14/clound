import request from '@/router/axios'


/**
 *  自动指标和手动指标分值统计信息
 * @param query
 */
export function countPage(query) {
    return request({
        url: '/portal/point/detail/countPage',
        method: 'get',
        params: query
    })
}

/**
 *  自动指标和手动指标明细
 * @param query
 */
export function detailPage(query) {
    return request({
        url: '/portal/point/detail/detailPage',
        method: 'get',
        params: query
    })
}

/**
 * 手动积分
 * @param obj
 */
export function passivePoint (obj) {
    return request({
        url: '/portal/point/detail/passivePoint',
        method: 'put',
        data: obj
    })
}






