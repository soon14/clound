

import request from '@/router/axios'


export function fetchMenuTree (query) {
  return request({
    url: '/portal/unitMenu/tree',
    method: 'get',
    params: query
  })
}

export function addObj (obj) {
  return request({
    url: '/portal/unitMenu',
    method: 'post',
    data: obj
  })
}

export function getObj (id) {
  return request({
    url: '/portal/unitMenu/' + id,
    method: 'get'
  })
}

export function delObj (id) {
  return request({
    url: '/portal/unitMenu/' + id,
    method: 'delete'
  })
}

export function putObj (obj) {
  return request({
    url: '/portal/unitMenu',
    method: 'put',
    data: obj
  })
}
