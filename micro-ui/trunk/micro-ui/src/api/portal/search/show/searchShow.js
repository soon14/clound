import request from '@/router/axios'
import Qs from 'qs'

/**
 *  统计查询首页
 * @param query
 */

export function searchIndex(query) {
    return request({
        url: '/portal/search/show/index',
        method: 'get',
        params: query
    })
}

export function listPage(query) {
    return request({
        url: '/portal/search/show/listPage',
        method: 'get',
        params: query,
        paramsSerializer:function (params) {
            return Qs.stringify(params,{arrayFormat:'repeat'});
        }
    })
}









