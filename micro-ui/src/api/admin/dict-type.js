
import request from '@/router/axios'

export function fetchDictTypeList (query) {
    return request({
        url: '/admin/dicttype/page',
        method: 'get',
        params: query
    })
}

export function addObj (obj) {
    return request({
        url: '/admin/dicttype/',
        method: 'post',
        data: obj
    })
}

export function getObj (id) {
    return request({
        url: '/admin/dicttype/' + id,
        method: 'get'
    })
}

export function delObj (row) {
    return request({
        url: '/admin/dicttype/' + row.id + '/' + row.innerFlag,
        method: 'delete'
    })
}

export function putObj (obj) {
    return request({
        url: '/admin/dicttype/',
        method: 'put',
        data: obj
    })
}