

import request from '@/router/axios'

export function fetchList (query) {
  return request({
    url: '/portal/field/page',
    method: 'get',
    params: query
  })
}
export function fetchPreViewList (query) {
  return request({
    url: '/portal/field/preViewPage',
    method: 'get',
    params: query
  })
}

export function preViewAddObj (obj) {
  return request({
    url: '/portal/field/import',
    method: 'post',
    data: obj
  })
}

export function addObj (obj) {
  return request({
    url: '/portal/field',
    method: 'post',
    data: obj
  })
}

export function getObj (id) {
  return request({
    url: '/portal/field/' + id,
    method: 'get'
  })
}

export function delObj (ids) {
  return request({
    url: '/portal/field/' + ids,
    method: 'delete'
  })
}

export function putObj (obj) {
  return request({
    url: '/portal/field',
    method: 'put',
    data: obj
  })
}

export function checkName (name,tableId) {
  return request({
    url: '/portal/field/checkName/' + name+ '/' + tableId,
    method: 'get'
  })
}

export function checkDbConn (dbId) {
  return request({
    url: '/portal/RdbSource/checkDbConn/' + dbId,
    method: 'get'
  })
}
