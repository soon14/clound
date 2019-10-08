import {checkName,checkCode} from "@/api/portal/area/area";

let newVue = {
    obj: null
}
var validateName = (rule, value, callback) => {
    checkName(value).then(response => {
        let result = response.data.data
        if (window.boxType === 'edit'){
            if (result.id===newVue.obj.editId) {
                callback()
            }else{
                callback(new Error('该名称已经存在'))
            }
        }
        if (result !== null) {
            callback(new Error('字段名已经存在'))
        } else {
            callback()
        }
    })
}
var validateCode = (rule, value, callback) => {
    checkCode(value).then(response => {
        let result = response.data.data
        if (window.boxType === 'edit'){
            if (result.id===newVue.obj.editId) {
                callback()
            }else{
                callback(new Error('该区域名称已经存在'))
            }
        }
        if (result !== null) {
            callback(new Error('该区域名称已经存在'))
        } else {
            callback()
        }
    })
}
export const tableOption = {
    expandLevel: 2,
    border: true,
    index: true,
    tree:true,
    indexLabel: '序号',
    stripe: true,
    menuAlign: 'center',
    editBtn: false,
    delBtn: false,
    align: 'center',
    addBtn: false,
    viewBtn: true,
    menuWidth:'190',
    column: [
        {
        overHidden:true,
        label: '名称',
        prop: 'name',
        span: 12,
        search:'true',
        minWidth:'120',
        align:'left',
        rules: [{
            required: true,
            message: '名称不能为空',
            trigger: 'blur'
        },{ validator: validateName, trigger: 'blur' }
        ]
    },
        {
            overHidden:true,
            label: '全称',
            prop: 'fullName',
            span: 12,
            minWidth:'120',
            align:'left',
            rules: [{
                required: true,
                message: '名称不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            label: '父级菜单',
            prop: 'parentId',
            span: 12,
            hide: true,
            formslot: true,
            slot: true,
            valueDefault:''
        },
        {
            label: '区域编码',
            prop: 'code',
            span: 12,
            rules: [{
                required: true,
                message: '区域编码不能为空',
                trigger: 'blur'
            },{ validator: validateCode, trigger: 'blur' }]
        },
        {
            label: '排序',
            prop: 'sort',
            span: 12,
            type: 'number',
            hide:true,
            rules: [{
                required: true,
                message: '请输入排序',
                trigger: 'blur'
            }]
        },
        {
            label: '区域类型',
            prop: 'type',
            type:'select',
            search:true,
            hide:true,
            dicData:[
                {label: '国家', value: '1'},
                {label: '省份', value: '2'},
                {label: '地市', value: '3'},
                {label: '区县', value: '4'}
                ],
            rules: [{
                required: true,
                message: '类型不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            width: 120,
            label: '备注',
            prop: 'remarks',
            span: 12,
            overHidden:true,
        }

        ]
}
export { newVue }