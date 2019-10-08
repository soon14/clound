import request from '@/router/axios'

export function countAll (query) {
        return request({
            url: 'portal/archive/common/count',
            method: 'get',
            params: query
        })
    }

export function findListPage (query) {
    return request({
        url: 'portal/archive/common/page',
        method: 'get',
        params: query
    })
}
export function findOne (query) {
    return request({
        url: 'portal/archive/common/findOne',
        method: 'get',
        params: query
    })
}

export function findTab () {
    return request({
        url: 'portal/archive/common/findTab',
        method: 'get'

    })
}
