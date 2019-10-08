import {validatenull} from '@/util/validate'
import request from '@/router/axios'

/**
 * 按积分类型统计当天新增积分记录总数
 */
export function totalTodayDetail(){
    return request({
        url: 'portal/point/monitor/total_today',
        method: 'get'
    })
}

export function findNewestAutomaticList(schemeid) {
    return request({
        url: 'portal/point/monitor/newest_automatic_list/'+schemeid,
        method: 'get'
    })
}


