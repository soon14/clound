import request from '@/router/axios'

/**
 *  旅业-日常接口 分页列表
 * @param query 日常检查实体类、从业单位ID是必填项
 */
export function findCheckPage(query) {
    return request({
        url: 'portal/ly/check/page',
        method: 'get',
        params: query
    })
}



