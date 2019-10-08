import request from '@/router/axios'

/**
 * 维修记录异常分析 列表信息
 * @param query
 */
export function analyzePage(query){
    return request({
        url: 'portal/analyze/machine/rre/page',
        method: 'get',
        params: query
    })
}

/**
 * 维修记录异常分析 列表信息
 * @param query
 */
export function fetchListPage(query){
    return request({
        url: 'portal/analyze/machine/rre/listPage',
        method: 'get',
        params: query
    })
}

