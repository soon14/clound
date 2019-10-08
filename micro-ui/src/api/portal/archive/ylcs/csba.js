import request from '@/router/axios'
import {validatenull} from '@/util/validate'

/**
 *  娱乐场所-获取场所备案详情
 * @param csbaId 场所id
 */
export function getCsbaJbxx (csbaId) {
    return request({
        url: '/portal/ylcs/csba/' + csbaId,
        method: 'get'
    })
}

/**
 *  娱乐场所-获取从业人员列表
 * @param query 从业人员实体类、从业单位ID是必填项
 */
export function findRyListPage (query) {
    return request({
        url: 'portal/ylcs/ryba/findRyListPage',
        method: 'get',
        params: query
    })
}

/**
 *  娱乐场所-统计从业人员在岗、离职数量
 * @param compId,csbabh 单位id 场所编号
 */
export function getRybaCount (compId,csbabh) {
    return request({
        url: '/portal/ylcs/ryba/getRybaCount/' + compId+ '/' + csbabh ,
        method: 'get'
    })
}
/**
 *  娱乐场所-获取从业人员详情
 * @param rybaId 人员id
 */
export function getRyDetail (rybaId) {
    return request({
        url: '/portal/ylcs/ryba/' + rybaId,
        method: 'get'
    })
}

/**
 *  娱乐场所-获取人员考勤（上下班打卡）列表
 * @param query 人员考勤实体类、从业单位ID是必填项
 */
export function findRykqPage (query) {
    return request({
        url: 'portal/ylcs/ryLog/findRyLogPage',
        method: 'get',
        params: query
    })
}

/**
 *  娱乐场所-获取人员考勤详情
 * @param rylogId 人员考勤id
 */
export function getRyKqDetail (rylogId) {
    return request({
        url: '/portal/ylcs/ryLog/' + rylogId,
        method: 'get'
    })
}

/**
 *  娱乐场所-获取保安巡检列表
 * @param query 保安巡检实体类、巡检单位ID是必填项
 */
export function findBaxjPage (query) {
    return request({
        url: 'portal/ylcs/baxj/findBaxjPage',
        method: 'get',
        params: query
    })
}

/**
 *  娱乐场所-获取安全检查列表
 * @param query 安全检查实体类、被检单位ID是必填项
 */
export function findAqjcPage (query) {
    return request({
        url: 'portal/ylcs/aqjc/findAqjcPage',
        method: 'get',
        params: query
    })
}
/**
 *  娱乐场所-获取安全检查详情
 * @param aqjcId 检查记录id
 */
export function getAqjcDetail (aqjcId) {
    return request({
        url: '/portal/ylcs/aqjc/' + aqjcId,
        method: 'get'
    })
}
/**
 *  娱乐场所-获取安全检查项
 * @param aqjcId 检查记录id
 */
export function getAqjcContent (aqjcId) {
    return request({
        url: 'portal/ylcs/aqjc/getAqjcContent/'+ aqjcId,
        method: 'get',
    })
}
/**
 *  娱乐场所-获取场所备案附件（证照信息）列表
 * @param query 场所备案附件实体类、场所ID是必填项
 */
export function findCsbafjPage (query) {
    return request({
        url: 'portal/ylcs/csbafj/findCsbafjPage',
        method: 'get',
        params: query
    })
}
/**
 *  娱乐场所-场所关注
 * @param query 场所ID是必填项
 */
export function ShouCan (query) {
    return request({
        url: '/portal/Csgz/add',
        method: 'post',
        params: query
    })
}
export function IsGz (params) {
    return request({
        url: '/portal/Csgz/isgz',
        method: 'get',
        params: params
    })
}
export function getRyImg(rybaZp,id) {
    return  request({
        url: '/portal/ylcs/ryba/img/' + rybaZp,
        method: 'get',
        responseType: 'blob'
    }).then((response) => { // 处理返回的文件流
        let blob = response.data;
        if(validatenull(id)){
            id=rybaZp;
        }
        let img =document.getElementById(id);
        if(null===blob||undefined===typeof (blob)||blob.size<=0){
            img.src="/img/people.png";
        }else{
            img.src = URL.createObjectURL(blob);
            window.setTimeout(function () {
                window.URL.revokeObjectURL(blob)
            }, 0)
        }
    })
}
export function getJbxxImg(query,id) {
    return  request({
        url: '/portal/ylcs/csbafj/img',
        method: 'get',
        params: query,
        responseType: 'blob'
    }).then((response) => { // 处理返回的文件流
        let blob = response.data;
        if(validatenull(id)){
            id=query.csbaId;
        }
        let img =document.getElementById(id);
        if(null===blob||undefined===typeof (blob)||blob.size<=0){
            img.src="/img/unit.jpg";
        }else{
            img.src = URL.createObjectURL(blob);
            window.setTimeout(function () {
                window.URL.revokeObjectURL(blob)
            }, 0)
        }
    })
}
export function getPmtImg(csbaId,id) {
    return  request({
        url: '/portal/ylcs/csbafj/pmtImg/' + csbaId,
        method: 'get',
        responseType: 'blob'
    }).then((response) => { // 处理返回的文件流
        let blob = response.data;
        if(validatenull(id)){
            id=csbaId;
        }
        let img =document.getElementById(id);
        if(null===blob||undefined===typeof (blob)||blob.size<=0){
            img.src="/img/unit.jpg";
        }else{
            img.src = URL.createObjectURL(blob);
            window.setTimeout(function () {
                window.URL.revokeObjectURL(blob)
            }, 0)
        }
    })
}
