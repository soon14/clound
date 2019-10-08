import request from '@/router/axios'
import {validatenull} from '@/util/validate'
/**
 * @param query 警综证件信息
 */
export function findZjxxPage (query) {
    return request({
        url: 'portal/Zjxx/page',
        method: 'get',
        params: query
    })
}

export function getZjxxImg(fileid,id) {
    return  request({
        url: '/portal/Zjxx/img/' + fileid,
        method: 'get',
        responseType: 'blob'
    }).then((response) => { // 处理返回的文件流
        let blob = response.data;
        if(validatenull(id)){
            id=fileid;
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

