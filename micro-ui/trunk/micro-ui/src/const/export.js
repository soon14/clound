/**
 * 数据导出功能js
 * Created by wuxinx on 2019/2/25.
 */

/**
 * 默认导出最大记录数
 * @type {number}
 */
const export_size = 1000

/**
 *
 *  excel数据导出
 * @param url 导出url
 * @param params 查询参数
 * @param exportSize 导出的大小
 * @param exprotFields 需要导出的属性
 * @param exportFiletype 需要导出的文件类型
 * @param ascs 升序字段
 * @param descs 逆序字段
 * @param tpageNo 当前页号
 * @param tpageSize 页面大小
 * @param fileName 文件名称
 * @param dailog 消息提示窗口
 * @param callBack 回调函数
 *
 */
export function doexport(url, params, exportSize, exprotFields, exportFiletype, ascs, descs, tpageNo, tpageSize, fileName, dialog, callBack) {
    if(exprotFields.length < 1){
        dialog({
            showClose: true,
            message: '请选择需要导出的字段',
            type: 'warning'
        })
    }else{
        var pageNo = tpageNo;//当前页号
        var pageSize;
        if("1" == exportSize){//导出当前页
            pageSize = tpageSize;//当前页面大小
        } else{//导出最大条数
            pageNo = 1;
            pageSize = export_size;
        }

        let paramUrl = "";
        for(var key in params){
            let value = params[key]
            if( value != undefined){
                paramUrl += form.key + "= " + value + "&"
            }
        }

        url += "?" + paramUrl + "pageSize="+pageSize+"&pageNo=" + pageNo + "&ascs="+ ascs +"&descs="+ descs + "&exportftype="+exportFiletype+"&exportfields="+exprotFields + "&filetitle=" + fileName;

        let fullFileName;
        let contentType;
        if (exportFiletype == 1) {// excel
            contentType = "application/vnd.ms-excel"
            fullFileName = fileName + ".xls"
        } else if (exportFiletype == 2) {// pdf
            contentType ="application/pdf"
            fullFileName = fileName + ".pdf"
        } else if (exportFiletype == 3) {// csv
            contentType = "application/csv"
            fullFileName = fileName + ".csv"
        } else if (exportFiletype == 4) {// doc
            contentType = "application/msword"
            fullFileName = fileName + ".doc"
        } else if (exportFiletype == 5) {// txt
            contentType = "text/plain"
            fullFileName = fileName + ".txt"
        }

       axios({
            method: 'get',
            url: url,
            data: {},
            responseType: 'blob'
        }).then(res => {
            download(res, contentType, fullFileName)
            //回调关闭窗口
            if(typeof(callBack) == 'function'){
                callBack()
            }
        }).catch((error) => {
            dialog({
                showClose: true,
                message: '导出失败' + error,
                type: 'error'
            })
        })
    }
}

/**
 * 根据流进行文件下载
 * @param res
 * @param contentType
 * @param fileName
 */
function download(res, contentType, fileName) {
    if (!res) {
        return
    }
    let blob = new Blob([res.data], {type: contentType})
    let url = window.URL.createObjectURL(blob)

    let link = document.createElement('a')
    link.style.display = 'none'
    link.href = url
    link.setAttribute('download', fileName)

    document.body.appendChild(link)
    link.click()
}
