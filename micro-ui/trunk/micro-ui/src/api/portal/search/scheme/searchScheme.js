import request from '@/router/axios'

/**
 *查询配置方案分页查询
 */
export function fetchList(query) {
  return request({
    url: '/portal/search/scheme/page',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
  return request({
    url: '/portal/search/scheme/save',
    method: 'post',
    data: obj
  })
}

export function getObj(id) {
  return request({
    url: '/portal/search/scheme/get/' + id,
    method: 'get'
  })
}

export function delObj(id) {
  return request({
    url: '/portal/search/scheme/del/' + id,
    method: 'delete'
  })
}

export function putObj(obj) {
  return request({
    url: '/portal/search/scheme/update',
    method: 'put',
    data: obj
  })
}

/**
 * 更新方案状态
 * @param id 方案ID
 * @param status 状态（0 启用 1 停用 ）
 */
export function changeStatus(id, status) {
  return request({
    url: '/portal/search/scheme/status',
    method: 'put',
    params: {
      id: id,
      status: status
    }
  })
}

export function fetchSchemeTree () {
  return request({
    url: '/portal/search/scheme/tree',
    method: 'get',
  })
}



