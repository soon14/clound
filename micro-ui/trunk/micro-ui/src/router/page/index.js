import Layout from '@/page/index/'
import {vaildatePc} from '@/util/validate'

const indexPath = (function () {
    if (vaildatePc()) {
        return "/wel"
    } else {
        return "/mobile"
    }
})();
export default [
    {
        path: '/wxLogin',
        name: '政务微信',
        component: () =>
            import ( /* webpackChunkName: "page" */ '@/page/login/wxLogin'),
        meta: {
            keepAlive: true,
            isTab: false,
            isAuth: false
        }
    },
    {
        path: '/login',
        name: '登录页',
        component: () =>
            import ( /* webpackChunkName: "page" */ '@/page/login/login'),
        meta: {
            keepAlive: true,
            isTab: false,
            isAuth: false
        }
    },
    {
        path: '/lock',
        name: '锁屏页',
        component: () =>
            import ( /* webpackChunkName: "page" */ '@/page/lock/index'),
        meta: {
            keepAlive: true,
            isTab: false,
            isAuth: false
        }
    },
    {
        path: '/404',
        component: () =>
            import ( /* webpackChunkName: "page" */ '@/components/error-page/404'),
        name: '404',
        meta: {
            keepAlive: true,
            isTab: false,
            isAuth: false
        }
    },
    {
        path: '/403',
        component: () =>
            import ( /* webpackChunkName: "page" */ '@/components/error-page/403'),
        name: '403',
        meta: {
            keepAlive: true,
            isTab: false,
            isAuth: false
        }
    },
    {
        path: '/500',
        component: () =>
            import ( /* webpackChunkName: "page" */ '@/components/error-page/500'),
        name: '500',
        meta: {
            keepAlive: true,
            isTab: false,
            isAuth: false
        }
    },
    {
        path: '/',
        name: '主页',
        redirect: indexPath
    },
    {
        path: '/myiframe',
        component: Layout,
        redirect: '/myiframe',
        children: [{
            path: ":routerPath",
            name: 'iframe',
            component: () =>
                import ( /* webpackChunkName: "page" */ '@/components/iframe/main'),
            props: true
        }]
    },
    {
        path: '*',
        redirect: '/404',
    },

    {
        path: '/M403',
        component: () =>
            import ( /* webpackChunkName: "page" */ '@/components/error-page/M403'),
        name: 'M403',
        meta: {
            keepAlive: true,
            isTab: false,
            isAuth: false
        }
    },
    {
        path: '/401',
        component: () =>
            import ( /* webpackChunkName: "page" */ '@/components/error-page/401'),
        name: '401',
        meta: {
            keepAlive: true,
            isTab: false,
            isAuth: false
        }
    },
]
