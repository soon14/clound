

import request from '@/router/axios'

export function fetchList(query) {
    return request({
        url: '/portal/PlanLibrary/page',
        method: 'get',
        params: query
    })
}

    export function addObj (obj) {
        return request({
            url: '/portal/PlanLibrary/add',
            method: 'post',
            data: obj
        })
    }

export function putObj (obj) {
    return request({
        url: '/portal/PlanLibrary/put',
        method: 'put',
        data: obj
    })
}

export function delObj (id) {
    return request({
        url: '/portal/PlanLibrary/removeById/'+ id,
        method: 'delete'
    })
}

export function fetchLibrarylist (query) {
    return request({
        url: '/portal/PlanLibrary/fetchLibrarylist',
        method: 'get',
        params: query
    })
}
