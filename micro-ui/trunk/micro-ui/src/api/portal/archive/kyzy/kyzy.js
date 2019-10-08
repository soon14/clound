
import {validatenull} from '@/util/validate'
import request from '@/router/axios'

/** 根据id获取印章单位信息 */
export function findById (id) {
    return request({
        url: 'portal/kyzy/produce_unit/'+ id,
        method: 'get',
    })
}

/** 根据id获取印章信息 */
export function findSealInfoPage (query) {
    return request({
        url: 'portal/kyzy/seal_info/page',
        method: 'get',
        params: query
    })
}

/** 根据印章id获取章模扫描件 */
export function getScanImage(id,domId) {
    return request({
        url:'portal/kyzy/seal_info/scan_image/'+id,
        method:'get',
        responseType: 'blob'
    }).then((response) => { // 处理返回的文件流
        let blob = response.data;
        if(validatenull(domId)){
            return ;
        }
        let img =document.getElementById(domId);
        if(null===blob||undefined===typeof (blob)||blob.size<=0){
            img.src="/img/no_picture.png";
        }else{
            img.src = URL.createObjectURL(blob);
            window.setTimeout(function () {
                window.URL.revokeObjectURL(blob)
            }, 0)
        }
    })
}

/** 根据印章id获取备案印章图像 */
export function getAuditImage(id,domId) {
    return request({
        url:'portal/kyzy/seal_info/audit_image/'+id,
        method:'get',
        responseType: 'blob'
    }).then((response) => { // 处理返回的文件流
        let blob = response.data;
        if(validatenull(domId)){
            return;
        }
        let img =document.getElementById(domId);
        if(null===blob||undefined===typeof (blob)||blob.size<=0){
            img.src="/img/no_picture.png";
        }else{
            img.src = URL.createObjectURL(blob);
            window.setTimeout(function () {
                window.URL.revokeObjectURL(blob)
            }, 0)
        }
    })
}

/** 根据印章id获取排版印章图像 */
export function getMakeImage(id,domId) {
    return request({
        url:'portal/kyzy/seal_info/make_image/'+id,
        method:'get',
        responseType: 'blob'
    }).then((response) => { // 处理返回的文件流
        let blob = response.data;
        if(validatenull(domId)){
            return;
        }
        let img =document.getElementById(domId);
        if(null===blob||undefined===typeof (blob)||blob.size<=0){
            img.src="/img/no_picture.png";
        }else{
            img.src = URL.createObjectURL(blob);
            window.setTimeout(function () {
                window.URL.revokeObjectURL(blob)
            }, 0)
        }
    })
}