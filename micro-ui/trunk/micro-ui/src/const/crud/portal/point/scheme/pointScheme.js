/**
 *校验指标分值
 */
let validateScore = (rule, value, callback) => {
    if ((value >= -100 && value <= 100) && value != 0) {
        callback()
    } else {
        callback(new Error('上限范围只能 -100 至 100 、不能为0'))
    }
}

export const tableOption = {
    dialogHeight: '100%',
    dialogWidth:'100%',
    dialogFullscreen:true,
    border: true,
    index: true,
    indexLabel: '序号',
    stripe: true,
    menuAlign: 'center',
    align: 'center',
    addBtn: false,
    viewBtn: false,
    delBtn: false,
    editBtn:false,
    submitBtn: true,
    emptyBtn: true,
    column: [
    { label:"主键ID", prop:"id",hide:true,display:false},
    { label:"方案名称", prop:"name",search: true,rules: [
            {
                required: true,
                message: '状态不能为空',
                trigger: 'blur'
            }
        ]},
    { label:"场所类型", prop:"placeType",dicUrl: '/admin/dict/type/HYLX_DL',
        type: "tree",
        dicMethod: 'get',
        dicQuery: {type: 'HYLX'},
        search: true,},
    { label:"所属周期", prop:"cycleType",type:"select",dicData:[
            {label:'年度',value:'1'},
            {label:'半年度',value:'2'},
            {label:'季度',value:'3'},
            {label:'月度',value:'4'},
        ],search: true,rules: [
            {
                required: true,
                message: '所属周期不能为空',
                trigger: 'blur'
            }
        ]},
    { label:"适用地区", prop:"cityCode", dicUrl: '/portal/area/level/tree',search: true, type: "tree",
        dicMethod: 'get',dicQuery: {
            level: 2
        },
        props: {
            label: "name",
            value: 'code'
        },},
    { label:"状态", prop:"status",type:"select",search: true,slot:true,dicUrl: '/admin/dict/type/point_status',valueDefault: '1',addDisplay:false,editDisplay:false},
    { label:"通用", prop:"universal",type:"select",search: true,dicUrl: '/admin/dict/type/yes_no',valueDefault: '1',rules: [
            {
                required: true,
                message: '通用不能为空',
                trigger: 'blur'
            }
        ]},
    { label:"默认分值", prop:"defaultScore",type:"number",
        valueDefault: 100,
        rules: [
            {
                required: true,
                message: '默认分值不能为空',
                trigger: 'blur'
            }, {
                validator: validateScore,
                trigger: 'blur'
            }
        ]},
    { label:"初始化值", prop:"isInit",type:"select",dicUrl: '/admin/dict/type/yes_no',valueDefault: '1',hide:true},
        { label:"考核开始", prop:"startTime",hide:true,
            type: 'date',
            format: 'yyyy-MM-dd',
            valueFormat: 'yyyy-MM-dd',rules: [
                {
                    required: true,
                    message: '考核开始时间不能为空',
                    trigger: 'blur'
                }
            ]},
        { label:"考核结束", prop:"endTime",hide:true,
            type: 'date',
            format: 'yyyy-MM-dd',
            valueFormat: 'yyyy-MM-dd',rules: [
                {
                    required: true,
                    message: '考核结束时间不能为空',
                    trigger: 'blur'
                }
            ]},
        { label:"创建者", prop:"creator",addDisplay:false,editDisplay:false,hide:true},
        { label:"创建时间", prop:"createTime",hide:true,addDisplay:false,editDisplay:false,
            type: 'date',
            format: 'yyyy-MM-dd HH:mm:ss',
            valueFormat: 'yyyy-MM-dd HH:mm:ss',},
        { label:"更新者", prop:"updater",hide:true,addDisplay:false,editDisplay:false,},
        { label:"更新时间", prop:"updateTime",hide:true,addDisplay:false,editDisplay:false,
            type: 'date',
            format: 'yyyy-MM-dd HH:mm:ss',
            valueFormat: 'yyyy-MM-dd HH:mm:ss',},
        {
            labelWidth:0,
            label: '',
            prop: 'info',
            span:24,
            hide:true,
            formslot:true,
            addDisplay:false,
            editDisplay:false
        }
]
}