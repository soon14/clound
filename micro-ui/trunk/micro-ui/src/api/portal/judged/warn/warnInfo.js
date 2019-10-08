import request from '@/router/axios'
/**
 * 异常警告信息
 * @param query
 */
export function findWarnInfoPage (query) {
  return request({
    url: 'portal/judged/warn/info/page',
    method: 'get',
    params: query
  })
}

/**
 * 异常警告信息
 * 关联 模型信息、及积分扣分警告信息
 * @param query
 */
export function findRelationPage (query) {
  return request({
    url: 'portal/judged/warn/info/relation/page',
    method: 'get',
    params: query
  })
}
