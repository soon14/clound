import request from '@/router/axios'

export function findListPage (query) {
    return request({
        url: 'portal/asj/dwAjxx',
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