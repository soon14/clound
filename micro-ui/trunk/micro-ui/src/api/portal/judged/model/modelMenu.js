
import request from '@/router/axios'
import {validatenull} from '@/util/validate'


export function fetchMenuTree (query) {
  return request({
    url: '/portal/judged/model/tree',
    method: 'get',
    params: query
  })
}

export function addObj (obj) {
  return request({
    url: '/portal/judged/model',
    method: 'post',
    data: obj
  })
}

export function getObj (id) {
  return request({
    url: '/portal/judged/model/' + id,
    method: 'get'
  })
}

export function delObj (id) {
  return request({
    url: '/portal/judged/model/' + id,
    method: 'delete'
  })
}

export function putObj (obj) {
  return request({
    url: '/portal/judged/model',
    method: 'put',
    data: obj
  })
}

export function checkUdId (udId) {
  return request({
    url: '/portal/judged/model/checkUdId/' + udId,
    method: 'get'
  })
}

/**
 * 获取图标--模型配置
 * @param obj
 */
export function getIcon (id,flag) {
  return request({
    url: '/portal/judged/model/icon/' + id+ '/' + flag ,
    responseType: 'blob',
    method: 'get',
  })
}

/**
 * 移除图标
 * @param id
 */
export function delIcon () {
  return request({
    url: '/portal/judged/model/delIcon',
    method: 'delete',
  })
}

/**
 * 分级获取模型
 * @param level,id
 */
export function getModelByLevel (query) {
  return request({
    url: '/portal/judged/model/getModelByLevel',
    method: 'get',
    params: query
  })
}
/**
 * 获取列表模型图标--模型展示页面
 * @param 模型id
 * @param from 标识来自哪个页面（默认分类、展示全部APP），解决相同id获取照片失效
 */
export async function getIconList(id,from) {
  let flag='show'
  let src="/img/unit.jpg";
  await axios({
    url: '/portal/judged/model/icon/' + id+ '/' + flag ,
    method: 'get',
    responseType: 'blob'
  }).then(response => {
    let blob = response.data;
    if(null!==blob&&undefined!==typeof (blob)&&blob.size>0){
      src= URL.createObjectURL(blob);
      window.setTimeout(function () {
        window.URL.revokeObjectURL(blob)
      }, 0)
    }
  }).catch((error) => {
    if (axios.isCancel(error)) {
      console.log('Rquest canceled', error.message); //请求如果被取消，这里是返回取消的message
    } else {
      console.log(error);
    }
  })
  if(from != null && from=="app"){
    if(!validatenull(id)){
    let img =document.getElementById(id+"_allApp");
    img.src =src
    }
  return src;
  }
  if(!validatenull(id)){
    let img =document.getElementById(id);
    img.src =src
  }

  return src;
}


/**
 * 统计目录下的主题模型（app）数量
 * @param id
 */
export function countModel (id) {
  return request({
    url: '/portal/judged/model/count/'+id ,
    method: 'get',
  })
}

/**
 * 根据名称获取所有主题模型（app）
 * @param name
 */
export function getApp (query) {
  return request({
    url: '/portal/judged/model/app',
    method: 'get',
    params: query
  })
}