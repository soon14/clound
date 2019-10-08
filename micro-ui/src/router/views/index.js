import Layout from '@/page/index/'
export default [{
    path: '/wel',
    component: Layout,
    redirect: '/wel/index',
    children: [{
        path: 'index',
        name: '首页',
        component: () =>
            import ( /* webpackChunkName: "views" */ '@/page/wel')
    }]
}, {
    path: '/info',
    component: Layout,
    redirect: '/info/index',
    children: [{
        path: 'index',
        name: '个人信息',
        component: () =>
            import ( /* webpackChunkName: "page" */ '@/views/admin/user/info'),
    }]
}, {
    path: '/activti',
    component: Layout,
    redirect: '/activti/detail',
    children: [{
        path: 'detail/:id',
        component: () =>
            import ( /* webpackChunkName: "views" */ '@/views/activiti/detail')
    }]
}, {
    path: '/admin/dict',
    component: Layout,
    redirect: '/admin/dict/index',
    children: [{
        path: 'index',
        name: '字典管理',
        component: () =>
            import ('@/views/admin/dict/index')
    }]
},
    {
        path: '/portal/unit/standard/metadata',
        component: Layout,
        redirect: '/portal/unit/standard/metadata/fieldIndex',
        children: [{
            path: 'fieldIndex',
            name: '字段编辑',
            component: () =>
                import ('@/views/portal/unit/standard/metadata/fieldIndex')
        }]
},{
    path: '/portal/archive/common-query',
        component: Layout,
        redirect: '/portal/archive/common-query/queryTab',
        children: [{
        path: 'queryTab',
        name: '档案列表',
        component: () =>
    import ('@/views/portal/archive/common-query/queryTab')
}]
},{
        path: '/portal/archive/archivelist',
        component: Layout,
        redirect: '/portal/archive/archivelist/index',
        children: [{
            path: 'index',
            name: '档案列表',
            component: () =>
                import ('@/views/portal/archive/archivelist/index')
        }]
    },{
        path: '/portal/archive/fileInfo',
        component: Layout,
        redirect: '/portal/archive/fileInfo/index',
        children: [{
            path: 'index',
            name: '基本档案信息',
            component: () =>
                import ('@/views/portal/archive/fileInfo/index')
        }]
    },{
        path: '/portal/archive/mechanical/fileInfo',
        component: Layout,
        redirect: '/portal/archive/mechanical/fileInfo/index',
        children: [{
            path: 'index',
            name: '机修基本档案',
            component: () =>
                import ('@/views/portal/archive/mechanical/fileInfo/index')
        }]
    },
    {
        path: '/portal/archive/ylcs/csba',
        component: Layout,
        redirect: '/portal/archive/ylcs/csba/csbaIndex',
        children: [{
            path: 'csbaIndex',
            name: '基本档案信息',
            component: () =>
                import ('@/views/portal/archive/ylcs/csba/csbaIndex')
        }]
    },

]
