import request from '@/router/axios'
import {validatenull} from '@/util/validate'
export function findPage (query) {
    return request({
        url: 'portal/dict/page',
        method: 'get',
        params: query
    })
}

export function getDict (type,source) {
    return   request({
        url: '/portal/dict/getDict/' + type+"/"+source,
        method: 'get'
    })
}


export function findLabel(type,source,val) {
    return new Promise((resolve, reject) => {
        if(validatenull(val)||validatenull(type)||validatenull(source)){
            resolve(val)
        }else{
            getDict(type, source).then((res) => {
                const data = res.data.data || {}
                if (Array.isArray(data) && data.length > 0) {
                    for (let i = 0; i < data.length; i++) {
                        if (data[i].value === val) {
                            val = data[i].label
                            break;
                        }
                    }
                }
                resolve(val)
            }).catch((err) => {
                reject()
            })
        }


    })


}
export function  getLabel(obj, val)  {
    let name = val
    if (Array.isArray(obj) && obj.length > 0) {
        for (let i = 0; i < obj.length; i++) {
            if (obj[i].value === val) {
                name = obj[i].label
                break;
            }
        }
    }return name
}