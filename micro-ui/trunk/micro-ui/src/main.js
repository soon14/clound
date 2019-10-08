import Vue from 'vue'
import 'babel-polyfill'
import 'classlist-polyfill'
import axios from './router/axios'
import VueAxios from 'vue-axios'
import App from './App'
import './permission' // 权限
import './error' // 日志
import router from './router/router'
import store from './store'
import { loadStyle,filterForm,vaildData,findLabelByValue,findLabelByValues } from './util/util'
import directive from './directive'
import * as urls from '@/config/env'
import { iconfontUrl, iconfontVersion } from '@/config/env'
import * as filters from './filters' // 全局filter
import './styles/common.scss'
import basicContainer from './components/basic-container/main'
// 插件 json 展示
import vueJsonTreeView from 'vue-json-tree-view'

import { validatenull } from '@/util/validate'
import Viewer from 'v-viewer'
import 'viewerjs/dist/viewer.css'
import treeSelect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
import Qs from 'qs'
Vue.prototype.validatenull = validatenull
Vue.prototype.filterForm = filterForm
Vue.prototype.vaildData = vaildData
Vue.prototype.findLabelByValue = findLabelByValue
Vue.prototype.findLabelByValues = findLabelByValues
Vue.prototype.Qs=Qs
Vue.use(window.AVUE);

Vue.use(router)


Vue.use(vueJsonTreeView)

Vue.use(VueAxios, axios)

Vue.use(Viewer,({
  defaultOptions: { "inline": false,"zIndex":911101, "button": true, "navbar": true, "title": true, "toolbar": true, "tooltip": true, "movable": true, "zoomable": true, "rotatable": true, "scalable": true, "transition": true, "fullscreen": true, "keyboard": true, "url": "data-source" }
}));


import {Notify}from 'vant'
Vue.use(Notify);
// 注册全局容器
Vue.component('basicContainer', basicContainer)
Vue.component('treeSelect', treeSelect)
// 加载相关url地址
Object.keys(urls).forEach(key => {
  Vue.prototype[key] = urls[key]
})

//加载过滤器
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

// 动态加载阿里云字体库
iconfontVersion.forEach(ele => {
  loadStyle(iconfontUrl.replace('$key', ele))
})

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
