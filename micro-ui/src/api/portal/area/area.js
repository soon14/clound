

import request from '@/router/axios'


export function fetchAreaTree (query) {
  return request({
    url: '/portal/area/tree',
    method: 'get',
    params: query
  })
}

export function addObj (obj) {
  return request({
    url: '/portal/area',
    method: 'post',
    data: obj
  })
}

export function getObj (id) {
  return request({
    url: '/portal/area/' + id,
    method: 'get'
  })
}

export function delObj (id) {
  return request({
    url: '/portal/area/' + id,
    method: 'delete'
  })
}

export function putObj (obj) {
  return request({
    url: '/portal/area',
    method: 'put',
    data: obj
  })
}

export function checkName (name) {
    return request({
        url: '/portal/area/checkName/' + name,
        method: 'get'
    })
}

export function checkCode (code) {
    return request({
        url: '/portal/area/checkCode/' + code,
        method: 'get'
    })
}