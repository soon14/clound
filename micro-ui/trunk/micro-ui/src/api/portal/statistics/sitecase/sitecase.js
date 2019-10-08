import request from '@/router/axios'

export function siteListTree (query) {
    return request({
        url: '/portal/site/case/siteList',
        method: 'get',
        params: query
    })
}