
import {validatenull} from '@/util/validate'
import request from '@/router/axios'

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

export function findOne (dwbh) {
    return request({
        url: 'portal/Jxyxt/findOne/'+ dwbh,
        method: 'get',
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

export async function  getClImg(xxid,id) {
        let src = "/img/unit.jpg";
        await axios({
            url: '/portal/Cjcl/img/' + xxid,
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

    if(!validatenull(id)){
        let img =document.getElementById(id);
        img.src =src
    }
    return src;
}

export async function  getRyImg(xxid,id) {
    let src = "/img/people.png";
    await axios({
        url: '/portal/Cyry/img/' + xxid,
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

    if(!validatenull(id)){
        let img =document.getElementById(id);
        img.src =src
    }
    return src;
}
