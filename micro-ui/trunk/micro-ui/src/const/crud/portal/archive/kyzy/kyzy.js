export const kyzyDict = {
    type:[
        {label:"创业工厂",value:"01"},
        {label:"刻章企业",value:"02"},
        {label:"创业加急点",value:"03"},
        {label:"加工中心",value:"04"}
    ],
    shopType:[
        {
            label:"合作门店",
            value:"0"
        },
        {
            label:"普通门店",
            value:"1"
        }
    ],
    isStart:[
        {label:"启用",value:"0"},
        {label:"禁用",value:"1"}
    ],
    inspectMark:[
        {label:"检验合格",value:"1"},
        {label:"检验不合格",value:"2"},
        {label:"未检验",value:"0"}
    ],
    getLabel: (obj, val) => {
        let name = val
        if (Array.isArray(obj) && obj.length > 0) {
            for (let i = 0; i < obj.length; i++) {
                if (obj[i].value === val) {
                    name = obj[i].label
                    break;
                }
            }
        }return name
    }
}

