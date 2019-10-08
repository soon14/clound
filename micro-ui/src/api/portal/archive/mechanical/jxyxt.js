
import {validatenull} from '@/util/validate'
import request from '@/router/axios'

export function findYcjg (query) {
    return request({
        url: 'portal/CsYcjg/page',
        method: 'get',
        params: query
    })
}
export function findCyryPage (query) {
    return request({
        url: 'portal/Cyry/page',
        method: 'get',
        params: query
    })
}

export function findCjclPage (query) {
    return request({
        url: 'portal/Cjcl/page',
        method: 'get',
        params: query
    })
}

export function CountCyry (query) {
    return request({
        url: 'portal/Cyry/CountAll',
        method: 'get',
        params: query
    })
}

export function CountCjcl (query) {
    return request({
        url: 'portal/Cjcl/CountAll',
        method: 'get',
        params: query
    })
}

export function findOne (query) {
    return request({
        url: 'portal/archive/common/findOne',
        method: 'get',
        params: query
    })
}

export function getRyDetail (zjhm) {
    return request({
        url: '/portal/Cyry/Ryxq/' + zjhm,
        method: 'get'
    })
}

export function getClxqDetail (jdchphm) {
    return request({
        url: '/portal/Cjcl/Clxq/' + jdchphm,
        method: 'get'
    })
}



export function getClImg(xxid,id) {
    return  request({
        url: '/portal/Cjcl/img/' + xxid,
        method: 'get',
        responseType: 'blob'
    }).then((response) => { // 处理返回的文件流
        let blob = response.data;
        if(validatenull(id)){
            id=xxid;
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

export function getRyImg(xxid,id) {
    return  request({
        url: '/portal/Cyry/img/' + xxid,
        method: 'get',
        responseType: 'blob'
    }).then((response) => { // 处理返回的文件流

        let blob = response.data;
        if(validatenull(id)){
            id=xxid;
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

export function getImg(employeeId,id) {

    return  request({
        url: 'portal/Cjcl/img/'+employeeId,
        method: 'get',
        responseType: 'blob'
    }).then((response) => { // 处理返回的文件流
        let blob = response.data;
        if(validatenull(id)){
            id=employeeId;
        }
        let img =document.getElementById(id);
        if(null===blob||undefined===typeof (blob)||blob.size<=0){
            img.src="/img/portal/people.png";
        }else{
            img.src = URL.createObjectURL(blob);
            window.setTimeout(function () {
                window.URL.revokeObjectURL(blob)
            }, 0)
        }


    })

}
