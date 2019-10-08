import router from '@/router/router'

export function routerJump(data) {
    let httpStr = data.links.substr(0,5).toLowerCase()
    switch (data.openType) {
        case "1":
            if (httpStr =='http:'||httpStr=='https') {
                let myHttpStr = httpStr+data.links.substring(5,data.links.length)
                data.links = myHttpStr
                router.push({
                    path:  router.$avueRouter.getPath({
                        name: `${data.name}`,
                        src: `${data.links}`
                    }),
                });
            }else{
                router.$avueRouter.modelRouter(data)
                router.push({
                    path:  router.$avueRouter.getPath({
                        name: `${data.name}`,
                        src: `/${data.id}/index`
                    }),
                });
            }
            break;
        case "3":
            if (httpStr =='http:'||httpStr=='https'){
                let myHttpStr = httpStr+data.links.substring(5,data.links.length)
                data.links = myHttpStr
                window.open(data.links, '_blank');
            }else{
                router.$avueRouter.modelRouter(data)
                window.open(`${window.location.origin}/#/`+data.id+"/index", '_blank');
            }
            break;
        default:
            break;
    }


}