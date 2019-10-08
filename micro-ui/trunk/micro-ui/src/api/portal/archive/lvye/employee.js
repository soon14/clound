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

export async function  getImg(employeeId, code,tagId) {
    let src="/img/people.png";
   await axios({
       url: 'portal/ly/employee/img/' + code+"/"+employeeId,
       method: 'get',
       responseType: 'blob'
    }).then(response => {
       let blob = response.data;
       if(null!==blob&&undefined!==typeof (blob)&&blob.size>0){
           src= URL.createObjectURL(blob);
           window.setTimeout(function () {
               window.URL.revokeObjectURL(blob)
           }, 0)
       }
    }).catch((error) => {
    })

    if(!validatenull(tagId)){
        let img =document.getElementById(tagId);
        img.src =src
    }

    return src;

}

/**
 * 获取从业人员信息
 * @param id
 * @param code
 */
export function getOne(id,code) {
    return request({
        url: 'portal/ly/employee/'+code+'/'+id,
        method: 'get',
    })
}


