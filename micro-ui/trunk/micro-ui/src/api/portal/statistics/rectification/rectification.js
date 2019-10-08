import request from '@/router/axios'

export function treeList(query) {
  return request({
      url: '/portal/statistics/data_rectification/tree_list',
      method: 'get',
      params: query
  });
}

