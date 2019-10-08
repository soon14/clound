import request from '@/router/axios'

/**
 *等级评定方案分页查询
 */
export function fetchList(query) {
  return request({
    url: '/portal/point/level/page',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
  return request({
    url: '/portal/point/level/save',
    method: 'post',
    data: obj
  })
}

export function getObj(id) {
  return request({
    url: '/portal/point/level/get/' + id,
    method: 'get'
  })
}

export function delObj(id) {
  return request({
    url: '/portal/point/level/del/' + id,
    method: 'delete'
  })
}

export function putObj(obj) {
  return request({
    url: '/portal/point/level/update',
    method: 'put',
    data: obj
  })
}

/**
*根据方案id获取等级评定子项
*/
export function getChild(levelId) {
    return request({
        url: '/portal/point/level/child/' + levelId,
        method: 'get'
    })
}

/**
 * 更新方案状态
 * @param id 方案ID
 * @param status 状态（0 启用 1 停用 ）
 */
export function changeStatus(id, status) {
  return request({
    url: '/portal/point/level/status',
    method: 'put',
    params: {
      id: id,
      status: status
    }
  })
}



