import request from '@/router/axios'

/**
 * 获取从业人员统计列表
 * @param query
 */
export function treeList(query) {
  return request({
      url: '/portal/statistics/employee/tree_list',
      method: 'get',
      params: query
  });
}

/**
 * 获取从业人员信息列表
 * @param query
 */
export function employeeList(query) {
    return request({
        url:"/portal/statistics/employee/message/page",
        method:"get",
        params:query
    });
}

/**
 * 根据来源系统获取从业人员信息列表
 * @param query
 */
export function employeeListBySourceType(query) {
    return request({
        url:"/portal/statistics/employee/message/source_type/page",
        method:"get",
        params:query
    });
}

/**
 * 根据身份证号获取违法犯罪列表
 * @param zjhm
 */
export function illegalityListByZjhm(zjhm){
    return request({
        /*url:"/portal/statistics/employee/illegality/list/"+zjhm,*/
        url:"/portal/statistics/employee/illegality/list/441522198705048616",
        method:"get",
    })
}
