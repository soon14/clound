import request from '@/router/axios'
import { Message } from 'element-ui';
import {validatenull} from '@/util/validate'
/**
 * 警综 附件管理 、获取附件列表 不包含文件大字段信息
 * @param query
 */
export function findPlaceFilePage (query) {
    return request({
        url: 'portal/jz/file/page',
        method: 'get',
        params: query
    })
}

/**
 * 获取照片信息
 * @param query
 * @param tagId 图片标签主键ID
 * @param unitOrPerson 默认图片是单位还是人 true 标识单位 false标识个人
 * @returns {Q.Promise<any> | * | Q.Promise<T | never> | PromiseLike<T | never> | Promise<T | never>}
 */
export async function  getImg(query,tagId,unitOrPerson) {
    let src="/img/people.png";
    await axios({
        url: 'portal/jz/file/getImg',
        method: 'get',
        params: query,
        responseType: 'blob'
    }).then(response => {
        let blob = response.data;
        if(null===blob||undefined===typeof (blob)||blob.size<=0){
            if(unitOrPerson===true){
                src="/img/unit.jpg";
            }else{
                src="/img/people.png";
            }
        }else{
            src = URL.createObjectURL(blob);
            window.setTimeout(function () {
                window.URL.revokeObjectURL(blob)
            }, 0)
        }
    }).catch((error) => {
        if(unitOrPerson===true){
            src="/img/unit.jpg";
        }else{
            src="/img/people.png";
        }
    })
    if(!validatenull(tagId)){
        let img =document.getElementById(tagId);
        img.src =src
    }
    return src;

}


export function downLoadFile(systemId,fileName) {
    return  request({
        url: 'portal/jz/file/' + systemId,
        method: 'get',
        responseType: 'blob'
    }).then((response) => { // 处理返回的文件流
        let blob = response.data;
        if(null===blob||undefined===typeof (blob)||blob.size<=0){
            Message.error("下载失败、文件不存在！")
        }else{
            let url = window.URL.createObjectURL(blob)
            let aTag = document.createElement('a')
            aTag.style.display = 'none'
            aTag.href = url
            aTag.setAttribute('download', fileName)
            document.body.appendChild(aTag)
            aTag.click()
            window.setTimeout(function () {
                window.URL.revokeObjectURL(url)
            }, 0)
        }
    }).catch((error) => {
        Message.error("下载失败、文件不存在！")
    })
}



