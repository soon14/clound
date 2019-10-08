

import request from '@/router/axios'

export function fetchList(query) {
    return request({
        url: '/portal/RdbSource/page',
        method: 'get',
        params: query
    })
}

    export function addObj (obj) {
        return request({
            url: '/portal/RdbSource/add',
            method: 'post',
            data: obj
        })
    }

export function putObj (obj) {
    return request({
        url: '/portal/RdbSource/put',
        method: 'put',
        data: obj
    })
}

export function delObj (id,type) {
    return request({
        url: '/portal/RdbSource/removeById/'+ id +'/'+ type,
        method: 'delete'
    })
}

export function handleCheck (obj) {
    return request({
        url: '/portal/RdbSource/check',
        method: 'get',
        params: obj
    })
}

export function getOneByName (obj) {
    return request({
        url: '/portal/RdbSource/getOneByName/' + obj,
        method: 'get'
    })
}
