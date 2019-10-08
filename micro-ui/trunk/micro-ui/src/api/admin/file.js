
import request from '@/router/axios'

/**
 * 分页获取文件列表
 * @param query
 */
export function fetchFileList (query) {
    return request({
        url: '/admin/file/page',
        method: 'get',
        params: query
    })
}

/**
 * 根据文件id获取文件
 * @param id 文件id
 */
export function getObj (id) {
    return request({
        url: '/admin/file/' + id,
        method: 'get'
    })
}

/**
 * 文件删除
 * @param id
 */
export function delObj (id) {
    return request({
        url: '/admin/file/' + id,
        method: 'delete'
    })
}

/**
 * 获取文件列表
 * @param obj
 */
export function getList (obj) {
    return request({
        url: '/admin/file/list',
        method: 'get',
        data: obj
    })
}

/**
 * 文件下载
 * @param id 文件id
 * @param fileName 文件名称
 * @param dialog 窗口对象
 */
export function download(id, fileName, dialog) {
    axios({
        method: 'get',
        url: '/admin/file/' + id,
        data: {},
        responseType: 'blob'
    }).then(res => {
        console.log("res" + res)
        doDownload(res, fileName)
    }).catch((error) => {
        console.log("失败" + error)
        dialog({
            showClose: true,
            message: '文件下载失败' + error,
            type: 'error'
        })
    })
}

function doDownload(res, fileName) {
    if (!res) {
        return
    }
    let blob = new Blob([res.data])
    let url = window.URL.createObjectURL(blob)

    let link = document.createElement('a')
    link.style.display = 'none'
    link.href = url
    link.setAttribute('download', fileName)

    document.body.appendChild(link)
    link.click()
}