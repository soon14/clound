

import request from '@/router/axios'
import {validatenull} from '@/util/validate'

export function fetchDeptTree (query) {
  return request({
    url: '/admin/dept/user-tree',
    method: 'get',
    params: query
  })
}

export function fetchUser (obj) {
  return request({
    url: 'admin/user/DownUser',
      method: 'post',
      data: obj
  })
}

export function fetchUserOne (obj) {
  return request({
    url: 'admin/user/DownUser',
    method: 'post',
    data: obj
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
    url: 'portal/Tyjg/getJgTree',
    method: 'get',
    params: query
  })
}

/**
 * 伪分页信息
 * @param data
 * @param page
 * @returns {Array}
 */
export function getPageData(data, page) {
  let currentPage = page.currentPage
  let size = page.pageSize
  let totalSize = currentPage * size
  if (validatenull(data)) {
    return []
  } else {
    let tempData = []
    for (let i = (currentPage - 1) * size, j = data.length; i < totalSize && i < j; i++) {
      tempData.push(data[i])
    }
    return tempData
  }

}