import request from '@/router/axios'

export function fetchArea (query) {
    return request({
        url: '/portal/area/areaData',
        method: 'get',
        params: query
    })
}

export function fetchHylx (query) {
    return request({
        url: '/admin/dict/tree',
        method: 'get',
        params: query
    })
}

export function fetchJfdj () {
    return request({
        url: '/admin/dict/type/jfdj',
        method: 'get',
    })
}

/**
 * 获取所有行政区划数据 、没有等级区分 主要用于字典翻译
 */
export function findDictArea () {
    return request({
        url: '/portal/area/dict/all',
        method: 'get'

    })
}

export function fetchAddress (query) {
    return request({
        url: '/portal/mapQuery/mapList',
        method: 'get',
        params: query
    })
}