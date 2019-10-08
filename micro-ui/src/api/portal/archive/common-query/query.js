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