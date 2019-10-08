import request from '@/router/axios'

/**
 * 从业人员未如实报备分析 列表信息
 * @param query
 */
export function analyzePage(query){
    return request({
        url: 'portal/analyze/entertainment/personTruthReport/page',
        method: 'get',
        params: query
    })
}

/**
 * 从业人员未如实报备分析 详情信息
 * @param query
 */
export function fetchListPage(query){
    return request({
        url: 'portal/analyze/entertainment/personTruthReport/listPage',
        method: 'get',
        params: query
    })
}

