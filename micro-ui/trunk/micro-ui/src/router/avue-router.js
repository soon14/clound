import {setStore, getStore} from "@/util/store";

let RouterPlugin = function () {
    this.$router = null
    this.$store = null
}
RouterPlugin.install = function (router, store) {
    this.$router = router
    this.$store = store

    function objToform(obj) {
        let result = []
        Object.keys(obj).forEach(ele => {
            result.push(`${ele}=${obj[ele]}`)
        })
        return result.join('&')
    }

    this.$router.$avueRouter = {
        // 全局配置
        $website: this.$store.getters.website,
        $defaultTitle: '智慧新防控特行场所管理系统',
        routerList: [],
        group: '',
        safe: this,
        // 设置标题
        setTitle: function (title) {
            title = title ? `${title}-${this.$defaultTitle}` : this.$defaultTitle;
            document.title = title
        },
        closeTag: (value) => {
            const tag = value || this.$store.getters.tag
            this.$store.commit('DEL_TAG', tag)
        },
        // 处理路由
        getPath: function (params) {
            let {src} = params
            let result = src || '/'
            let httpStr = src.substr(0,5).toLowerCase()
            if (httpStr =='http:'||httpStr=='https'){
                let myHttpStr = httpStr+src.substring(5,src.length)
                params.src=myHttpStr
                result = `/myiframe/urlPath?${objToform(params)}`
            }
            return result
        },
        // 正则处理路由
        vaildPath: function (list, path) {
            let result = false
            list.forEach(ele => {
                if (new RegExp('^' + ele + '.*', 'g').test(path)) {
                    result = true
                }
            })
            return result
        },
        // 设置路由值
        getValue: function (route) {
            let value = ''
            if (route.query.src) {
                value = route.query.src
            } else {
                value = route.path
            }
            return value
        },
        // 动态路由
        formatRoutes: function (aMenu = [], first) {
            const aRouter = []
            const propsConfig = this.$website.menu.props
            const propsDefault = {
                label: propsConfig.label || 'label',
                path: propsConfig.path || 'path',
                icon: propsConfig.icon || 'icon',
                children: propsConfig.children || 'children',
                meta: propsConfig.meta || 'meta'
            }
            if (aMenu.length === 0) return
            for (let i = 0; i < aMenu.length; i++) {
                const oMenu = aMenu[i]
                if (this.routerList.includes(oMenu[propsDefault.path])) return
                const path = (() => {
                    if (first) {
                        var menuPath = oMenu[propsDefault.path];
                        if (menuPath === null || menuPath === 'null' || typeof (menuPath) === "undefined") {
                            return '';
                        }
                        return menuPath.replace('/index', '')
                    } else {
                        return oMenu[propsDefault.path]
                    }
                })()

                const component = oMenu.component

                const name = oMenu[propsDefault.label]

                const icon = oMenu[propsDefault.icon]

                const children = oMenu[propsDefault.children]

                const meta = {
                    keepAlive: Number(oMenu['keepAlive']) === 0
                }
                const isChild = children.length !== 0
                const oRouter = {
                    path: path,
                    component(resolve) {
                        // 判断是否为首路由
                        if (first) {
                            require(['../page/index'], resolve)

                            // 判断是否为多层路由
                        } else if (isChild && !first) {
                            require(['../page/index/layout'], resolve)

                            // 判断是否为最终的页面视图
                        } else {
                            require([`../${component}.vue`], resolve)
                        }
                    },
                    name: name,
                    icon: icon,
                    meta: meta,
                    redirect: (() => {
                        if (!isChild && first) return `${path}/index`
                        else return ''
                    })(),
                    // 处理是否为一级路由
                    children: !isChild ? (() => {
                        if (first) {
                            oMenu[propsDefault.path] = `${path}/index`
                            return [{
                                component(resolve) {
                                    require([`../${component}.vue`], resolve)
                                },
                                icon: icon,
                                name: name,
                                meta: meta,
                                path: 'index'
                            }]
                        }
                        return []
                    })() : (() => {
                        return this.formatRoutes(children, false)
                    })()
                }
                aRouter.push(oRouter)
            }
            if (first) {
                if (!this.routerList.includes(aRouter[0][propsDefault.path])) {
                    this.safe.$router.addRoutes(aRouter)
                    this.routerList.push(aRouter[0][propsDefault.path])
                    this.addModelListRouter(getStore({name: 'modelRoute'}) || [])
                }
            } else {
                return aRouter
            }
        },
        //智能研判-模型展示 添加路由信息
        addModelListRouter: function (modelList) {
            if (null != modelList && "undefined" != modelList && modelList.length > 0) {
                let modelRoute = []
                for (let model of modelList) {
                    let route=[]
                    if(model.openType=='3'){
                        route=[{
                            path: `/${model.id}/index`,
                            name: `${model.name}`,
                            component(resolve) {
                                require([`@/${model.links}.vue`], resolve)
                            },
                            meta: {
                                keepAlive: true,
                                isTab: false,
                                isAuth: false
                            }
                        }]
                        if (!this.$website.whiteTagList.includes("/"+model.id+"/index")) {
                            this.$website.whiteTagList.push("/"+model.id+"/index")
                        }

                    }else{
                        route = [
                            {
                                path: `/${model.id}`,
                                component(resolve) {
                                    require(['@/page/index'], resolve)
                                },
                                redirect: `/${model.id}/index`,
                                children: [{
                                    path: 'index',
                                    name: `${model.name}`,
                                    component(resolve) {
                                        require([`@/${model.links}.vue`], resolve)
                                    },
                                }]
                            }
                        ]
                    }

                    modelRoute= modelRoute.concat(route)
                }
                this.safe.$router.addRoutes(modelRoute)
            }
        },
        //智能研判-模型展示 添加路由信息
        modelRouter: function (data) {
            let route=[]
            if(data.openType=='3'){
                route=[{
                    path: `/${data.id}/index`,
                    name: `${data.name}`,
                    component(resolve) {
                        require([`@/${data.links}.vue`], resolve)
                    },
                    meta: {
                        keepAlive: true,
                        isTab: false,
                        isAuth: false
                    }
                }]

              if(!this.$website.whiteTagList.includes("/"+data.id+"/index")){
                  this.$website.whiteTagList.push("/"+data.id+"/index")
              }

            } else{
                route = [
                    {
                        path: `/${data.id}`,
                        component(resolve) {
                            require(['@/page/index'], resolve)
                        },
                        redirect: `/${data.id}/index`,
                        children: [{
                            path: 'index',
                            name: `${data.name}`,
                            component(resolve) {
                                require([`@/${data.links}.vue`], resolve)
                            },
                        }]
                    }
                ]
            }

            this.safe.$router.addRoutes(route)
            let routeList = getStore({name: 'modelRoute'}) || []
            let tempRouteList=[]
            if (null != routeList && undefined != routeList && routeList.length > 0) {
                let flag = false
                tempRouteList=  tempRouteList.concat(data)
                for (let temp of routeList) {
                    if (temp.id === data.id &&flag==false) {
                     //   tempRouteList=  tempRouteList.concat(data)
                        flag =true
                    }else{
                        tempRouteList = tempRouteList.concat(temp)
                    }
                }

            }else{
                tempRouteList=  tempRouteList.concat(data)
            }
            if(null!=tempRouteList&&tempRouteList.length>0){
                setStore({name:'modelRoute', content:tempRouteList})
            }

        },

    }

}
export default RouterPlugin
