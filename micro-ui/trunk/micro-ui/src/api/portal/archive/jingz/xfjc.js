import request from '@/router/axios'

/**
 * @param query 警综单位id
 */
export function findXfjcPage (query) {
    return request({
        url: 'portal/jz/xfjc/findXfjcPage',
        method: 'get',
        params: query
    })
}

/**
*  警综-获取治安检查详情
* @param id 检查记录id
*/
export function getXfjcDetail (id) {
    return request({
        url: 'portal/jz/xfjc/' + id,
        method: 'get'
    })
}