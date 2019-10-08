import request from '@/router/axios'

export function IsGz (params) {
    return request({
        url: '/portal/Csgz/isgz',
        method: 'get',
        params: params
    })
}