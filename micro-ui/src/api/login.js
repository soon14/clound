
import request from '@/router/axios'
const scope = 'server'
const basic= 'Basic eWduZXQ6c3Vuc2hpbmU='
export const loginByUsername = (username, password, code, randomStr) => {
  const grant_type = 'password'

  return request({
    url: '/auth/oauth/token',
    headers: {
      isToken:false,
      'Authorization': basic
    },
    method: 'post',
    params: { username, password, randomStr, code, grant_type, scope }
  })
}

export const refreshToken = (refresh_token) => {
  const grant_type = 'refresh_token'
  return request({
    url: '/auth/oauth/token',
    headers: {
      'isToken': false,
      'Authorization': basic,
    },
    method: 'post',
    params: { refresh_token, grant_type, scope }
  })
}




export const loginByPki = (state, code) => {
  const grant_type = 'pki'
  console.log(state+":"+code)
  return request({
    url: '/auth/pki/token/certificate',
    headers: {
      'Authorization': basic
    },
    method: 'post',
    params: { pki: code , grant_type }
  })
}

export const getUserInfo = () => {
  return request({
    url: '/admin/user/info',
    method: 'get'
  })
}

export const logout = () => {
  return request({
    url: '/auth/token/logout',
    method: 'delete'
  })
}
