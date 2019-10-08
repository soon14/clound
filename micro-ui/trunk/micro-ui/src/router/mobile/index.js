
export default [
    {
        path: '/mobile',
        name: '移动端页面',
         component: () =>
            import ( '@/views/mobile/page/mainpage'),
        redirect:"/mobile/peosoncenter",
        children: [{//子路由
            path: '/mobile/peosoncenter',
            component: () =>
                import ( '@/views/mobile/page/personalCenter'),
            },
            {
                path: '/mobile/find',
                component: () =>
                    import ( '@/views/mobile/page/find'),
            },
        ],
    },
    {
        path: '/mobile/simpleQuery',
        component: () =>
        import ( '@/views/mobile/simple-query/place/queryTab'),
        meta:{
            keepAlive:true,
            isBack:false
        }
    },
    {
        path: '/mobile/employee/index',
        component: () =>
            import ( '@/views/mobile/employee/index'),
            meta:{
                keepAlive:true,
                isBack:false
            }
    },
    {
    path: '/mobile/ajQuery/index',
        component: () =>
    import ( '@/views/mobile/ajQuery/index'),
},
    {
    path: '/mobile/ajQuery/ajDetail',
        component: () =>
    import ( '@/views/mobile/ajQuery/ajDetail'),
},
    {
        path: '/mobile/simpleQuery/ylcsDetail',
        component: () =>
            import ( '@/views/mobile/simple-query/place/ylcs/detail'),
    },
    {
        path: '/mobile/simpleQuery/hotelDetail',
        component: () =>
            import ( '@/views/mobile/simple-query/place/hotel/hotelDetail'),
    },
    {
        path: '/mobile/simpleQuery/mechanicalDetail',
        component: () =>
            import ( '@/views/mobile/simple-query/place/mechanical/mechanicalDetail'),
    },
    {
        path: '/mobile/simpleQuery/kyzyDetail',
        component: () =>
            import ( '@/views/mobile/simple-query/place/kyzy/kyzyDetail'),
    },
    {
        path: '/mobile/simpleQuery/jzDetail',
        component: () =>
            import ( '@/views/mobile/simple-query/place/jzxt/jzDetail'),
    },
    {
        path: '/mobile/employee/yl/detail',
        component: () =>
            import ( '@/views/mobile/employee/ylDetail'),
    },
    {
        path: '/mobile/employee/jx/detail',
        component: () =>
            import ( '@/views/mobile/employee/jxDetail'),
    },
    {
        path: '/mobile/employee/ly/detail',
        component: () =>
            import ( '@/views/mobile/employee/lyDetail'),
    },
    {
        path: '/mobile/employee/jz/detail',
        component: () =>
            import ( '@/views/mobile/employee/jzDetail'),
    },
    {
        path: '/mobile/simpleQuery/event',
        component: () =>
            import ( '@/views/mobile/simple-query/event/eventList'),
            meta:{
                keepAlive:true,
                isBack:false
            }
    },
    {
        path: '/mobile/simpleQuery/eventDetail',
        component: () =>
            import ( '@/views/mobile/simple-query/event/eventDetail'),
    },
    {
        path: '/mobile/mapQuery',
            component: () =>
        import ( '@/views/mobile/mapQuery/index'),
    },
    {
        path: '/mobile/searchList',
            component: () =>
        import ( '@/views/mobile/mapQuery/searchList'),
    },
    {
        path: '/mobile/peosoncenter/instruction',
        component: () =>
            import ( '@/views/mobile/page/instruction'),
    },

];