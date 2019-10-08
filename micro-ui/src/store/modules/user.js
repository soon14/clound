import {getStore, setStore} from '@/util/store'
import {isURL} from '@/util/validate'
import {getUserInfo, loginByUsername, loginByPki,logout, refreshToken} from '@/api/login'
import {deepClone, encryption} from '@/util/util'
import webiste from '@/const/website'
import {GetMenu} from '@/api/admin/menu'

function addPath(ele, first) {
  const propsConfig = webiste.menu.props
  const propsDefault = {
    label: propsConfig.label || 'label',
    path: propsConfig.path || 'path',
    icon: propsConfig.icon || 'icon',
    children: propsConfig.children || 'children'
  }
  const isChild = ele[propsDefault.children] && ele[propsDefault.children].length !== 0
  if (!isChild && first) {
    ele[propsDefault.path] = ele[propsDefault.path] + '/index'
    return
  }
  ele[propsDefault.children].forEach(child => {
    if (!isURL(child[propsDefault.path])) {
      child[propsDefault.path] = `${ele[propsDefault.path]}/${child[propsDefault.path] ? child[propsDefault.path] : 'index'}`
    }
    addPath(child)
  })
}

const user = {
  state: {
    showWaterMark: false, //是否开启水印,TODO 通过后端配置获取
    waterMark: '', //用户水印
    userInfo: {},
    permissions: {},
    roles: [],
    userRole:{},
    userDept:{},
    isAdmin:false,
    adminFlag:'1',
    menu: getStore({
      name: 'menu'
    }) || [],
    menuAll: [],
    expires_in: getStore({
      name: 'expires_in'
    }) || '',
    access_token: getStore({
      name: 'access_token'
    }) || '',
    refresh_token: getStore({
      name: 'refresh_token'
    }) || ''
  },
  actions: {
    // 根据用户名登录
    LoginByUsername({commit}, userInfo) {
      const user = encryption({
        data: userInfo,
        key: 'makebysunsineboy',
        param: ['password']
      })
      return new Promise((resolve, reject) => {
        loginByUsername(user.username, user.password, user.code, user.randomStr).then(response => {
          const data = response.data
          commit('SET_ACCESS_TOKEN', data.access_token)
          commit('SET_REFRESH_TOKEN', data.refresh_token)
          commit('SET_EXPIRES_IN', data.expires_in)
          commit('CLEAR_LOCK')
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 根据PKI登录
    LoginByPki({commit}, param) {
      return new Promise((resolve, reject) => {
        loginByPki(param.state, param.code).then(response => {
        const data = response.data
        commit('SET_ACCESS_TOKEN', data.access_token)
      commit('SET_REFRESH_TOKEN', data.refresh_token)
      commit('SET_EXPIRES_IN', data.expires_in)
      commit('CLEAR_LOCK')
      resolve()
    }).catch(error => {
        reject(error)
      })
    })
    },
    GetUserInfo({commit}) {
      return new Promise((resolve, reject) => {
        getUserInfo().then((res) => {
          const data = res.data.data || {}
          commit('SET_USERIFNO', data.sysUser)
          commit('SET_ROLES', data.roles || [])
          commit('SET_USERROLE', data.sysRole || {})
          commit('SET_USERDEPT', data.sysDept || {})
          commit('SET_PERMISSIONS', data.permissions || [])
          // 获取水印
          commit('SET_WATERMAERK')
          resolve(data)
        }).catch((err) => {
          reject()
        })
      })
    },
    // 刷新token
    RefreshToken({commit, state}) {
      return new Promise((resolve, reject) => {
        refreshToken(state.refresh_token).then(response => {
          const data = response.data
          commit('SET_ACCESS_TOKEN', data.access_token)
          commit('SET_REFRESH_TOKEN', data.refresh_token)
          commit('SET_EXPIRES_IN', data.expires_in)
          commit('CLEAR_LOCK')
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 登出
    LogOut({commit}) {
      return new Promise((resolve, reject) => {
        logout().then(() => {
          commit('SET_MENU', [])
          commit('SET_PERMISSIONS', [])
          commit('SET_USER_INFO', {})
          commit('SET_ACCESS_TOKEN', '')
          commit('SET_REFRESH_TOKEN', '')
          commit('SET_EXPIRES_IN', '')
          commit('SET_ROLES', [])
          commit('SET_USERROLE', {})
          commit('SET_USERDEPT', {})
          commit('DEL_ALL_TAG')
          commit('CLEAR_LOCK')
          commit('CLEAR_WATERMAERK')
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 注销session
    FedLogOut({commit}) {
      return new Promise(resolve => {
        commit('SET_MENU', [])
        commit('SET_PERMISSIONS', [])
        commit('SET_USER_INFO', {})
        commit('SET_ACCESS_TOKEN', '')
        commit('SET_REFRESH_TOKEN', '')
        commit('SET_ROLES', [])
        commit('SET_USERROLE', {})
        commit('SET_USERDEPT', {})
        commit('DEL_ALL_TAG')
        commit('CLEAR_LOCK')
        resolve()
      })
    },
    // 获取系统菜单
    GetMenu({
              commit
            }) {
      return new Promise(resolve => {
        GetMenu().then((res) => {
          const data = res.data.data
          let menu = deepClone(data)
          menu.forEach(ele => {
            addPath(ele)
          })
          commit('SET_MENU', menu)
          resolve(menu)
        })
      })
    }

  },
  mutations: {
    SET_ACCESS_TOKEN: (state, access_token) => {
      state.access_token = access_token
      setStore({
        name: 'access_token',
        content: state.access_token,
        type: 'session'
      })
    },
    SET_EXPIRES_IN: (state, expires_in) => {
      state.expires_in = expires_in
      setStore({
        name: 'expires_in',
        content: state.expires_in,
        type: 'session'
      })
    },
    SET_REFRESH_TOKEN: (state, rfToken) => {
      state.refresh_token = rfToken
      setStore({
        name: 'refresh_token',
        content: state.refresh_token,
        type: 'session'
      })
    },
    SET_USERIFNO: (state, userInfo) => {
      state.userInfo = userInfo
      if(userInfo&&userInfo.userId===state.adminFlag){
        state.isAdmin=true
      }else{
        state.isAdmin=false
      }
    },
    SET_MENU: (state, menu) => {
      state.menu = menu
      setStore({
        name: 'menu',
        content: state.menu,
        type: 'session'
      })
    },
    SET_MENU_ALL: (state, menuAll) => {
      state.menuAll = menuAll
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_USERROLE: (state, userRole) => {
       state.userRole = userRole
    },
   SET_USERDEPT: (state, userDept) => {
    state.userDept = userDept
   },
    SET_PERMISSIONS: (state, permissions) => {
      const list = {}
      for (let i = 0; i < permissions.length; i++) {
        list[permissions[i]] = true
      }
      state.permissions = list
    },
      /**
       * 生成水印图片并缓存
        * @param state
       * @constructor
       */
    SET_WATERMAERK(state){
        if(state.showWaterMark){
            let text = state.userInfo.username + " " + state.userInfo.policeNumber
            console.log("水印文本：" + text)
            let leng = text.length
            let width = 80 + leng * 19
            let height = 70 + leng * 11

            let can = document.createElement('canvas')
            can.width = width
            can.height = height

            let cans = can.getContext('2d')
            cans.rotate(Math.PI/6);

            cans.font = '24px 宋体'
            cans.fillStyle = 'rgba(9, 83, 130, 0.25)'
            cans.textAlign = 'left'
            cans.textBaseline = 'Middle'
            cans.fillText(text, can.width / 3, can.height / 2)
            state.waterMark = can.toDataURL('image/png')
        }
    },
      /**
       * 清除水印缓存
        * @param state
       * @constructor
       */
    CLEAR_WATERMAERK(state){
      state.waterMark = ''
    }
  }

}
export default user