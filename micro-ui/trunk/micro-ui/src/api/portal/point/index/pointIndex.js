import request from '@/router/axios'

export function fetchList(query) {
    return request({
        url: '/portal/point/index/page',
        method: 'get',
        params: query
    })
}

export function addObj(obj) {
    return request({
        url: '/portal/point/index/save',
        method: 'post',
        data: obj
    })
}

export function getObj(id) {
    return request({
        url: '/portal/point/index/get/' + id,
        method: 'get'
    })
}

export function delObj(id) {
    return request({
        url: '/portal/point/index/del/' + id,
        method: 'delete'
    })
}

export function putObj(obj) {
    return request({
        url: '/portal/point/index/update',
        method: 'put',
        data: obj
    })
}

/**
 * 判断标识符是否存在
 * @param id
 * @param udId
 */
export function udIdExit(id, udId) {
    return request({
        url: '/portal/point/index/udId/exit',
        method: 'get',
        params: {
            id: id,
            udId: udId
        }
    })
}

/**
 * 更新指标状态
 * @param id 主键ID
 * @param status 状态（0 启用 1 停用 ） 除了这两个状态 其他都是异常数据
 */
export function putStatus(id, status) {
    return request({
        url: '/portal/point/index/status',
        method: 'put',
        params: {
            id: id,
            status: status
        }
    })
}

/**
 * 获取指标树形结构信息
 * 默认状态下是手动指标
 * @param indexType 指标类型（0 自动指标 1 手动指标）
 * @param placeType 七行八所类型
 */
export function indexTreeByType(indexType, placeType) {
    return request({
        url: '/portal/point/index/indexTreeByType',
        method: 'get',
        params: {
            indexType: indexType,
            placeType: placeType
        }
    })
}

/**
 * 重置积分分数
 */
export function resetScore() {
    return request({
        url: '/portal/point/index/resetScore',
        method: 'get',
        params: {}
    })
}



