import request from '@/router/axios'

export function IsGz (params) {
    return request({
        url: '/portal/Csgz/isgz',
        method: 'get',
        params: params
    })
}

export function NoGz (dwbh) {
    return request({
        url: '/portal/Csgz/nogz/'+dwbh,
        method: 'get',
    })
}

export function findPage (query) {
    return request({
        url: 'portal/Csgz/page',
        method: 'get',
        params: query
    })
}