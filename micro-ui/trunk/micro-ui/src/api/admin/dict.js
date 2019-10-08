

import request from '@/router/axios'

export function fetchList (query) {
    return request({
        url: '/admin/dict/page',
        method: 'get',
        params: query
    })
}

export function addObj (obj) {
    return request({
        url: '/admin/dict/',
        method: 'post',
        data: obj
    })
}

export function getObj (id) {
    return request({
        url: '/admin/dict/' + id,
        method: 'get'
    })
}

export function delObj (ids, type, isTree) {
    return request({
        url: '/admin/dict/' + ids + '/' + type + '/' +isTree,
        method: 'delete'
    })
}

export function putObj (obj) {
    return request({
        url: '/admin/dict/',
        method: 'put',
        data: obj
    })
}

export function fetchDictTree (query) {
    return request({
        url: '/admin/dict/tree',
        method: 'get',
        params: query
    })
}

export function remote (type) {
    return request({
        url: '/admin/dict/type/' + type,
        method: 'get'
    })
}