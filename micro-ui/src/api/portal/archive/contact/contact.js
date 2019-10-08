import request from '@/router/axios'

/**
 *  获取场所关联列表
 * @param query 场所id
 */
export function fetchGlList (query) {
    return request({
        url: 'portal/ylcs/contact/glPage',
        method: 'get',
        params: query
    })
}

/**
 *  获取警综场所列表
 * @param query 场所名称、区域、派出所、场所地址
 */
export function fetchList (query) {
    return request({
        url: 'portal/ylcs/contact/page',
        method: 'get',
        params: query
    })
}

export function addObj (obj) {
    return request({
        url: 'portal/ylcs/contact',
        method: 'post',
        data: obj
    })
}