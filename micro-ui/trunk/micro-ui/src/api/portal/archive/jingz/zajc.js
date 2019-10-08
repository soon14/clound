import request from '@/router/axios'

/**
 * @param query 警综单位id
 */
export function findZajcPage (query) {
    return request({
        url: 'portal/jz/zajc/findZajcPage',
        method: 'get',
        params: query
    })
}

/**
*  警综-获取治安检查详情
* @param id 检查记录id
*/
export function getZajcDetail (id) {
    return request({
        url: 'portal/jz/zajc/' + id,
        method: 'get'
    })
}
/**
 *  警综-获取治安检查项
 * @param id 检查记录id
 */
export function getZajcContent (id) {
    return request({
        url: 'portal/jz/zajc/getZajcConfig/'+ id,
        method: 'get',
    })
}