import request from '@/router/axios'

/**
 * 同一车辆不同车行多次修改分析 列表信息
 * @param query
 */
export function analyzePage(query){
    return request({
        url: 'portal/different/places/revamp/monthPage',
        method: 'get',
        params: query
    })
}

/**
 * 同一车辆不同车行多次修改分析 列表信息
 * @param query
 */
export function fetchListPage(query){
    return request({
        url: 'portal/different/places/revamp/listPage',
        method: 'get',
        params: query
    })
}

/**
 * 同一车辆不同车行多次修改分析 详情信息
 * @param query
 */
export function dialog(query){
    return request({
        url: 'portal/different/places/revamp/oneToMany',
        method: 'get',
        params: query
    })
}
