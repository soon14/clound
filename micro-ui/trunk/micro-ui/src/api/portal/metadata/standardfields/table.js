

import request from '@/router/axios'

export function fetchTree (query) {
  return request({
    url: '/portal/table/tree',
    method: 'get',
    params: query
  })
}
export function fetchPreViewList (query) {
  return request({
    url: '/portal/table/preViewPage',
    method: 'get',
    params: query
  })
}
export function fetchList (query) {
  return request({
    url: '/portal/table/page',
    method: 'get',
    params: query
  })
}

export function addObj (obj) {
  return request({
    url: '/portal/table/import',
    method: 'post',
    data: obj
  })
}

export function getObj (id) {
  return request({
    url: '/portal/table/' + id,
    method: 'get'
  })
}

export function delObj (id) {
  return request({
    url: '/portal/table/' + id,
    method: 'delete'
  })
}

export function putObj (obj) {
  return request({
    url: '/portal/table',
    method: 'put',
    data: obj
  })
}

export function checkName (name,dbId) {
    return request({
        url: '/portal/table/checkName/' + name+ '/' + dbId ,
        method: 'get'
    })
}

export function checkDbConn (dbId) {
  return request({
    url: '/portal/RdbSource/checkDbConn/' + dbId,
    method: 'get'
  })
}