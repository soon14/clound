import request from '@/router/axios'

export function fetchList(query) {
    return request({
        url: '/portal/point/scheme/page',
        method: 'get',
        params: query
    })
}

export function fetchCycleList(id) {
    return request({
        url: '/portal/point/scheme/cycleList/' + id,
        method: 'get'
    })
}

export function addObj(obj) {
    return request({
        url: '/portal/point/scheme/save',
        method: 'post',
        data: obj
    })
}
/**
 * 获取当前方案运行周期
 * **/
export function starCycle(obj) {
    return request({
        url: '/portal/point/scheme/starCycle',
        method: 'get',
        data: obj
    })
}

export function getObj(id) {
    return request({
        url: '/portal/point/scheme/get/' + id,
        method: 'get'
    })
}

export function delObj(id) {
    return request({
        url: '/portal/point/scheme/del/' + id,
        method: 'delete'
    })
}

export function deleteScore(id) {
    return request({
        url: '/portal/point/scheme/deleteScore/' + id,
        method: 'put'
    })
}

export function putObj(obj) {
    return request({
        url: '/portal/point/scheme/update',
        method: 'put',
        data: obj
    })
}

/**
 * 更新状态
 * @param id 主键ID
 * @param status 状态（0 启用 1 停用 ） 除了这两个状态 其他都是异常数据
 */
export function putStatus(id, status) {
    return request({
        url: '/portal/point/scheme/status',
        method: 'put',
        params: {
            id: id,
            status: status
        }
    })
}

export function resetScore() {
    return request({
        url: '/portal/point/scheme/resetScore',
        method: 'put',
    })
}


