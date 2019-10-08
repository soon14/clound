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

/**
 *  获取场所关联列表-警综
 * @param query 场所id
 */
export function fetchJzGlList (query) {
    return request({
        url: 'portal/ylcs/contact/jzGlPage',
        method: 'get',
        params: query
    })
}

/**
 *  获取场所列表(娱乐场所、机修业、旅业)
 * @param query 场所名称、区域、派出所、场所地址
 */
export function fetchJzList (query) {
    return request({
        url: 'portal/ylcs/contact/jzPage',
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

export function addJzObj (obj) {
    return request({
        url: 'portal/ylcs/contact/jzContact',
        method: 'post',
        data: obj
    })
}

export function delObj (jzdwbh,qtdwbh) {
    return request({
        url: 'portal/ylcs/contact/' + jzdwbh+ '/' + qtdwbh,
        method: 'delete'
    })
}
/**
 *  自动关联
 */
export function autoAddObj () {
    return request({
        url: 'portal/ylcs/contact/autoContact',
        method: 'post'
    })
}