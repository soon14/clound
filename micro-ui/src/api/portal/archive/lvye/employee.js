import request from '@/router/axios'

import {validatenull} from '@/util/validate'
/**
 *  旅业-从业人员 分页列表
 * @param query 从业人员实体类、从业单位ID是必填项
 */
export function findEmployeePage(query) {
    return request({
        url: 'portal/ly/employee/page',
        method: 'get',
        params: query
    })
}

/**
 * 统计当前旅馆 从业人员 离岗和上岗 人数
 * @param unitId 旅馆编号
 */
export function monthCount (unitId) {
    return request({
        url: 'portal/ly/employee/count/' + unitId,
        method: 'get'
    })
}
export function getImg(employeeId, code,id) {
    return  request({
        url: 'portal/ly/employee/img/' + code+"/"+employeeId,
        method: 'get',
        responseType: 'blob'
    }).then((response) => { // 处理返回的文件流
        let blob = response.data;
        if(validatenull(id)){
            id=employeeId;
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
