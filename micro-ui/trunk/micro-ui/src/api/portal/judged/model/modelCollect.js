import request from '@/router/axios'
import {validatenull} from '@/util/validate'

export function getCollect (params) {
    return request({
        url: '/portal/collect/isCollect',
        method: 'get',
        params: params
    })
}

export function gradeModel (params) {
    return request({
        url: '/portal/collect/gradeModel',
        method: 'get',
        params: params
    })
}

export function deleteCollect (modelId) {
    return request({
        url: '/portal/collect/deleteCollect/'+modelId,
        method: 'get',
    })
}

export function findPage (query) {
    return request({
        url: 'portal/collect/page',
        method: 'get',
        params: query
    })
}

export function modelAll (query) {
    return request({
        url: 'portal/collect/modelAll',
        method: 'get',
        params: query
    })
}

/**
 * 获取列表模型图标--模型展示页面
 * @param 模型id
 */
export async function getIconList(id,imgid) {
    let flag='show'
    let src="/img/unit.jpg";
    await axios({
        url: '/portal/judged/model/icon/' + id+ '/' + flag ,
        method: 'get',
        responseType: 'blob'
    }).then(response => {
        debugger
        let blob = response.data;
        if(null!==blob&&undefined!==typeof (blob)&&blob.size>0){
            src= URL.createObjectURL(blob);
            window.setTimeout(function () {
                window.URL.revokeObjectURL(blob)
            }, 0)
        }
    }).catch((error) => {
    })

    if(!validatenull(imgid)){
        let img =document.getElementById(imgid+'_app');
        img.src =src
    }
    return src;
}

export async function geteditIcon(id,imgid) {
    let flag='show'
    let src="/img/unit.jpg";
    await axios({
        url: '/portal/judged/model/icon/' + id+ '/' + flag ,
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

    if(!validatenull(imgid)){
        let img =document.getElementById(imgid);
        img.src =src
    }
    return src;
}