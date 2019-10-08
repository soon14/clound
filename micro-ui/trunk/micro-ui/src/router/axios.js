import {serialize} from '@/util/util'
import {getStore} from '../util/store'
import NProgress from 'nprogress' // progress bar
import errorCode from '@/const/errorCode'
import router from "@/router/router"
import {Message} from 'element-ui'
import 'nprogress/nprogress.css'
import store from "@/store"; // progress bar style
import {vaildatePc, validatenull} from '@/util/validate'

axios.defaults.timeout = 60000
// 返回其他状态吗
axios.defaults.validateStatus = function (status) {
    return status >= 200 && status <= 500 // 默认的
}
// 跨域请求，允许保存cookie
axios.defaults.withCredentials = true
// NProgress Configuration
NProgress.configure({
    showSpinner: false
})

// HTTPrequest拦截
axios.interceptors.request.use(config => {
    NProgress.start() // start progress bar
    const TENANT_ID = getStore({name: 'tenantId'})
    const isToken = (config.headers || {}).isToken === false
    const noLocal = !(config.headers || {}).noLocal
    let token = store.getters.access_token
    if (noLocal && token && !isToken) {
        config.headers['Authorization'] = 'Bearer ' + token// token
    }
    if (TENANT_ID) {
        config.headers['TENANT_ID'] = TENANT_ID // 租户ID
    }
    // headers中配置serialize为true开启序列化
    if (config.methods === 'post' && config.headers.serialize) {
        config.data = serialize(config.data)
        delete config.data.serialize
    }
    return config
}, error => {
    return Promise.reject(error)
})


// HTTPresponse拦截
axios.interceptors.response.use(res => {
    NProgress.done()
    const status = Number(res.status) || 200
    const message = res.data.msg || errorCode[status] || errorCode['default']
    if (status === 401) {
        store.dispatch('FedLogOut').then(() => {
            if (vaildatePc()) {
                router.push({path: '/login'})
            } else {
                router.push({path: '/wxLogin'})
            }
        })
        return
    }
    //没有权限的跳转 原理上 只是PC端才适用
    if (status === 403 && vaildatePc()) {
        let url = res.headers.contextpath
        if (!validatenull(url) && url.indexOf("http") != -1) {
            window.location.href = url
            return
        }
    }

    if (status !== 200 || res.data.code === 1) {
        Message({
            message: message,
            type: 'error'
        })
        return Promise.reject(new Error(message))
    }

    return res
}, error => {
    NProgress.done()
    return Promise.reject(new Error(error))
})

export default axios
