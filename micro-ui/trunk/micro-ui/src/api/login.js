import request from '@/router/axios'
import {encryption} from '@/util/util'
import {validatenull} from '@/util/validate'

const scope = 'server'
const basic = 'Basic eWduZXQ6c3Vuc2hpbmU='
export const securityPwd = 'makebysunsineboy'
export const loginByUsername = (username, password, code, randomStr) => {
    const grant_type = 'password'
    return request({
        url: '/auth/oauth/token',
        headers: {
            isToken: false,
            'Authorization': basic
        },
        method: 'post',
        params: {username, password, randomStr, code, grant_type, scope}
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
        params: {refresh_token, grant_type, scope}
    })
}


export const loginByPki = (state, code) => {
    const grant_type = 'pki'
    return request({
        url: '/auth/pki/token/certificate',
        headers: {
            'Authorization': basic
        },
        method: 'post',
        params: {pki: code, grant_type, type: grant_type}
    })
}
/**
 * 政务微信登录
 * @param code 验证CODE值
 */
export const loginByCode = (code, type) => {
    return request({
        url: '/auth/pki/token/certificate',
        headers: {
            'Authorization': basic
        },
        method: 'post',
        params: {securityCode: code, grant_type: "password", type: type}
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
//CAS统一验证登录 START
export const LoginByCas = async (state, code) => {
    let response = await request({
        url: '/cas/sso/user',
        method: 'get',
        headers: {
            "X-Requested-With": "XMLHttpRequest"
        }
    })
    if (!validatenull(response) && !validatenull(response.data)) {
        const idCode = response.data.data
        const result = {code: false, data: ""}
        if (validatenull(idCode)) {
            result.data = "验证信息缺失";
            return result
        }
        const tempForm = {
            code: "",
            type: "idCode"
        }
        tempForm.code = idCode + ""
        try {
            const param = encryption({
                data: tempForm,
                key: securityPwd,
                param: ['code']
            })
            let res = await request({
                url: '/auth/pki/token/certificate',
                headers: {
                    'Authorization': basic
                },
                method: 'post',
                params: {securityCode: param.code, grant_type: param.type, type: param.type}
            })
            if (validatenull(res) || validatenull(res.data) || validatenull(res.data.access_token)) {
                result.data = "没有操作权限"
                return result
            } else {
                return res
            }
        } catch (e) {
            result.data = e
            return result
        }
    } else {
        return response;
    }
}
export const loginOutCas = () => {
    return request({
        url: '/cas/sso/logout',
        method: 'get',
        headers: {
            "X-Requested-With": "XMLHttpRequest"
        },
        params: {}
    })
}
//CAS统一验证登录 END
