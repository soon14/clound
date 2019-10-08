

import request from '@/router/axios'

export function fetchList (query) {
  return request({
    url: '/admin/parameter/page',
    method: 'get',
    params: query
  })
}

export function addObj (obj) {
  return request({
    url: '/admin/parameter/save',
    method: 'post',
    data: obj
  })
}

export function getObj (id) {
  return request({
    url: '/admin/parameter/' + id,
    method: 'get'
  })
}

export function delObj (id) {
  return request({
    url: '/admin/parameter/' + id,
    method: 'delete'
  })
}

export function putObj (obj) {
  return request({
    url: '/admin/parameter',
    method: 'put',
    data: obj
  })
}
