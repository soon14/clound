/**
 * 全站权限配置
 *
 */
import router from './router/router'
import store from '@/store'
import {validatenull, vaildatePc} from '@/util/validate'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
NProgress.configure({showSpinner: false})
const lockPage = store.getters.website.lockPage // 锁屏页
router.beforeEach((to, from, next) => {
    // 缓冲设置
    if (to.meta.keepAlive === true && store.state.tags.tagList.some(ele => {
        return ele.value === to.fullPath
    })) {
        to.meta.$keepAlive = true
    } else {
        NProgress.start()
        if (to.meta.keepAlive === true && validatenull(to.meta.$keepAlive)) {
            to.meta.$keepAlive = true
        } else {
            to.meta.$keepAlive = false
        }
    }
    const meta = to.meta || {}
    if (store.getters.access_token) {
        if (store.getters.isLock && to.path != lockPage) {
            next({path: lockPage})
        } else if (to.path === '/login' || to.path === '/wxLogin') {
            if (vaildatePc()) {
                next('/')
            } else {
                next('/mobile')
            }
        } else {
            if (store.getters.roles.length === 0) {
                store.dispatch('GetUserInfo').then(() => {
                    next({...to, replace: true})
                }).catch(() => {
                    store.dispatch('FedLogOut').then(() => {
                        if (vaildatePc()) {
                            urlJump(next)
                        } else {
                            next('/wxLogin')
                        }
                    })
                })
            } else {
                const value = to.query.src || to.fullPath
                const label = to.query.name || to.name
                if (meta.isTab !== false && !validatenull(value) && !validatenull(label)) {
                    store.commit('ADD_TAG', {
                        label: label,
                        value: value,
                        params: to.params,
                        query: to.query,
                        group: router.$avueRouter.group || []
                    })
                }
                next()
            }
        }
    } else {
        if (meta.isAuth === false) {
            next()
        } else {
            if (vaildatePc()) {
                urlJump(next)
            } else {
                next('/wxLogin')
            }

        }
    }
})

function urlJump(next) {
    // next('/login') 如果是去除需要去除单点登录、把下面注释掉 放开这一句
    store.dispatch("LoginByCas").then((res) => {
        if (false == res || false === res) {
            //木有权限
            next('/401')
        } else {
            next()
        }
    }).catch((error) => {
        store.dispatch("loginOutCas").then(res => {
            window.location.href = res;
        });
    });
}

router.afterEach(() => {
    NProgress.done()
    const title = store.getters.tag.label
    router.$avueRouter.setTitle(title)
})
