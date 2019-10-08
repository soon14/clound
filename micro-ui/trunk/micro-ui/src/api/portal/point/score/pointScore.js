import request from '@/router/axios'


/**
 * 重置当前周期积分分值
 */
export function resetCurrentCycleScore() {
    return request({
        url: '/portal/point/score/resetCurrentCycleScore',
        method: 'get',
        params: {}
    })
}

/**
 * 积分撤回功能
 * @param data
 */
export function rollBackScore(data) {
    return request({
        url: '/portal/point/score/rollBackScore',
        method: 'put',
        params: data
    })
}

/**
 * 积分排名
 * @param query
 */
export function fetchList(query) {
    return request({
        url: '/portal/point/score/page',
        method: 'get',
        params: query
    })
}

/**
 * 根据特行场所信息、
 * @param placeId
 */
export function getPlaceById(placeId) {
    return request({
        url: '/portal/point/score/detail/' + placeId,
        method: 'get'
    })
}

/**
 * 特行场所积分信息
 * @param placeId
 */
export function placeHistoryScore(placeId) {
    return request({
        url: '/portal/point/score/history/score/' + placeId,
        method: 'get'
    })
}


/**
 * 重置所有积分特行场所的积分信息
 * 适用于定时调度任务
 */
export function resetAllScore() {
    return request({
        url: '/portal/point/score/resetAllScore',
        method: 'get'
    })
}

/**
 * 根据场所获取当前周期积分
 * @param placeId
 */
export function currentCycleScoreByPlaceId(placeId) {
    return request({
        url: "/portal/point/score/current_score/"+placeId,
        method: "get"
    })
}


/**
 * 积分排名
 * @param query
 */
export function topScoreList(query) {
    return request({
        url: '/portal/point/score/list',
        method: 'get',
        params: query
    })
}




