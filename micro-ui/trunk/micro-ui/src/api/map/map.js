import request from '@/router/axios';

/**
 * 获取华为网关授权
 */
export function getToken(){
    let data = new URLSearchParams();
    data.append('grant_type', 'client_credentials');
    data.append('client_secret', 'c545af7160dc480e893bc7806b971a45');
    data.append('client_id', '77e3266ad5f84ba69f752e9a763756c3');
    return request({
      url:"/v1/apigw/oauth2/token",
      method: 'post',
      headers:{
        'Content-Type':'application/x-www-form-urlencoded',
      },
      data:data,
    });
};

/**
 * 获取地址
 * @params   url:请求的url
 **/
function getMapList(data,url){
  return new Promise((resolve,reject)=>{
    getToken().then((token) =>{
      request({
        url:url,
        method: 'post',
        headers:{
          'Authorization':"Bearer "+ token.data.access_token,
          'Content-Type':'application/json',
          'noLocal':true
        },
        data:data,
      }).then(res=>{
        resolve(res)
      }).catch(err=>{
        reject(err)
      })
    })
  })
}

/**
* 获取地址正向匹配服务
* @params data type==>Object
**/
export function matchForward (data) {
  return new Promise((resolve,reject)=>{
    getMapList(data,'/finest/services/address/matchForward').then(res=>{
      resolve(res)
    }).catch(err=>{
      reject(err)
    })
  })
};

/**
 * 获取地址反向匹配服务
 **/
export function matchReverse (data) {
  return new Promise((resolve,reject)=>{
    getMapList(data,'/finest/services/address/matchReverse').then(res=>{
      resolve(res)
    }).catch(err=>{
      reject(err)
    })
  })
};

/**
 * 获取地址智能提示
 **/
export function autoComplete (data) {
  return new Promise((resolve,reject)=>{
    getMapList(data,'/finest/services/address/autoComplete').then(res=>{
      resolve(res)
    }).catch(err=>{
      reject(err)
    })
  })
};

/**
 * 获取地址智能搜索
 **/
export function intSearch (data) {
  return new Promise((resolve,reject)=>{
    getMapList(data,'/finest/services/address/intSearch').then(res=>{
      resolve(res)
    }).catch(err=>{
      reject(err)
    })
  })
};

/**
 * 获取信息点智能搜索
 **/
export function matchPoint (data) {
  return new Promise((resolve,reject)=>{
    getMapList(data,'/finest/services/address/matchPoint').then(res=>{
      resolve(res)
    }).catch(err=>{
      reject(err)
    })
  })
};

/**
 * 位置所属行政区划、辖区归属服务
 **/
export function matchRegion (data) {
    return new Promise((resolve,reject)=>{
        getMapList(data,'/finest/services/address/matchRegion').then(res=>{
            resolve(res)
        }).catch(err=>{
            reject(err)
        })
    })
};

/**
 * 行政辖区、管理辖区
 **/
export function getAreaBoundary (data) {
    return new Promise((resolve,reject)=>{
        getMapList(data,'/finest/services/address/getAreaBoundary').then(res=>{
            resolve(res)
        }).catch(err=>{
            reject(err)
        })
    })
};



