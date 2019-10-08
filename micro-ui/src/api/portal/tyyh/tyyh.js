

import request from '@/router/axios'

export function fetchDeptTree (query) {
  return request({
    url: '/admin/dept/user-tree',
    method: 'get',
    params: query
  })
}

export function fetchUser (orgid) {
  return request({
    url: 'portal/Tyyh/DownUser/'+orgid,
    method: 'put',
  })
}

export function fetchList (query) {
  return request({
    url: 'portal/Tyyh/page',
    method: 'get',
    params: query
  })
}

export function fetchTree (query) {
  return request({
    url: 'portal/Tyjg/getJgTree/',
    method: 'get',
    params: query
  })
}

