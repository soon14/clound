import request from '@/router/axios'
import Qs from 'qs'
import {validatenull} from '@/util/validate'
import {deepClone} from '@/util/util'

/**
 *  自动指标和手动指标分值统计信息
 * @param query
 */
export function fetchPage(page, query) {
    let urlKey = "urlKey";
    let method = "method";
    let obj = {}, temp = {}
    if (validatenull(page)) {
        page = {}
    }
    if (!validatenull(query)) {
        temp = deepClone(query)
        Object.keys(temp).forEach(ele => {
            if (urlKey == ele || urlKey === ele || method == ele || method === ele) {
                obj[ele] = temp[ele]
            } else {
                obj["map[" + ele + "]"] = temp[ele]
            }

        });
    }
    let param = Object.assign(obj, page)
    return request({
        url: '/rest/maya/api/page',
        method: 'get',
        params: param,
        paramsSerializer: function (params) {
            return Qs.stringify(params, {arrayFormat: 'repeat'});
        }
    })
}

/**
 * 伪分页信息
 * @param data
 * @param page
 * @returns {Array}
 */
export function getPageData(data, page) {
    let currentPage = page.currentPage
    let size = page.pageSize
    let totalSize = currentPage * size
    if (validatenull(data)) {
        return []
    } else {
        let tempData = []
        for (let i = (currentPage - 1) * size, j = data.length; i < totalSize && i < j; i++) {
            tempData.push(data[i])
        }
        return tempData
    }

}
