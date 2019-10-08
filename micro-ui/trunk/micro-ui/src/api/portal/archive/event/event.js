import request from '@/router/axios'
import {validatenull} from '@/util/validate'

export function findEventPage (query) {
    return request({
        url: 'portal/event/eventPage',
        method: 'get',
        params: query
    })
}

export function findEventSignsPage (query) {
    return request({
        url: 'portal/event/eventSignsPage',
        method: 'get',
        params: query
    })
}

export function getEventDetail (id) {
    return request({
        url: 'portal/event/eventDetail/' + id,
        method: 'get'
    })
}

export function getEventSignsDetail (id) {
    return request({
        url: 'portal/event/eventSignsDetail/' + id,
        method: 'get'
    })
}