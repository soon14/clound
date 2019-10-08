

import request from '@/router/axios'

export function fetchList(query) {
  return request({
    url: '/concern/personConcern/page',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
  return request({
    url: '/concern/personConcern',
    method: 'post',
    data: obj
  })
}

export function getObj(id) {
  return request({
    url: '/concern/personConcern/' + id,
    method: 'get'
  })
}

export function delObj(id) {
  return request({
    url: '/concern/personConcern/' + id,
    method: 'delete'
  })
}

export function putObj(obj) {
  return request({
    url: '/concern/personConcern',
    method: 'put',
    data: obj
  })
}


export function getOneByidCard (obj) {
  return request({
    url: '/concern/personConcern/getOneByidCard/' + obj,
    method: 'get'
  })
}
