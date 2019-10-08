import request from '@/router/axios'


/**
 * 一个单位印章被多次刻制
 * @param query
 */
export function analyPage(query){
    return request({
        url: 'portal/analyze/seal/sem/page',
        method: 'get',
        params: query
    })
}


/**
 * 一个单位印章被多次刻制
 * @param query
 */
export function fetchListPage(query){
    return request({
        url: 'portal/analyze/seal/sem/listPage',
        method: 'get',
        params: query
    })
}

