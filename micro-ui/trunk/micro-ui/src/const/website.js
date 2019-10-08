export default {
    title: '智慧新防控特行场所管理系统',
    logo: 'ygnet',
    key: 'ygnet',   //配置主键,目前用于存储
    indexTitle: '智慧新防控特行场所管理系统',
    whiteList: ['/login', '/404', '/401', '/lock', '/wxLogin', "/M403"], // 配置无权限可以访问的页面
    whiteTagList: ['/login', '/404', '/401', '/lock', '/wxLogin', "/M403"], // 配置不添加tags页面 （'/advanced-router/mutative-detail/*'——*为通配符）
    lockPage: '/lock',
    tokenTime: 6000,
    infoTitle: '智慧新防控特行场所管理系统',
    statusWhiteList: [428],
    // 配置首页不可关闭
    isFirstPage: false,
    fistPage: {
        label: '首页',
        value: '/wel/index',
        params: {},
        query: {},
        group: [],
        close: false
    },
    mobilePage: {
        label: '移动警务',
        value: '/mobile',
        params: {},
        query: {},
        group: [],
        close: false
    },
    // 配置菜单的属性
    menu: {
        props: {
            label: 'label',
            path: 'path',
            icon: 'icon',
            children: 'children'
        }
    }
}
