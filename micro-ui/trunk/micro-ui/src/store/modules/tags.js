import { getStore, setStore } from '@/util/store'
import { diff } from '@/util/util'
import website from '@/const/website'
import {vaildatePc} from "@/util/validate"

const isFirstPage = website.isFirstPage;
const tagWel=(function () {
    if(vaildatePc()){
        return website.fistPage
    }else{
        return website.mobilePage
    }
})();
const tagObj = {
  label: '', // 标题名称
  value: '', // 标题的路径
  params: '', // 标题的路径参数
  query: '', // 标题的参数
  group: [] // 分组
}

// 处理首个标签
function setFistTag (list) {
  if (list.length == 1) {
    list[0].close = false
  } else {
    list.forEach(ele => {
      if (ele.value === tagWel.value && isFirstPage === false) {
        ele.close = false
      } else {
        ele.close = true
      }
    })
  }
}

const navs = {
  state: {
    tagList: getStore({ name: 'tagList' }) || [],
    tag: getStore({ name: 'tag' }) || tagObj,
    tagWel: tagWel
  },
  actions: {},
  mutations: {
    ADD_TAG: (state, action) => {

      let temp = action.value
      if (temp.indexOf("http") != -1 || temp.indexOf("https") != -1) {
        let list = []
        for (let key in action.query) {
          if (key != 'src' && key != 'name' ) {
            let param = `${key}=${action.query[key]}`
            let tempKey = `&${key}=`
            if (temp.indexOf(tempKey) == -1  ) {
              list.push(param)
            }
          }
        }
        list = list.join('&').toString()
        if (temp.indexOf("?") != -1) {
          temp = `${temp}${list.length > 0 ? `&${list}` : ''}`
        } else {
          temp = `${temp}${list.length > 0 ? `?${list}` : ''}`
        }
        action.value = temp
      }
      state.tag = action
      setStore({ name: 'tag', content: state.tag, type: 'session' })
      if (state.tagList.some(ele => diff(ele, action))) return
      state.tagList.push(action)
      setFistTag(state.tagList)
      setStore({ name: 'tagList', content: state.tagList, type: 'session' })
    },
    DEL_TAG: (state, action) => {
      state.tagList = state.tagList.filter(item => {
        return !diff(item, action)
      })
      setFistTag(state.tagList)
      setStore({ name: 'tagList', content: state.tagList, type: 'session' })
    },
    DEL_ALL_TAG: (state) => {
      state.tagList = [state.tagWel]
      setStore({ name: 'tagList', content: state.tagList, type: 'session' })
    },
    DEL_TAG_OTHER: (state) => {
      state.tagList = state.tagList.filter(item => {
        if (item.value === state.tag.value) {
          return true;
        } else if (!website.isFirstPage && item.value === website.fistPage.value) {
          return true;
        }
      })
      setFistTag(state.tagList)
      setStore({ name: 'tagList', content: state.tagList, type: 'session' })
    },
    DEL_TAG_BY_LABEL: (state,label) => {
      state.tagList = state.tagList.filter(item => {
        if (item.label != label) {
          return true;
        }
      })
      setFistTag(state.tagList)
      setStore({ name: 'tagList', content: state.tagList, type: 'session' })
    },
  }
}
export default navs
